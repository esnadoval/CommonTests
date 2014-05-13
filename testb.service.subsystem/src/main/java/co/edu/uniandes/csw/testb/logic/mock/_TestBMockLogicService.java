
package co.edu.uniandes.csw.testb.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.logic.api._ITestBLogicService;

public abstract class _TestBMockLogicService implements _ITestBLogicService {

	private Long id= new Long(1);
	protected List<TestBDTO> data=new ArrayList<TestBDTO>();

	public TestBDTO createTestB(TestBDTO testB){
		id++;
		testB.setId(id);
		return testB;
    }

	public List<TestBDTO> getTestBs(){
		return data; 
	}

	public TestBDTO getTestB(Long id){
		for(TestBDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteTestB(Long id){
	    TestBDTO delete=null;
		for(TestBDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateTestB(TestBDTO testB){
	    TestBDTO delete=null;
		for(TestBDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(testB);
		} 
	}	
}