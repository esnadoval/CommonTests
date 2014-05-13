
package co.edu.uniandes.csw.testb.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.testb.logic.api.ITestBLogicService;

@Alternative
@Singleton
public class TestBMockLogicService extends _TestBMockLogicService implements ITestBLogicService {
	
}