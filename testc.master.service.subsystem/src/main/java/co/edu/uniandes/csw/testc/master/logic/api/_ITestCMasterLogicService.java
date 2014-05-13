 
package co.edu.uniandes.csw.testc.master.logic.api;

import co.edu.uniandes.csw.testc.master.logic.dto.TestCMasterDTO;

public interface _ITestCMasterLogicService {

	public TestCMasterDTO createMasterTestC(TestCMasterDTO detail);
    public void updateMasterTestC(TestCMasterDTO detail);
	public void deleteMasterTestC(Long id); 
	public TestCMasterDTO getMasterTestC(Long id);
        
}