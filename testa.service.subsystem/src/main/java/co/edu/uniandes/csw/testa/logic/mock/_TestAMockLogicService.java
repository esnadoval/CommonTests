
package co.edu.uniandes.csw.testa.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.logic.api._ITestALogicService;

public abstract class _TestAMockLogicService implements _ITestALogicService {

	private Long id= new Long(1);
	protected List<TestADTO> data=new ArrayList<TestADTO>();

	public TestADTO createTestA(TestADTO testA){
		id++;
		testA.setId(id);
		return testA;
    }

	public List<TestADTO> getTestAs(){
		return data; 
	}

	public TestADTO getTestA(Long id){
		for(TestADTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteTestA(Long id){
	    TestADTO delete=null;
		for(TestADTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateTestA(TestADTO testA){
	    TestADTO delete=null;
		for(TestADTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(testA);
		} 
	}	
}