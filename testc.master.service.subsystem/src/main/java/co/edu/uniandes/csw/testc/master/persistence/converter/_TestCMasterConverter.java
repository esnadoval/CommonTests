package co.edu.uniandes.csw.testc.master.persistence.converter;
import co.edu.uniandes.csw.testc.master.persistence.entity.TestCTestBEntity;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.converter.TestBConverter;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.master.logic.dto.TestCMasterDTO;
import co.edu.uniandes.csw.testc.persistence.converter.TestCConverter;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _TestCMasterConverter {

    public static TestCMasterDTO entity2PersistenceDTO(TestCEntity testcEntity 
    ,List<TestCTestBEntity> testcTestBEntity 
    ) {
        TestCDTO testcDTO = TestCConverter.entity2PersistenceDTO(testcEntity);
        ArrayList<TestBDTO> testBEntities = new ArrayList<TestBDTO>(testcTestBEntity.size());
        for (TestCTestBEntity testcTestB : testcTestBEntity) {
            testBEntities.add(TestBConverter.entity2PersistenceDTO(testcTestB.getTestBEntity()));
        }
        TestCMasterDTO respDTO  = new TestCMasterDTO();
        respDTO.setTestCEntity(testcDTO);
        respDTO.setListTestB(testBEntities);
        return respDTO;
    }

}