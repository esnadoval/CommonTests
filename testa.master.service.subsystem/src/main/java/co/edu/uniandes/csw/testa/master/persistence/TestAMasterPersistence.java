package co.edu.uniandes.csw.testa.master.persistence;

import javax.ejb.Stateless;

import co.edu.uniandes.csw.testa.master.persistence.api.ITestAMasterPersistence;
import javax.ejb.LocalBean;
import javax.enterprise.inject.Default;

@Default
@Stateless 
@LocalBean
public class TestAMasterPersistence extends _TestAMasterPersistence  implements ITestAMasterPersistence {

}