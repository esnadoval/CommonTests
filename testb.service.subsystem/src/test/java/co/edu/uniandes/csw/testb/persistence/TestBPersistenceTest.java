
package co.edu.uniandes.csw.testb.persistence;

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


import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.api.ITestBPersistence;
import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;

@RunWith(Arquillian.class)
public class TestBPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(TestBPersistence.class.getPackage())
				.addPackage(TestBEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ITestBPersistence testBPersistence;

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
		em.createQuery("delete from TestBEntity").executeUpdate();
	}

	private List<TestBEntity> data=new ArrayList<TestBEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			TestBEntity entity=new TestBEntity();
			entity.setName(generateRandom(String.class));
			entity.setAtr2(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createTestBTest(){
		TestBDTO dto=new TestBDTO();
		dto.setName(generateRandom(String.class));
		dto.setAtr2(generateRandom(String.class));
		
		
		TestBDTO result=testBPersistence.createTestB(dto);
		
		Assert.assertNotNull(result);
		
		TestBEntity entity=em.find(TestBEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getAtr2(), entity.getAtr2());	
	}
	
	@Test
	public void getTestBsTest(){
		List<TestBDTO> list=testBPersistence.getTestBs();
		Assert.assertEquals(list.size(), data.size());
        for(TestBDTO dto:list){
        	boolean found=false;
            for(TestBEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getTestBTest(){
		TestBEntity entity=data.get(0);
		TestBDTO dto=testBPersistence.getTestB(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getAtr2(), dto.getAtr2());
        
	}
	
	@Test
	public void deleteTestBTest(){
		TestBEntity entity=data.get(0);
		testBPersistence.deleteTestB(entity.getId());
        TestBEntity deleted=em.find(TestBEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateTestBTest(){
		TestBEntity entity=data.get(0);
		
		TestBDTO dto=new TestBDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setAtr2(generateRandom(String.class));
		
		
		testBPersistence.updateTestB(dto);
		
		
		TestBEntity resp=em.find(TestBEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getAtr2(), resp.getAtr2());	
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