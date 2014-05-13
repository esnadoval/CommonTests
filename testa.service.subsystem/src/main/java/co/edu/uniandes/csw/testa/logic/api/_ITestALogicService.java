
package co.edu.uniandes.csw.testa.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.testa.logic.dto.TestADTO;

public interface _ITestALogicService {

	public TestADTO createTestA(TestADTO detail);
	public List<TestADTO> getTestAs();
	public TestADTO getTestA(Long id);
	public void deleteTestA(Long id);
	public void updateTestA(TestADTO detail);
	
}