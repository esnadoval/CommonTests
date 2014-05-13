
package co.edu.uniandes.csw.testb.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.testb.logic.api.ITestBLogicService;

@Default
@Stateless
@LocalBean
public class TestBLogicService extends _TestBLogicService implements ITestBLogicService {

}