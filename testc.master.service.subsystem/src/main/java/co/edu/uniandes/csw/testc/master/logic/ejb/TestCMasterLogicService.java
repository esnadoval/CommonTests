package co.edu.uniandes.csw.testc.master.logic.ejb;

import co.edu.uniandes.csw.testc.master.logic.api.ITestCMasterLogicService;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

@Default
@Stateless
@LocalBean
public class TestCMasterLogicService extends _TestCMasterLogicService implements ITestCMasterLogicService {

}