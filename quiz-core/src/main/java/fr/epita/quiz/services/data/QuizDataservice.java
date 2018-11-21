package fr.epita.quiz.services.data;

import java.io.Console;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fr.epita.maths.test.TestDI;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.QuestionMCQPozo;
import fr.epita.quiz.datamodel.Question;

@Repository
public class QuizDataservice {

	@Inject
	QuestionDAO questionDAO;
	
	@Inject
	MCQChoiceDAO mcqDAO;
	
	@Inject
	QuestionMCQDAO questionMCQDAO;
	
	
	
	@Inject
	SessionFactory sessionFactory;
	
	public void createQuestionWithChoices(Question question, List<MCQChoice> choices) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		
		if(!questionDAO.isQuestionExists(question.getQuestionLabel())) {
			questionDAO.create(question);
		
		
		for (MCQChoice choice : choices) {
//			choice.setQuestion(question);
			mcqDAO.create(choice);
		}
		}else {
			System.out.println(">>>>>>>>>>>>>>>> the question exists");
		}
		
		tx.commit();
		
		session.close();
	}
	
	public void createQuestionWithChoices1(QuestionMCQPozo pMcqPozo) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		questionMCQDAO.create(pMcqPozo);

		tx.commit();
		
		session.close();
	}
	
	//Find all the search questions by string.
	public Map<Question,List<MCQChoice>> findAllQuestions(Question question) {
		 
		Map<Question,List<MCQChoice>> questionsAndChoices = new LinkedHashMap<Question,List<MCQChoice>>(); 
		List<Question> list = questionDAO.search(question);
		
		for (Question current : list) {
			MCQChoice mcqChoice=new MCQChoice();
//			mcqChoice.setQuestion(question);
			List<MCQChoice> mcqList=mcqDAO.findAll();//later change it to searh.
 			questionsAndChoices.put(current, mcqList);
			
		}
		 
		return questionsAndChoices;

	}
	
	//get question
	public Question getQuestionById(Long id) {
		Question question = questionDAO.getById(id);
		return question;
	}
	
	//delete question 

	public Boolean deleteQuestionWithMCQChoices(Question question) {
 		MCQChoice choiceCriteria = new MCQChoice();
//		choiceCriteria.setQuestion(question);
		List<MCQChoice> mcqChoiceList = mcqDAO.search(choiceCriteria);
System.out.println("List of mcq before deelte.............."+mcqChoiceList.size());
		if (question != null && question.getId() != null) {
 			for (MCQChoice currentChoice : mcqChoiceList) {
 				System.out.println("Name of mcq before deelte.............."+currentChoice.getChoiceLabel());

 				mcqDAO.delete(currentChoice);
			}
			questionDAO.delete(question);
			return true;
		}
		return false;
	}
	
}
