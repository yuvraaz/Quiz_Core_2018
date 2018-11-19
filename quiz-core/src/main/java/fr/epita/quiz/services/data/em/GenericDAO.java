package fr.epita.quiz.services.data.em;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDAO<T> {

	private static final Logger LOGGER = LogManager.getLogger(GenericDAO.class);

	@PersistenceContext
	private EntityManager em;

	@Transactional(propagation=Propagation.NESTED)
	public void update(T instance) {
		em.merge(instance);
	}

	@Transactional(propagation=Propagation.NESTED)
	public void delete(T instance) {
		em.remove(instance);
	}

	@Transactional(propagation=Propagation.NESTED)
	public void create(T instance) {
		em.persist(instance);

	}

	public abstract List<T> search(T criteriaInstance);

	public T getById(Class<? extends Serializable> id) {
		return em.find(getType(), id);
	}

	public abstract Class<T> getType();

}
