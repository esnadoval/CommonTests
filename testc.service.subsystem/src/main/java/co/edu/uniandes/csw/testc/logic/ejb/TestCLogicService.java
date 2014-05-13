
package co.edu.uniandes.csw.testc.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.testc.logic.api.ITestCLogicService;

@Default
@Stateless
@LocalBean
public class TestCLogicService extends _TestCLogicService implements ITestCLogicService {

}