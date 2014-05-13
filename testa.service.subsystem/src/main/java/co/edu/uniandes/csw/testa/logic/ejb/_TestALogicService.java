
package co.edu.uniandes.csw.testa.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.logic.api._ITestALogicService;
import co.edu.uniandes.csw.testa.persistence.api.ITestAPersistence;

public abstract class _TestALogicService implements _ITestALogicService {

	@Inject
	protected ITestAPersistence persistance;

	public TestADTO createTestA(TestADTO testA){
		return persistance.createTestA( testA); 
    }

	public List<TestADTO> getTestAs(){
		return persistance.getTestAs(); 
	}

	public TestADTO getTestA(Long id){
		return persistance.getTestA(id); 
	}

	public void deleteTestA(Long id){
	    persistance.deleteTestA(id); 
	}

	public void updateTestA(TestADTO testA){
	    persistance.updateTestA(testA); 
	}	
}