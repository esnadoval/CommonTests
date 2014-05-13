package co.edu.uniandes.csw.testa.master.logic.ejb;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.api.ITestBPersistence;
import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.master.logic.api._ITestAMasterLogicService;
import co.edu.uniandes.csw.testa.master.logic.dto.TestAMasterDTO;
import co.edu.uniandes.csw.testa.master.persistence.api.ITestAMasterPersistence;
import co.edu.uniandes.csw.testa.master.persistence.entity.TestATestBEntity;
import co.edu.uniandes.csw.testa.persistence.api.ITestAPersistence;
import javax.inject.Inject;

public abstract class _TestAMasterLogicService implements _ITestAMasterLogicService {

    @Inject
    protected ITestAPersistence testaPersistance;
    @Inject
    protected ITestAMasterPersistence testaMasterPersistance;
    @Inject
    protected ITestBPersistence testBPersistance;

    public TestAMasterDTO createMasterTestA(TestAMasterDTO testa) {
        TestADTO persistedTestADTO = testaPersistance.createTestA(testa.getTestAEntity());
        if (testa.getCreateTestB() != null) {
            for (TestBDTO testBDTO : testa.getCreateTestB()) {
                TestBDTO persistedTestBDTO = testBPersistance.createTestB(testBDTO);
                TestATestBEntity testaTestBEntity = new TestATestBEntity(persistedTestADTO.getId(), persistedTestBDTO.getId());
                testaMasterPersistance.createTestATestB(testaTestBEntity);
            }
        }
        return testa;
    }

    public TestAMasterDTO getMasterTestA(Long id) {
        return testaMasterPersistance.getTestA(id);
    }

    public void deleteMasterTestA(Long id) {
        testaPersistance.deleteTestA(id);
    }

    public void updateMasterTestA(TestAMasterDTO testa) {
        testaPersistance.updateTestA(testa.getTestAEntity());

        //---- FOR RELATIONSHIP
        // persist new testB
        if (testa.getCreateTestB() != null) {
            for (TestBDTO testBDTO : testa.getCreateTestB()) {
                TestBDTO persistedTestBDTO = testBPersistance.createTestB(testBDTO);
                TestATestBEntity testaTestBEntity = new TestATestBEntity(testa.getTestAEntity().getId(), persistedTestBDTO.getId());
                testaMasterPersistance.createTestATestB(testaTestBEntity);
            }
        }
        // update testB
        if (testa.getUpdateTestB() != null) {
            for (TestBDTO testBDTO : testa.getUpdateTestB()) {
                testBPersistance.updateTestB(testBDTO);
            }
        }
        // delete testB
        if (testa.getDeleteTestB() != null) {
            for (TestBDTO testBDTO : testa.getDeleteTestB()) {
                testaMasterPersistance.deleteTestATestB(testa.getTestAEntity().getId(), testBDTO.getId());
                testBPersistance.deleteTestB(testBDTO.getId());
            }
        }
    }
}
