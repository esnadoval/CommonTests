
package co.edu.uniandes.csw.testc.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.logic.api._ITestCLogicService;
import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;

public abstract class _TestCLogicService implements _ITestCLogicService {

	@Inject
	protected ITestCPersistence persistance;

	public TestCDTO createTestC(TestCDTO testC){
		return persistance.createTestC( testC); 
    }

	public List<TestCDTO> getTestCs(){
		return persistance.getTestCs(); 
	}

	public TestCDTO getTestC(Long id){
		return persistance.getTestC(id); 
	}

	public void deleteTestC(Long id){
	    persistance.deleteTestC(id); 
	}

	public void updateTestC(TestCDTO testC){
	    persistance.updateTestC(testC); 
	}	
}