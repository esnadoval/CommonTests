
package co.edu.uniandes.csw.testc.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import co.edu.uniandes.csw.testc.persistence.api._ITestCPersistence;
import co.edu.uniandes.csw.testc.persistence.converter.TestCConverter;
import co.edu.uniandes.csw.testc.persistence.entity.TestCEntity;

public abstract class _TestCPersistence implements _ITestCPersistence {

	@PersistenceContext(unitName="TestCPU")
	protected EntityManager entityManager;
	
	public TestCDTO createTestC(TestCDTO testC) {
		TestCEntity entity=TestCConverter.persistenceDTO2Entity(testC);
		entityManager.persist(entity);
		return TestCConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<TestCDTO> getTestCs() {
		Query q = entityManager.createQuery("select u from TestCEntity u");
		return TestCConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public TestCDTO getTestC(Long id) {
		return TestCConverter.entity2PersistenceDTO(entityManager.find(TestCEntity.class, id));
	}

	public void deleteTestC(Long id) {
		TestCEntity entity=entityManager.find(TestCEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateTestC(TestCDTO detail) {
		TestCEntity entity=entityManager.merge(TestCConverter.persistenceDTO2Entity(detail));
		TestCConverter.entity2PersistenceDTO(entity);
	}

}