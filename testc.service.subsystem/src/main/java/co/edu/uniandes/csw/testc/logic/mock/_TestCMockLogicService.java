
package co.edu.uniandes.csw.testc.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.logic.api._ITestCLogicService;

public abstract class _TestCMockLogicService implements _ITestCLogicService {

	private Long id= new Long(1);
	protected List<TestCDTO> data=new ArrayList<TestCDTO>();

	public TestCDTO createTestC(TestCDTO testC){
		id++;
		testC.setId(id);
		return testC;
    }

	public List<TestCDTO> getTestCs(){
		return data; 
	}

	public TestCDTO getTestC(Long id){
		for(TestCDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteTestC(Long id){
	    TestCDTO delete=null;
		for(TestCDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateTestC(TestCDTO testC){
	    TestCDTO delete=null;
		for(TestCDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(testC);
		} 
	}	
}