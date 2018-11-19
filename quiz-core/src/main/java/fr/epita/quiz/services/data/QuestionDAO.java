package fr.epita.quiz.services.data;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.Question;


@Repository
public class QuestionDAO extends GenericDAO<Question>{

	private static final Logger LOGGER = LogManager.getLogger(QuestionDAO.class);


	public List<Question> search(Question questionCriteria) {
		
		Query<Question> searchQuery = getSession().createQuery("from Question where questionLabel like :inputString ", Question.class);
		searchQuery.setParameter("inputString", "%"+questionCriteria.getQuestionLabel()+"%");
		return searchQuery.list();
		

	}

	@Override
	public Class<Question> getType() {
		// TODO Auto-generated method stub
		return Question.class;
	}


}
