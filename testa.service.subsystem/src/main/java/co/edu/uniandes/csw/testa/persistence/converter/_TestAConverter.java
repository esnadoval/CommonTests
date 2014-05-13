
package co.edu.uniandes.csw.testa.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.persistence.entity.TestAEntity;

public abstract class _TestAConverter {


	public static TestADTO entity2PersistenceDTO(TestAEntity entity){
		if (entity != null) {
			TestADTO dto = new TestADTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setAtr1(entity.getAtr1());
			return dto;
		}else{
			return null;
		}
	}
	
	public static TestAEntity persistenceDTO2Entity(TestADTO dto){
		if(dto!=null){
			TestAEntity entity=new TestAEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setAtr1(dto.getAtr1());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<TestADTO> entity2PersistenceDTOList(List<TestAEntity> entities){
		List<TestADTO> dtos=new ArrayList<TestADTO>();
		for(TestAEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<TestAEntity> persistenceDTO2EntityList(List<TestADTO> dtos){
		List<TestAEntity> entities=new ArrayList<TestAEntity>();
		for(TestADTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}