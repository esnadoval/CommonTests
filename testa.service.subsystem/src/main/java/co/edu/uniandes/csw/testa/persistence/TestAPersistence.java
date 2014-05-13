
package co.edu.uniandes.csw.testa.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.testa.persistence.api.ITestAPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class TestAPersistence extends _TestAPersistence  implements ITestAPersistence {

}