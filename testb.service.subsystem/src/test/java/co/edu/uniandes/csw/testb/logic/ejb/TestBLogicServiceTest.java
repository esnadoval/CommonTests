
package co.edu.uniandes.csw.testb.logic.ejb;

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
import co.edu.uniandes.csw.testb.logic.api.ITestBLogicService;
import co.edu.uniandes.csw.testb.persistence.TestBPersistence;
import co.edu.uniandes.csw.testb.persistence.api.ITestBPersistence;
import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;

@RunWith(Arquillian.class)
public class TestBLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(TestBLogicService.class.getPackage())
				.addPackage(TestBPersistence.class.getPackage())
				.addPackage(TestBEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ITestBLogicService testBLogicService;
	
	@Inject
	private ITestBPersistence testBPersistence;	

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
		List<TestBDTO> dtos=testBPersistence.getTestBs();
		for(TestBDTO dto:dtos){
			testBPersistence.deleteTestB(dto.getId());
		}
	}

	private List<TestBDTO> data=new ArrayList<TestBDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			TestBDTO pdto=new TestBDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setAtr2(generateRandom(String.class));
			pdto=testBPersistence.createTestB(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createTestBTest(){
		TestBDTO ldto=new TestBDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setAtr2(generateRandom(String.class));
		
		
		TestBDTO result=testBLogicService.createTestB(ldto);
		
		Assert.assertNotNull(result);
		
		TestBDTO pdto=testBPersistence.getTestB(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getAtr2(), pdto.getAtr2());	
	}
	
	@Test
	public void getTestBsTest(){
		List<TestBDTO> list=testBLogicService.getTestBs();
		Assert.assertEquals(list.size(), data.size());
        for(TestBDTO ldto:list){
        	boolean found=false;
            for(TestBDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getTestBTest(){
		TestBDTO pdto=data.get(0);
		TestBDTO ldto=testBLogicService.getTestB(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getAtr2(), ldto.getAtr2());
        
	}
	
	@Test
	public void deleteTestBTest(){
		TestBDTO pdto=data.get(0);
		testBLogicService.deleteTestB(pdto.getId());
        TestBDTO deleted=testBPersistence.getTestB(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateTestBTest(){
		TestBDTO pdto=data.get(0);
		
		TestBDTO ldto=new TestBDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setAtr2(generateRandom(String.class));
		
		
		testBLogicService.updateTestB(ldto);
		
		
		TestBDTO resp=testBPersistence.getTestB(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getAtr2(), resp.getAtr2());	
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