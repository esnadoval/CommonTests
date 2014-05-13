
package co.edu.uniandes.csw.testc.persistence;

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


import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;

@RunWith(Arquillian.class)
public class TestCPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(TestCPersistence.class.getPackage())
				.addPackage(TestCEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ITestCPersistence testCPersistence;

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
		em.createQuery("delete from TestCEntity").executeUpdate();
	}

	private List<TestCEntity> data=new ArrayList<TestCEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			TestCEntity entity=new TestCEntity();
			entity.setName(generateRandom(String.class));
			entity.setAtr3(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createTestCTest(){
		TestCDTO dto=new TestCDTO();
		dto.setName(generateRandom(String.class));
		dto.setAtr3(generateRandom(String.class));
		
		
		TestCDTO result=testCPersistence.createTestC(dto);
		
		Assert.assertNotNull(result);
		
		TestCEntity entity=em.find(TestCEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());	
		Assert.assertEquals(dto.getAtr3(), entity.getAtr3());	
	}
	
	@Test
	public void getTestCsTest(){
		List<TestCDTO> list=testCPersistence.getTestCs();
		Assert.assertEquals(list.size(), data.size());
        for(TestCDTO dto:list){
        	boolean found=false;
            for(TestCEntity entity:data){
            	if(dto.getId()==entity.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getTestCTest(){
		TestCEntity entity=data.get(0);
		TestCDTO dto=testCPersistence.getTestC(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getAtr3(), dto.getAtr3());
        
	}
	
	@Test
	public void deleteTestCTest(){
		TestCEntity entity=data.get(0);
		testCPersistence.deleteTestC(entity.getId());
        TestCEntity deleted=em.find(TestCEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateTestCTest(){
		TestCEntity entity=data.get(0);
		
		TestCDTO dto=new TestCDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setAtr3(generateRandom(String.class));
		
		
		testCPersistence.updateTestC(dto);
		
		
		TestCEntity resp=em.find(TestCEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getAtr3(), resp.getAtr3());	
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