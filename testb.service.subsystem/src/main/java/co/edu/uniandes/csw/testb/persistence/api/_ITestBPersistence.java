
package co.edu.uniandes.csw.testb.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;

public interface _ITestBPersistence {

	public TestBDTO createTestB(TestBDTO detail);
	public List<TestBDTO> getTestBs();
	public TestBDTO getTestB(Long id);
	public void deleteTestB(Long id);
	public void updateTestB(TestBDTO detail);
	
}