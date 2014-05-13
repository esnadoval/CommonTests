
package co.edu.uniandes.csw.testc.logic.ejb;

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
import co.edu.uniandes.csw.testc.logic.api.ITestCLogicService;
import co.edu.uniandes.csw.testc.persistence.TestCPersistence;
import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;

@RunWith(Arquillian.class)
public class TestCLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(TestCLogicService.class.getPackage())
				.addPackage(TestCPersistence.class.getPackage())
				.addPackage(TestCEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ITestCLogicService testCLogicService;
	
	@Inject
	private ITestCPersistence testCPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<TestCDTO> dtos=testCPersistence.getTestCs();
		for(TestCDTO dto:dtos){
			testCPersistence.deleteTestC(dto.getId());
		}
	}

	private List<TestCDTO> data=new ArrayList<TestCDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			TestCDTO pdto=new TestCDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setAtr3(generateRandom(String.class));
			pdto=testCPersistence.createTestC(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createTestCTest(){
		TestCDTO ldto=new TestCDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setAtr3(generateRandom(String.class));
		
		
		TestCDTO result=testCLogicService.createTestC(ldto);
		
		Assert.assertNotNull(result);
		
		TestCDTO pdto=testCPersistence.getTestC(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getAtr3(), pdto.getAtr3());	
	}
	
	@Test
	public void getTestCsTest(){
		List<TestCDTO> list=testCLogicService.getTestCs();
		Assert.assertEquals(list.size(), data.size());
        for(TestCDTO ldto:list){
        	boolean found=false;
            for(TestCDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getTestCTest(){
		TestCDTO pdto=data.get(0);
		TestCDTO ldto=testCLogicService.getTestC(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getAtr3(), ldto.getAtr3());
        
	}
	
	@Test
	public void deleteTestCTest(){
		TestCDTO pdto=data.get(0);
		testCLogicService.deleteTestC(pdto.getId());
        TestCDTO deleted=testCPersistence.getTestC(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateTestCTest(){
		TestCDTO pdto=data.get(0);
		
		TestCDTO ldto=new TestCDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setAtr3(generateRandom(String.class));
		
		
		testCLogicService.updateTestC(ldto);
		
		
		TestCDTO resp=testCPersistence.getTestC(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getAtr3(), resp.getAtr3());	
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