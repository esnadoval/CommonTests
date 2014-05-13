package co.edu.uniandes.csw.testc.master.logic.ejb;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.api.ITestBPersistence;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.master.logic.api._ITestCMasterLogicService;
import co.edu.uniandes.csw.testc.master.logic.dto.TestCMasterDTO;
import co.edu.uniandes.csw.testc.master.persistence.api.ITestCMasterPersistence;
import co.edu.uniandes.csw.testc.master.persistence.entity.TestCTestBEntity;
import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;
import javax.inject.Inject;

public abstract class _TestCMasterLogicService implements _ITestCMasterLogicService {

    @Inject
    protected ITestCPersistence testcPersistance;
    @Inject
    protected ITestCMasterPersistence testcMasterPersistance;
    @Inject
    protected ITestBPersistence testBPersistance;

    public TestCMasterDTO createMasterTestC(TestCMasterDTO testc) {
        TestCDTO persistedTestCDTO = testcPersistance.createTestC(testc.getTestCEntity());
        if (testc.getCreateTestB() != null) {
            for (TestBDTO testBDTO : testc.getCreateTestB()) {
                TestBDTO persistedTestBDTO = testBPersistance.createTestB(testBDTO);
                TestCTestBEntity testcTestBEntity = new TestCTestBEntity(persistedTestCDTO.getId(), persistedTestBDTO.getId());
                testcMasterPersistance.createTestCTestB(testcTestBEntity);
            }
        }
        return testc;
    }

    public TestCMasterDTO getMasterTestC(Long id) {
        return testcMasterPersistance.getTestC(id);
    }

    public void deleteMasterTestC(Long id) {
        testcPersistance.deleteTestC(id);
    }

    public void updateMasterTestC(TestCMasterDTO testc) {
        testcPersistance.updateTestC(testc.getTestCEntity());

        //---- FOR RELATIONSHIP
        // persist new testB
        if (testc.getCreateTestB() != null) {
            for (TestBDTO testBDTO : testc.getCreateTestB()) {
                TestCTestBEntity testcTestBEntity = new TestCTestBEntity(testc.getTestCEntity().getId(), testBDTO.getId());
                testcMasterPersistance.createTestCTestB(testcTestBEntity);
            }
        }
        // update testB
        if (testc.getUpdateTestB() != null) {
            for (TestBDTO testBDTO : testc.getUpdateTestB()) {
                testBPersistance.updateTestB(testBDTO);
            }
        }
        // delete testB
        if (testc.getDeleteTestB() != null) {
            for (TestBDTO testBDTO : testc.getDeleteTestB()) {
                testcMasterPersistance.deleteTestCTestB(testc.getTestCEntity().getId(), testBDTO.getId());
            }
        }
    }
}
