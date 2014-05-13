
package co.edu.uniandes.csw.testa.logic.ejb;

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
import co.edu.uniandes.csw.testa.logic.api.ITestALogicService;
import co.edu.uniandes.csw.testa.persistence.TestAPersistence;
import co.edu.uniandes.csw.testa.persistence.api.ITestAPersistence;
import co.edu.uniandes.csw.testa.persistence.entity.TestAEntity;

@RunWith(Arquillian.class)
public class TestALogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(TestALogicService.class.getPackage())
				.addPackage(TestAPersistence.class.getPackage())
				.addPackage(TestAEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ITestALogicService testALogicService;
	
	@Inject
	private ITestAPersistence testAPersistence;	

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
		List<TestADTO> dtos=testAPersistence.getTestAs();
		for(TestADTO dto:dtos){
			testAPersistence.deleteTestA(dto.getId());
		}
	}

	private List<TestADTO> data=new ArrayList<TestADTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			TestADTO pdto=new TestADTO();
			pdto.setName(generateRandom(String.class));
			pdto.setAtr1(generateRandom(String.class));
			pdto=testAPersistence.createTestA(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createTestATest(){
		TestADTO ldto=new TestADTO();
		ldto.setName(generateRandom(String.class));
		ldto.setAtr1(generateRandom(String.class));
		
		
		TestADTO result=testALogicService.createTestA(ldto);
		
		Assert.assertNotNull(result);
		
		TestADTO pdto=testAPersistence.getTestA(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getAtr1(), pdto.getAtr1());	
	}
	
	@Test
	public void getTestAsTest(){
		List<TestADTO> list=testALogicService.getTestAs();
		Assert.assertEquals(list.size(), data.size());
        for(TestADTO ldto:list){
        	boolean found=false;
            for(TestADTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getTestATest(){
		TestADTO pdto=data.get(0);
		TestADTO ldto=testALogicService.getTestA(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getAtr1(), ldto.getAtr1());
        
	}
	
	@Test
	public void deleteTestATest(){
		TestADTO pdto=data.get(0);
		testALogicService.deleteTestA(pdto.getId());
        TestADTO deleted=testAPersistence.getTestA(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateTestATest(){
		TestADTO pdto=data.get(0);
		
		TestADTO ldto=new TestADTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setAtr1(generateRandom(String.class));
		
		
		testALogicService.updateTestA(ldto);
		
		
		TestADTO resp=testAPersistence.getTestA(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getAtr1(), resp.getAtr1());	
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