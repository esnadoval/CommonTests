package co.edu.uniandes.csw.testa.master.persistence;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testa.master.persistence.entity.TestATestBEntity;
import co.edu.uniandes.csw.testb.persistence.converter.TestBConverter;
import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.master.logic.dto.TestAMasterDTO;
import co.edu.uniandes.csw.testa.master.persistence.api._ITestAMasterPersistence;
import co.edu.uniandes.csw.testa.persistence.api.ITestAPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _TestAMasterPersistence implements _ITestAMasterPersistence {

    @PersistenceContext(unitName = "TestAMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected ITestAPersistence testaPersistence;  

    public TestAMasterDTO getTestA(Long testaId) {
        TestAMasterDTO testaMasterDTO = new TestAMasterDTO();
        TestADTO testa = testaPersistence.getTestA(testaId);
        testaMasterDTO.setTestAEntity(testa);
        testaMasterDTO.setListTestB(getTestBListForTestA(testaId));
        return testaMasterDTO;
    }

    public TestATestBEntity createTestATestB(TestATestBEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteTestATestB(Long testaId, Long testBId) {
        Query q = entityManager.createNamedQuery("TestATestBEntity.deleteTestATestB");
        q.setParameter("testaId", testaId);
        q.setParameter("testBId", testBId);
        q.executeUpdate();
    }

    public List<TestBDTO> getTestBListForTestA(Long testaId) {
        ArrayList<TestBDTO> resp = new ArrayList<TestBDTO>();
        Query q = entityManager.createNamedQuery("TestATestBEntity.getTestBListForTestA");
        q.setParameter("testaId", testaId);
        List<TestATestBEntity> qResult =  q.getResultList();
        for (TestATestBEntity testaTestBEntity : qResult) { 
            if(testaTestBEntity.getTestBEntity()==null){
                entityManager.refresh(testaTestBEntity);
            }
            resp.add(TestBConverter.entity2PersistenceDTO(testaTestBEntity.getTestBEntity()));
        }
        return resp;
    }

}
