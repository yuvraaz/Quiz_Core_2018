package fr.epita.quiz.services.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;

@Repository
public class MCQChoiceDAO extends GenericDAO<MCQChoice> {

	private static final Logger LOGGER = LogManager.getLogger(MCQChoiceDAO.class);
	
	@Override
	public List<MCQChoice> search(MCQChoice mcqChoiceCriteria) {
// 		String hqlString = "from MCQChoice as m where m.question = :question";
//		TypedQuery<MCQChoice> searchQuery = em.createQuery(hqlString, MCQChoice.class);
//		searchQuery.setParameter("question", mcqChoiceCriteria.getQuestion());
//
//		return searchQuery.getResultList();
		return findAll();
	}

	public List<MCQChoice> search(Long id) {
		return findAll();	
	
	}

	public List<MCQChoice> findAll() {
		Query<MCQChoice> searchQuery = getSession().createQuery("from MCQChoice", MCQChoice.class);
 		return searchQuery.list();	

	}
	
	@Override
	public Class<MCQChoice> getType() {
		// TODO Auto-generated method stub
		return MCQChoice.class;
	}


	

}
