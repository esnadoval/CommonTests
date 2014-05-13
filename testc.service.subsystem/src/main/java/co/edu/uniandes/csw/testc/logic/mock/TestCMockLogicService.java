
package co.edu.uniandes.csw.testc.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.testc.logic.api.ITestCLogicService;

@Alternative
@Singleton
public class TestCMockLogicService extends _TestCMockLogicService implements ITestCLogicService {
	
}