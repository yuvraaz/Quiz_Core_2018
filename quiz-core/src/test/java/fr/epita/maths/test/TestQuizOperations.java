package fr.epita.maths.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.MCQChoiceDAO;
import fr.epita.quiz.services.data.QuestionDAO;
import fr.epita.quiz.services.data.QuizDataservice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/applicationContext.xml"})
public class TestQuizOperations {
	
	@Inject
	private SessionFactory sf;
	
	
	@Inject
	private MCQChoiceDAO mcqDAO;
	
	@Inject
	private QuestionDAO questionDAO;
	
	@Inject
	private QuizDataservice quizDS;
	
	
 
	@Test
	public void testCreateQuestions() {
		
		//given 
		Question question = new Question();
		question.setQuestionLabel("What is J?");
		
		//when
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(question);
		tx.commit();
		
		session.close();
		
		//then
		Session session2 = sf.openSession();
		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
		
		Assert.assertNotEquals(0, searchQuery.list().size());
		session2.close();
		
	}
 
	@Test
	public void testCreateMCQChoices() {
		
		//given 
		Question question = new Question();
		question.setQuestionLabel("What is the capital of Greece?");
		MCQChoice choice1 = new MCQChoice();
		choice1.setChoiceLabel("it is a Kathmandu");
		choice1.setValid(false);
		
		MCQChoice choice2 = new MCQChoice();
		choice2.setChoiceLabel("it is Athence");
		choice2.setValid(true);
		
		choice1.setQuestion(question);
		choice2.setQuestion(question);
		
		//when
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(question);
		session.save(choice1);
		session.save(choice2);
		tx.commit();
		session.close();
		
		//then
		Session session2 = sf.openSession();
		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
		
		Assert.assertNotEquals(0, searchQuery.list().size());
		
		Query<MCQChoice> searchQueryMCQ = session2.createQuery("from MCQChoice", MCQChoice.class);
		Assert.assertEquals(2, searchQueryMCQ.list().size());
		session2.close();
		
	}
	
	

	@Test
	public void testSearchByString() {
		//given 
		Question question = new Question();
		question.setQuestionLabel("What is Computer?");
		MCQChoice choice1 = new MCQChoice();
		choice1.setChoiceLabel("It is a machine");
		choice1.setValid(false);
		
		MCQChoice choice2 = new MCQChoice();
		choice2.setChoiceLabel("It is a device");
		choice2.setValid(true);
		
		choice1.setQuestion(question);
		choice2.setQuestion(question);
		//when
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(question);
		session.save(choice1);
		session.save(choice2);
		tx.commit();
		session.close();
		//then
		Session session2 = sf.openSession();
		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
		
		Assert.assertNotEquals(0, searchQuery.list().size());
		
		Query<MCQChoice> searchMCQQuery = session2.createQuery("from MCQChoice where question = :question ", MCQChoice.class);
	    searchQuery.setParameter("question", question);
 		Assert.assertEquals(2, searchQuery.list().size());
		
		session2.close();
		
	}
	 
	 
	
	
/*	Test Case For Delete Question
*/
	@Test
	public void testDelete() {
		
		System.out.println("Deleting started...");
		
		//given 
		Question question = new Question();
 		question.setQuestionLabel("What is Software?");
		MCQChoice choice1 = new MCQChoice();
		choice1.setChoiceLabel("It is Information Technology");
		choice1.setValid(false);
		
		MCQChoice choice2 = new MCQChoice();
		choice2.setChoiceLabel("It is computer science");
		choice2.setValid(false);
		
		MCQChoice choice3 = new MCQChoice();
		choice3.setChoiceLabel("It is OS");
		choice3.setValid(false);
		
		MCQChoice choice4 = new MCQChoice();
		choice4.setChoiceLabel("It is PC");
		choice4.setValid(true);
	
		List<MCQChoice> mcqs = new ArrayList<MCQChoice>();
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(question);
		session.save(choice1);
		session.save(choice2);
		session.save(choice3);
		session.save(choice4);
		tx.commit();
		session.close();
//		
		 
		//when
		
		
		
		//post
//		this.quizDS.createQuestionWithChoices(question, mcqs);
		//get
		Map<Question,List<MCQChoice>> res=this.quizDS.searchAllQuestions(question);
		System.out.println("response count... "+res.get(question).get(0).getChoiceLabel());

		
//		//then
		Session session2 = sf.openSession();
		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
		Assert.assertNotEquals(0, searchQuery.list().size());
//		
		//before delete
		Query<MCQChoice> searchQueryMCQ = session2.createQuery("from MCQChoice", MCQChoice.class);
		Assert.assertEquals(2, searchQueryMCQ.list().size());
		System.out.println("Mcq Count before>>>>>>>>>>>"+searchQueryMCQ.list().size());
		
		Query deleteQueryQUestion = session2.createQuery("delete MCQChoice where id = 1");
		deleteQueryQUestion.executeUpdate();		
		
		//after delete
		Query<MCQChoice> searchQueryMCQAfter = session2.createQuery("from MCQChoice", MCQChoice.class);
		Assert.assertEquals(2, searchQueryMCQAfter.list().size());
		System.out.println("Mcq Count after>>>>>>>>>>>>>>"+searchQueryMCQ.list().size());

		session2.close();
		
	}
	
	
/*	Update Question with mcq
*/	@Test
	public void testUpdate() {
		//given 
		Question question = new Question();
		question.setQuestionLabel("What is IT?");
		MCQChoice choice1 = new MCQChoice();
		choice1.setChoiceLabel("It is Information Technology");
		choice1.setValid(false);
		
		MCQChoice choice2 = new MCQChoice();
		choice2.setChoiceLabel("It is computer science");
		choice2.setValid(true);
		List<MCQChoice> mcqs = new ArrayList<MCQChoice>();
		//when
		this.quizDS.createQuestionWithChoices(question, mcqs);
		//then
		Session session2 = sf.openSession();
		Query<Question> searchQuery = session2.createQuery("from Question", Question.class);
		Assert.assertNotEquals(0, searchQuery.list().size());
		
		Query<MCQChoice> searchQueryMCQ = session2.createQuery("from MCQChoice", MCQChoice.class);
		Assert.assertEquals(2, searchQueryMCQ.list().size());
	  		Question questionToUpdate = new Question();
	  		MCQChoice mcqToUpdate = new MCQChoice();
	  		
	  		String searchString="What is IT ? ";
	  		Query<Question> searchQUestionQuiry = session2.createQuery("from Question where questionLabel like :inputString ", Question.class);
			searchQUestionQuiry.setParameter("inputString", "%"+searchString+"%");
			Assert.assertEquals(2, searchQUestionQuiry.list().size());
			
	  		Long questionID=searchQUestionQuiry.list().get(0).getId();
	  		questionToUpdate.setId(questionID);
	  		questionToUpdate.setQuestionLabel("What is Programming" );
	  		mcqToUpdate.setChoiceLabel("It is Information Technology");
	  		mcqToUpdate.setValid(false);
	        session2.update(questionToUpdate); 	    
		    session2.close();
		
	} 
	
	
}
