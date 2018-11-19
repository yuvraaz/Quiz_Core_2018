package fr.epita.quiz.services.data;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public abstract class GenericDAO<T> {
	
	private static final Logger LOGGER = LogManager.getLogger(GenericDAO.class);

	@Inject
	private SessionFactory sf;

	public void update(T instance) {
		Session session = getSession();
		Commitable<Transaction> commitableTx = getTransaction(session);
		session.update(instance);
		commitableTx.commit();

	}

	public void delete(T instance) {
		Session session = getSession();
		Commitable<Transaction> commitableTx = getTransaction(session);
		session.delete(instance);
		commitableTx.commit();

	}

	public void create(T instance) {
		Session session = getSession();
		Commitable<Transaction> commitableTx = getTransaction(session);
		session.save(instance);
		commitableTx.commit();

	}

	protected final Commitable<Transaction> getTransaction(Session session) {
		final boolean areWeTheInitiatorOfTheTransaction = session.getTransaction() == null
				|| !session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE);
		final Transaction currentTransaction = areWeTheInitiatorOfTheTransaction ? session.beginTransaction()
				: session.getTransaction();

		return new Commitable<Transaction>() {

			public boolean isCommitable() {
				return areWeTheInitiatorOfTheTransaction;
			}

			public Transaction getInstance() {
				return currentTransaction;
			}

			public void commit() {
				if (areWeTheInitiatorOfTheTransaction) {
					currentTransaction.commit();
				}

			}
		};

	}

	protected final Session getSession() {
		Session session = null;
		try {
			session = sf.getCurrentSession();
		} catch (HibernateException he) {
			LOGGER.warn("got an exception while trying to get the current session : {}", he.getMessage());
		}
		if (session == null) {
			session = sf.openSession();
		}
		return session;
	}
	
	public abstract List<T> search(T criteriaInstance);

	public T getById(Serializable id) {
		return getSession().get(getType(), id);
	}
	
	public abstract Class<T> getType();
	
}
