/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.master.test.persistence;

import co.edu.uniandes.csw.rules.InitializeData;
import co.edu.uniandes.csw.rules.TestCTestRule;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.TestBPersistence;
import co.edu.uniandes.csw.testb.persistence.api.ITestBPersistence;
import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.master.persistence.TestCMasterPersistence;
import co.edu.uniandes.csw.testc.master.persistence.api.ITestCMasterPersistence;
import co.edu.uniandes.csw.testc.master.persistence.entity.TestCTestBEntity;
import co.edu.uniandes.csw.testc.persistence.TestCPersistence;
import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;
 

import java.util.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
 
@RunWith(Arquillian.class)
public class TestCPersistenceTest {
 
    public static final String DEPLOY = "Prueba";
    //Regla implementada para manejar múltiples escenarios de datos
    @Rule
    public TestCTestRule rule = new TestCTestRule("dataSample");
//Almacena el dato actual de la regla.
    private TestCDTO dataSample;
 
    @Deployment
    public static JavaArchive createDeployment() {
 
        return ShrinkWrap.create(JavaArchive.class, DEPLOY + ".jar")
                //Añade el paquete en el que se encuentra la clase 'TestCPersistance.java'
                //.addPackage(TestCPersistence.class.getPackage())
                //.addPackage(TestBPersistence.class.getPackage())
                .addPackage(TestCMasterPersistence.class.getPackage())
                //Añade el paquete en el que se encuentra la clase 'TestCEntity.java'
                .addPackage(TestCEntity.class.getPackage())
                .addPackage(TestBEntity.class.getPackage())
                .addPackage(TestCDTO.class.getPackage())
                .addPackage(TestBDTO.class.getPackage())
                //Finalmente se añaden los archivos persistance.xml y beans.xml para laa Unidad de peristencia y CDI del paquete mínimo
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
                //.addAsResource("META-INF/beans.xml", "META-INF/beans.xml");
    }
    //Atributo que contiene la referencia al componente que se va a probar (la persistencia)
    @Inject
    private ITestCMasterPersistence testCMasterPersistence;
    @Inject
    private ITestCPersistence testCPersistence;
    @Inject
    private ITestBPersistence testBPersistence;
    //Atributo que obtiene el persistance unit especificado en persistance.xml
    @PersistenceContext
    private EntityManager em;
    //Atributo que contiene la referencia al manegador de transacciones de JPA (utilizado para inicializar las pruebas)
    @Inject
    UserTransaction utx;
 
    //Método que configura las pruebas antes de ejecutarlas
    @Before
    public void configTest() {
 
        System.out.println("em: " + em);
       
 
    }
 
    
    @Test
    public void createTestCTest() {
        ArrayList<TestBDTO> details = InitializeData.generateTestB(5);
        em.persist(dataSample);
        ArrayList<TestCTestBEntity> oracle = new ArrayList<TestCTestBEntity>();
        
        for (TestBDTO testBDTO : details) {
            TestCTestBEntity temp = new TestCTestBEntity(dataSample.getId(),testBDTO.getId());
            testCMasterPersistence.createTestCTestB(temp);
        }
        
         ArrayList<TestBDTO> resp = new ArrayList<TestBDTO>();
        Query q = em.createNamedQuery("TestCTestBEntity.getTestBListForTestC");
        q.setParameter("testcId", dataSample.getId());
        List<TestCTestBEntity> qResult =  q.getResultList();
        
        boolean invalid = true;
        if(qResult.size() != oracle.size()){
            assertTrue(false);
        }
        
        for (TestCTestBEntity testCTestBEntity : qResult) {
            boolean fnd = false;
            for (int i = 0; i < oracle.size() && !fnd; i++) {
                invalid = true;
                if(oracle.get(i).getTestCId() == testCTestBEntity.getTestBId() && oracle.get(i).getTestCId() == testCTestBEntity.getTestCId()){
                    fnd = true;
                    invalid = false;
                }
                
                    
            }
        }
        
        assertTrue(!invalid);
        
        
        InitializeData.flushDataShared();
  
 
 
    }
 
}