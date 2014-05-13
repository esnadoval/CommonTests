package co.edu.uniandes.csw.testa.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.testa.logic.api.ITestALogicService;
import co.edu.uniandes.csw.testa.logic.dto.TestADTO;


public abstract class _TestAService {

	@Inject
	protected ITestALogicService testALogicService;
	
	@POST
	public TestADTO createTestA(TestADTO testA){
		return testALogicService.createTestA(testA);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteTestA(@PathParam("id") Long id){
		testALogicService.deleteTestA(id);
	}
	
	@GET
	public List<TestADTO> getTestAs(){
		return testALogicService.getTestAs();
	}
	
	@GET
	@Path("{id}")
	public TestADTO getTestA(@PathParam("id") Long id){
		return testALogicService.getTestA(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateTestA(@PathParam("id") Long id, TestADTO testA){
		testALogicService.updateTestA(testA);
	}
	
}