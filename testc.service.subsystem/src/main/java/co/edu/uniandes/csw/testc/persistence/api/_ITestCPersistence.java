
package co.edu.uniandes.csw.testc.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;

public interface _ITestCPersistence {

	public TestCDTO createTestC(TestCDTO detail);
	public List<TestCDTO> getTestCs();
	public TestCDTO getTestC(Long id);
	public void deleteTestC(Long id);
	public void updateTestC(TestCDTO detail);
	
}