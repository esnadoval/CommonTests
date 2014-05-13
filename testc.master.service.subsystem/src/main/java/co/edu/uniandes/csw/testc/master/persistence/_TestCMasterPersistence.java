package co.edu.uniandes.csw.testc.master.persistence;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testc.master.persistence.entity.TestCTestBEntity;
import co.edu.uniandes.csw.testb.persistence.converter.TestBConverter;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.master.logic.dto.TestCMasterDTO;
import co.edu.uniandes.csw.testc.master.persistence.api._ITestCMasterPersistence;
import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _TestCMasterPersistence implements _ITestCMasterPersistence {

    @PersistenceContext(unitName = "TestCMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected ITestCPersistence testcPersistence;  

    public TestCMasterDTO getTestC(Long testcId) {
        TestCMasterDTO testcMasterDTO = new TestCMasterDTO();
        TestCDTO testc = testcPersistence.getTestC(testcId);
        testcMasterDTO.setTestCEntity(testc);
        testcMasterDTO.setListTestB(getTestBListForTestC(testcId));
        return testcMasterDTO;
    }

    public TestCTestBEntity createTestCTestB(TestCTestBEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteTestCTestB(Long testcId, Long testBId) {
        Query q = entityManager.createNamedQuery("TestCTestBEntity.deleteTestCTestB");
        q.setParameter("testcId", testcId);
        q.setParameter("testBId", testBId);
        q.executeUpdate();
    }

    public List<TestBDTO> getTestBListForTestC(Long testcId) {
        ArrayList<TestBDTO> resp = new ArrayList<TestBDTO>();
        Query q = entityManager.createNamedQuery("TestCTestBEntity.getTestBListForTestC");
        q.setParameter("testcId", testcId);
        List<TestCTestBEntity> qResult =  q.getResultList();
        for (TestCTestBEntity testcTestBEntity : qResult) { 
            if(testcTestBEntity.getTestBEntity()==null){
                entityManager.refresh(testcTestBEntity);
            }
            resp.add(TestBConverter.entity2PersistenceDTO(testcTestBEntity.getTestBEntity()));
        }
        return resp;
    }

}
