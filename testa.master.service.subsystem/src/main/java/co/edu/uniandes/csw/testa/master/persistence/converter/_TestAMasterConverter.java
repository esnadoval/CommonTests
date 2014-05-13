package co.edu.uniandes.csw.testa.master.persistence.converter;
import co.edu.uniandes.csw.testa.master.persistence.entity.TestATestBEntity;
import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.converter.TestBConverter;
import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.master.logic.dto.TestAMasterDTO;
import co.edu.uniandes.csw.testa.persistence.converter.TestAConverter;
import co.edu.uniandes.csw.testa.persistence.entity.TestAEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _TestAMasterConverter {

    public static TestAMasterDTO entity2PersistenceDTO(TestAEntity testaEntity 
    ,List<TestATestBEntity> testaTestBEntity 
    ) {
        TestADTO testaDTO = TestAConverter.entity2PersistenceDTO(testaEntity);
        ArrayList<TestBDTO> testBEntities = new ArrayList<TestBDTO>(testaTestBEntity.size());
        for (TestATestBEntity testaTestB : testaTestBEntity) {
            testBEntities.add(TestBConverter.entity2PersistenceDTO(testaTestB.getTestBEntity()));
        }
        TestAMasterDTO respDTO  = new TestAMasterDTO();
        respDTO.setTestAEntity(testaDTO);
        respDTO.setListTestB(testBEntities);
        return respDTO;
    }

}