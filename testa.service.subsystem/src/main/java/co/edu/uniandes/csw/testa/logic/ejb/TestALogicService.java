
package co.edu.uniandes.csw.testa.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.testa.logic.api.ITestALogicService;

@Default
@Stateless
@LocalBean
public class TestALogicService extends _TestALogicService implements ITestALogicService {

}