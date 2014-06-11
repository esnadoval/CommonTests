/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.master.test.persistence;

import co.edu.uniandes.csw.rules.InitializeData;
import co.edu.uniandes.csw.rules.TestCTestRule;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.converter.TestBConverter;
import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.master.persistence.TestCMasterPersistence;
import co.edu.uniandes.csw.testc.master.persistence.api.ITestCMasterPersistence;
import co.edu.uniandes.csw.testc.master.persistence.converter.TestCMasterConverter;
import co.edu.uniandes.csw.testc.master.persistence.entity.TestCTestBEntity;
import co.edu.uniandes.csw.testc.persistence.TestCPersistence;
import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;
import co.edu.uniandes.csw.testc.persistence.converter.TestCConverter;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
    public static WebArchive createDeployment() {

        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                //Añade el paquete en el que se encuentra la clase 'TestCPersistance.java'
                //.addPackage(TestCPersistence.class.getPackage())
                //.addPackage(TestBPersistence.class.getPackage())
                .addPackage(ITestCMasterPersistence.class.getPackage())
                .addPackage(TestCMasterPersistence.class.getPackage())
                .addPackage(ITestCPersistence.class.getPackage())
                .addPackage(TestCPersistence.class.getPackage())
                //Añade el paquete en el que se encuentra la clase 'TestCEntity.java'
                .addPackage(TestCEntity.class.getPackage())
                .addPackage(TestCTestBEntity.class.getPackage())
                .addPackage(TestBEntity.class.getPackage())
                .addPackage(TestCDTO.class.getPackage())
                .addPackage(TestBDTO.class.getPackage())
                .addPackage(TestCMasterConverter.class.getPackage())
                //Finalmente se añaden los archivos persistance.xml y beans.xml para laa Unidad de peristencia y CDI del paquete mínimo
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    //Atributo que contiene la referencia al componente que se va a probar (la persistencia)
    @Inject
    private ITestCMasterPersistence testCMasterPersistence;

    //Atributo que obtiene el persistance unit especificado en persistance.xml
    @PersistenceContext(name = "TestCPU")
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
        try {
            System.err.println("--> " + dataSample);
            utx.begin();
            em.createQuery("delete from TestCEntity").executeUpdate();

            em.createQuery("delete from TestBEntity").executeUpdate();
            em.createQuery("delete from TestCTestBEntity").executeUpdate();
            utx.commit();
            System.err.println("--> load");
            utx.begin();

            ArrayList<TestBDTO> details = InitializeData.generateTestB(5);

            for (TestBDTO testBDTO : details) {
                testBDTO.setId((long) 0);
                TestBEntity e = TestBConverter.persistenceDTO2Entity(testBDTO);
                em.persist(e);
                testBDTO.setId(e.getId());
                // System.err.println("-->ID"+ e.getId());
            }
            TestCEntity m = TestCConverter.persistenceDTO2Entity(dataSample);
            em.persist(m);
            dataSample.setId(m.getId());
            utx.commit();

            ArrayList<TestCTestBEntity> oracle = new ArrayList<TestCTestBEntity>();
            System.err.println("--> begin");
            for (TestBDTO testBDTO : details) {

                TestCTestBEntity temp = new TestCTestBEntity(dataSample.getId(), testBDTO.getId());
                testCMasterPersistence.createTestCTestB(temp);
                oracle.add(temp);
            }

            Query q = em.createNamedQuery("TestCTestBEntity.getTestBListForTestC");
            q.setParameter("testcId", dataSample.getId());
            List<TestCTestBEntity> qResult = q.getResultList();

            boolean invalid = true;
            if (qResult.size() != oracle.size()) {
                assertTrue(false);
            }

            for (TestCTestBEntity testCTestBEntity : qResult) {
                boolean fnd = false;
                for (int i = 0; i < oracle.size() && !fnd; i++) {
                    System.err.println(testCTestBEntity.getTestBId() + "==" + oracle.get(i).getTestCId());
                    invalid = true;
                    if (oracle.get(i).getTestBId() == testCTestBEntity.getTestBId() && oracle.get(i).getTestCId() == testCTestBEntity.getTestCId()) {
                        fnd = true;
                        invalid = false;
                    }

                }
            }

            assertTrue(!invalid);

        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex.printStackTrace();
            }
        }

    }

    @Test
    public void TestBListForCTest() {
        try {
            System.err.println("--> " + dataSample);
            utx.begin();
            em.createQuery("delete from TestCEntity").executeUpdate();

            em.createQuery("delete from TestBEntity").executeUpdate();
            em.createQuery("delete from TestCTestBEntity").executeUpdate();
            utx.commit();
            System.err.println("--> load");
            utx.begin();

            ArrayList<TestBDTO> details = InitializeData.generateTestB(5);

            for (TestBDTO testBDTO : details) {
                testBDTO.setId((long) 0);
                TestBEntity e = TestBConverter.persistenceDTO2Entity(testBDTO);
                em.persist(e);
                testBDTO.setId(e.getId());
                // System.err.println("-->ID"+ e.getId());
            }
            TestCEntity m = TestCConverter.persistenceDTO2Entity(dataSample);
            em.persist(m);
            dataSample.setId(m.getId());

            for (TestBDTO testBDTO : details) {
                TestBEntity temp = TestBConverter.persistenceDTO2Entity(testBDTO);
                em.persist(temp);
                testBDTO.setId(temp.getId());
            }
            ArrayList<TestBDTO> oracle = new ArrayList<TestBDTO>();

            for (TestBDTO testCTestBEntity : details) {
                TestBEntity tmp = TestBConverter.persistenceDTO2Entity(testCTestBEntity);
                em.persist(tmp);
                testCTestBEntity.setId(tmp.getId());
                oracle.add(testCTestBEntity);
            }
            
            

            utx.commit();

            List<TestBDTO> dd = testCMasterPersistence.getTestBListForTestC(dataSample.getId());
            boolean valid = false;
            for (TestBDTO testBDTO : dd) {
                valid = false;
                boolean enc = false;
                for (int i = 0; i < oracle.size() && !enc; i++) {
                    if(oracle.get(i).getId() == testBDTO.getId()){
                        valid = true;
                        enc = true;
                    }
                }
            }
            
            assertTrue(valid);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
            try {
                utx.rollback();
            } catch (Exception ex1) {
                ex.printStackTrace();
            }
        }

    }
}
