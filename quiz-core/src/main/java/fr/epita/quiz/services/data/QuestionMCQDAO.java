package fr.epita.quiz.services.data;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.QuestionMCQPozo;
import fr.epita.quiz.datamodel.Question;


@Repository
public class QuestionMCQDAO extends GenericDAO<QuestionMCQPozo>{

 
	@Override
	public List<QuestionMCQPozo> search(QuestionMCQPozo criteriaInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionMCQPozo> search(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionMCQPozo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<QuestionMCQPozo> getType() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
