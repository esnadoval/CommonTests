
package co.edu.uniandes.csw.testa.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.testa.logic.dto.TestADTO;
import co.edu.uniandes.csw.testa.persistence.api._ITestAPersistence;
import co.edu.uniandes.csw.testa.persistence.converter.TestAConverter;
import co.edu.uniandes.csw.testa.persistence.entity.TestAEntity;

public abstract class _TestAPersistence implements _ITestAPersistence {

	@PersistenceContext(unitName="TestAPU")
	protected EntityManager entityManager;
	
	public TestADTO createTestA(TestADTO testA) {
		TestAEntity entity=TestAConverter.persistenceDTO2Entity(testA);
		entityManager.persist(entity);
		return TestAConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<TestADTO> getTestAs() {
		Query q = entityManager.createQuery("select u from TestAEntity u");
		return TestAConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public TestADTO getTestA(Long id) {
		return TestAConverter.entity2PersistenceDTO(entityManager.find(TestAEntity.class, id));
	}

	public void deleteTestA(Long id) {
		TestAEntity entity=entityManager.find(TestAEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateTestA(TestADTO detail) {
		TestAEntity entity=entityManager.merge(TestAConverter.persistenceDTO2Entity(detail));
		TestAConverter.entity2PersistenceDTO(entity);
	}

}