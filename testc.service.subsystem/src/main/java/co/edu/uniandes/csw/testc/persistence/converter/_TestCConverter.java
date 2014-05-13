
package co.edu.uniandes.csw.testc.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;

public abstract class _TestCConverter {


	public static TestCDTO entity2PersistenceDTO(TestCEntity entity){
		if (entity != null) {
			TestCDTO dto = new TestCDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setAtr3(entity.getAtr3());
			return dto;
		}else{
			return null;
		}
	}
	
	public static TestCEntity persistenceDTO2Entity(TestCDTO dto){
		if(dto!=null){
			TestCEntity entity=new TestCEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setAtr3(dto.getAtr3());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<TestCDTO> entity2PersistenceDTOList(List<TestCEntity> entities){
		List<TestCDTO> dtos=new ArrayList<TestCDTO>();
		for(TestCEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<TestCEntity> persistenceDTO2EntityList(List<TestCDTO> dtos){
		List<TestCEntity> entities=new ArrayList<TestCEntity>();
		for(TestCDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}