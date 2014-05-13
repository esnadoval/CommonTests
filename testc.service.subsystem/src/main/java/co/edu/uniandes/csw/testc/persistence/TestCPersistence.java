
package co.edu.uniandes.csw.testc.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.testc.persistence.api.ITestCPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class TestCPersistence extends _TestCPersistence  implements ITestCPersistence {

}