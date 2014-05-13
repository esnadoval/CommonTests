
package co.edu.uniandes.csw.testb.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.logic.api._ITestBLogicService;
import co.edu.uniandes.csw.testb.persistence.api.ITestBPersistence;

public abstract class _TestBLogicService implements _ITestBLogicService {

	@Inject
	protected ITestBPersistence persistance;

	public TestBDTO createTestB(TestBDTO testB){
		return persistance.createTestB( testB); 
    }

	public List<TestBDTO> getTestBs(){
		return persistance.getTestBs(); 
	}

	public TestBDTO getTestB(Long id){
		return persistance.getTestB(id); 
	}

	public void deleteTestB(Long id){
	    persistance.deleteTestB(id); 
	}

	public void updateTestB(TestBDTO testB){
	    persistance.updateTestB(testB); 
	}	
}