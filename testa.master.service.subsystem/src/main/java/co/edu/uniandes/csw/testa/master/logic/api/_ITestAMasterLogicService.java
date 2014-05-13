 
package co.edu.uniandes.csw.testa.master.logic.api;

import co.edu.uniandes.csw.testa.master.logic.dto.TestAMasterDTO;

public interface _ITestAMasterLogicService {

	public TestAMasterDTO createMasterTestA(TestAMasterDTO detail);
    public void updateMasterTestA(TestAMasterDTO detail);
	public void deleteMasterTestA(Long id); 
	public TestAMasterDTO getMasterTestA(Long id);
        
}