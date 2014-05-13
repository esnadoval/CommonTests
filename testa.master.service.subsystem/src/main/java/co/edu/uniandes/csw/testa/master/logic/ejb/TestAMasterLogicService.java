package co.edu.uniandes.csw.testa.master.logic.ejb;

import co.edu.uniandes.csw.testa.master.logic.api.ITestAMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class TestAMasterLogicService extends _TestAMasterLogicService implements ITestAMasterLogicService {

}