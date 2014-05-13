package co.edu.uniandes.csw.testc.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.testc.master.persistence.api.ITestCMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class TestCMasterPersistence extends _TestCMasterPersistence  implements ITestCMasterPersistence {

}