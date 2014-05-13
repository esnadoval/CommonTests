
package co.edu.uniandes.csw.testa.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.testa.logic.api.ITestALogicService;

@Alternative
@Singleton
public class TestAMockLogicService extends _TestAMockLogicService implements ITestALogicService {
	
}