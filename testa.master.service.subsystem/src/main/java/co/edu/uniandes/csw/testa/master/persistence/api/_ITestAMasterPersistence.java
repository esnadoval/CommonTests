package co.edu.uniandes.csw.testa.master.persistence.api;

import co.edu.uniandes.csw.testa.master.persistence.entity.TestATestBEntity;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testa.master.logic.dto.TestAMasterDTO;
import java.util.List;

public interface _ITestAMasterPersistence {

    public TestATestBEntity createTestATestB(TestATestBEntity entity);

    public void deleteTestATestB(Long testaId, Long testBId);
    
    public List<TestBDTO> getTestBListForTestA(Long testaId);
    
    public TestAMasterDTO getTestA(Long testaId);

}
