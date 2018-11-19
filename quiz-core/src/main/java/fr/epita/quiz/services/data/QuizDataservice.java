package fr.epita.quiz.services.data;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;

@Repository
public class QuizDataservice {

	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	MCQChoiceDAO mcqDAO;
	
	@Inject
	SessionFactory sessionFactory;
	
	
	public void createQuestionWithChoices(Question question, List<MCQChoice> choices) {

		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		questionDAO.create(question);
		
		for (MCQChoice choice : choices) {
		
			choice.setQuestion(question);
			
			mcqDAO.create(choice);
		}
		
		tx.commit();
		
		session.close();
	}
	
	public Map<Question,List<MCQChoice>> findAllQuestions(Question question) {
		
		Map<Question,List<MCQChoice>> questionsAndChoices = new LinkedHashMap<Question,List<MCQChoice>>(); 
		
		List<Question> list = questionDAO.search(question);
		
		for (Question current : list) {
			
			questionsAndChoices.put(current, null);//TODO fetch mcqChoices
			
		}
		return questionsAndChoices;
		
		
	}
	
}
