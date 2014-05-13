package co.edu.uniandes.csw.testb.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.testb.logic.api.ITestBLogicService;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;


public abstract class _TestBService {

	@Inject
	protected ITestBLogicService testBLogicService;
	
	@POST
	public TestBDTO createTestB(TestBDTO testB){
		return testBLogicService.createTestB(testB);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteTestB(@PathParam("id") Long id){
		testBLogicService.deleteTestB(id);
	}
	
	@GET
	public List<TestBDTO> getTestBs(){
		return testBLogicService.getTestBs();
	}
	
	@GET
	@Path("{id}")
	public TestBDTO getTestB(@PathParam("id") Long id){
		return testBLogicService.getTestB(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateTestB(@PathParam("id") Long id, TestBDTO testB){
		testBLogicService.updateTestB(testB);
	}
	
}