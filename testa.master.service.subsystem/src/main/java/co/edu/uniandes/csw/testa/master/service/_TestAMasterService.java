package co.edu.uniandes.csw.testa.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.testa.master.logic.api.ITestAMasterLogicService;
import co.edu.uniandes.csw.testa.master.logic.dto.TestAMasterDTO;

public abstract class _TestAMasterService {

    @Inject
    protected ITestAMasterLogicService testaLogicService;

    @POST
    public TestAMasterDTO createTestA(TestAMasterDTO testa) {
        return testaLogicService.createMasterTestA(testa);
    }

    @DELETE
    @Path("{id}")
    public void deleteTestA(@PathParam("id") Long id) {
        testaLogicService.deleteMasterTestA(id);
    }
    
    @GET
    @Path("{id}")
    public TestAMasterDTO getTestA(@PathParam("id") Long id) {
        return testaLogicService.getMasterTestA(id);
    }

    @PUT
    @Path("{id}")
    public void updateTestA(@PathParam("id") Long id, TestAMasterDTO testa) {
        testaLogicService.updateMasterTestA(testa);
    }

}
