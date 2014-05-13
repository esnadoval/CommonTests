package co.edu.uniandes.csw.testc.master.persistence.api;

import co.edu.uniandes.csw.testc.master.persistence.entity.TestCTestBEntity;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testc.master.logic.dto.TestCMasterDTO;
import java.util.List;

public interface _ITestCMasterPersistence {

    public TestCTestBEntity createTestCTestB(TestCTestBEntity entity);

    public void deleteTestCTestB(Long testcId, Long testBId);
    
    public List<TestBDTO> getTestBListForTestC(Long testcId);
    
    public TestCMasterDTO getTestC(Long testcId);

}
