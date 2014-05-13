
package co.edu.uniandes.csw.testb.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;

public abstract class _TestBConverter {


	public static TestBDTO entity2PersistenceDTO(TestBEntity entity){
		if (entity != null) {
			TestBDTO dto = new TestBDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setAtr2(entity.getAtr2());
			return dto;
		}else{
			return null;
		}
	}
	
	public static TestBEntity persistenceDTO2Entity(TestBDTO dto){
		if(dto!=null){
			TestBEntity entity=new TestBEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setAtr2(dto.getAtr2());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<TestBDTO> entity2PersistenceDTOList(List<TestBEntity> entities){
		List<TestBDTO> dtos=new ArrayList<TestBDTO>();
		for(TestBEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<TestBEntity> persistenceDTO2EntityList(List<TestBDTO> dtos){
		List<TestBEntity> entities=new ArrayList<TestBEntity>();
		for(TestBDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}