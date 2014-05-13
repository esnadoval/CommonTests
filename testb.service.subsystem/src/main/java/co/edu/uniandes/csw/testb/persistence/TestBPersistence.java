
package co.edu.uniandes.csw.testb.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.testb.persistence.api.ITestBPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class TestBPersistence extends _TestBPersistence  implements ITestBPersistence {

}