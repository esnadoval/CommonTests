package co.edu.uniandes.csw.testc.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.testc.logic.api.ITestCLogicService;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;


public abstract class _TestCService {

	@Inject
	protected ITestCLogicService testCLogicService;
	
	@POST
	public TestCDTO createTestC(TestCDTO testC){
		return testCLogicService.createTestC(testC);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteTestC(@PathParam("id") Long id){
		testCLogicService.deleteTestC(id);
	}
	
	@GET
	public List<TestCDTO> getTestCs(){
		return testCLogicService.getTestCs();
	}
	
	@GET
	@Path("{id}")
	public TestCDTO getTestC(@PathParam("id") Long id){
		return testCLogicService.getTestC(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateTestC(@PathParam("id") Long id, TestCDTO testC){
		testCLogicService.updateTestC(testC);
	}
	
}