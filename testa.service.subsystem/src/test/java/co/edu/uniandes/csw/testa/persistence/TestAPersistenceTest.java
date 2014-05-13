
package co.edu.uniandes.csw.testa.persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.persistence.api.ITestAPersistence;
import co.edu.uniandes.csw.testa.persistence.entity.TestAEntity;

@RunWith(Arquillian.class)
public class TestAPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(TestAPersistence.class.getPackage())
				.addPackage(TestAEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ITestAPersistence testAPersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from TestAEntity").executeUpdate();
	}

	private List<TestAEntity> data=new ArrayList<TestAEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			TestAEntity entity=new TestAEntity();
			entity.setName(generateRandom(String.class));
			entity.setAtr1(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createTestATest(){
		TestADTO dto=new TestADTO();
		dto.setName(generateRandom(String.class));
		dto.setAtr1(generateRandom(String.class));
		
		
		TestADTO result=testAPersistence.createTestA(dto);
		
		Assert.assertNotNull(result);
		
		TestAEntity entity=em.find(TestAEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getAtr1(), entity.getAtr1());	
	}
	
	@Test
	public void getTestAsTest(){
		List<TestADTO> list=testAPersistence.getTestAs();
		Assert.assertEquals(list.size(), data.size());
        for(TestADTO dto:list){
        	boolean found=false;
            for(TestAEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getTestATest(){
		TestAEntity entity=data.get(0);
		TestADTO dto=testAPersistence.getTestA(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getAtr1(), dto.getAtr1());
        
	}
	
	@Test
	public void deleteTestATest(){
		TestAEntity entity=data.get(0);
		testAPersistence.deleteTestA(entity.getId());
        TestAEntity deleted=em.find(TestAEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateTestATest(){
		TestAEntity entity=data.get(0);
		
		TestADTO dto=new TestADTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setAtr1(generateRandom(String.class));
		
		
		testAPersistence.updateTestA(dto);
		
		
		TestAEntity resp=em.find(TestAEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getAtr1(), resp.getAtr1());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}