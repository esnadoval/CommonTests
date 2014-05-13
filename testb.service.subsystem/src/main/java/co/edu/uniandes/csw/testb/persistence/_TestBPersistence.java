
package co.edu.uniandes.csw.testb.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testb.persistence.api._ITestBPersistence;
import co.edu.uniandes.csw.testb.persistence.converter.TestBConverter;
import co.edu.uniandes.csw.testb.persistence.entity.TestBEntity;

public abstract class _TestBPersistence implements _ITestBPersistence {

	@PersistenceContext(unitName="TestBPU")
	protected EntityManager entityManager;
	
	public TestBDTO createTestB(TestBDTO testB) {
		TestBEntity entity=TestBConverter.persistenceDTO2Entity(testB);
		entityManager.persist(entity);
		return TestBConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<TestBDTO> getTestBs() {
		Query q = entityManager.createQuery("select u from TestBEntity u");
		return TestBConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public TestBDTO getTestB(Long id) {
		return TestBConverter.entity2PersistenceDTO(entityManager.find(TestBEntity.class, id));
	}

	public void deleteTestB(Long id) {
		TestBEntity entity=entityManager.find(TestBEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateTestB(TestBDTO detail) {
		TestBEntity entity=entityManager.merge(TestBConverter.persistenceDTO2Entity(detail));
		TestBConverter.entity2PersistenceDTO(entity);
	}

}