package co.edu.uniandes.csw.testc.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.testc.master.logic.api.ITestCMasterLogicService;
import co.edu.uniandes.csw.testc.master.logic.dto.TestCMasterDTO;

public abstract class _TestCMasterService {

    @Inject
    protected ITestCMasterLogicService testcLogicService;

    @POST
    public TestCMasterDTO createTestC(TestCMasterDTO testc) {
        return testcLogicService.createMasterTestC(testc);
    }

    @DELETE
    @Path("{id}")
    public void deleteTestC(@PathParam("id") Long id) {
        testcLogicService.deleteMasterTestC(id);
    }
    
    @GET
    @Path("{id}")
    public TestCMasterDTO getTestC(@PathParam("id") Long id) {
        return testcLogicService.getMasterTestC(id);
    }

    @PUT
    @Path("{id}")
    public void updateTestC(@PathParam("id") Long id, TestCMasterDTO testc) {
        testcLogicService.updateMasterTestC(testc);
    }

}
