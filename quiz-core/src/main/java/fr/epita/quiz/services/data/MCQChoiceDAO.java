package fr.epita.quiz.services.data;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;

@Repository
public class MCQChoiceDAO extends GenericDAO<MCQChoice> {

	private static final Logger LOGGER = LogManager.getLogger(MCQChoiceDAO.class);

	public List<MCQChoice> search(MCQChoice mcqChoiceCriteria) {
		Query<MCQChoice> searchQuery = getSession().createQuery("from MCQChoice where question = :question ", MCQChoice.class);
		searchQuery.setParameter("question", mcqChoiceCriteria.getQuestion());
		return searchQuery.list();	

	}

	@Override
	public Class<MCQChoice> getType() {
		// TODO Auto-generated method stub
		return MCQChoice.class;
	}


	

}
