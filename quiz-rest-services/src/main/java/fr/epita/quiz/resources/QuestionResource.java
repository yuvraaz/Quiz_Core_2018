package fr.epita.quiz.resources;

import java.util.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
 

 import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.QuestionMCQPozo;
import fr.epita.quiz.services.data.QuestionDAO;
import fr.epita.quiz.services.data.QuizDataservice;
import fr.epita.quiz.services.web.api.transport.MCQChoiceMessage;
import fr.epita.quiz.services.web.api.transport.QuestionMessage;

@Path(QuestionResource.PATH)
public class QuestionResource {
	
	static final String PATH = "questions";

	@Inject
	QuizDataservice ds;
	
	@Inject
	QuestionDAO qDao;
	
	/*Create Questions*/
	@POST
	@Path("/")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response createQuestion(QuestionMessage message) throws URISyntaxException {
 		
		Question question = toQuestion(message);
		
		ds.createQuestionWithChoices(question,toMCQChoiceList(message.getMcqChoiceList()));		
//		ds.createQuestionWithChoices1(messageToQuestionMCQPozo(message));		

		return Response.created(new URI(PATH + "/" + String.valueOf(question.getId()))).build();
	}


	 /*get question by id.*/
	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllQuestions( @QueryParam("query") String inputString) {
		
		List<QuestionMessage> messages = new ArrayList<QuestionMessage>();
		Question question = new Question();
		question.setQuestionLabel(inputString);
		
		Map<Question, List<MCQChoice>> map = ds.searchAllQuestions(question);
		
		for(Entry<Question,List<MCQChoice>> entry : map.entrySet()) {
			QuestionMessage qm = new QuestionMessage();
			qm.setId(entry.getKey().getId());
			qm.setQuestionLabel(entry.getKey().getQuestionLabel());
			qm.setMcqChoices(toMCQChoiceMessageList(entry.getValue()));			
			messages.add(qm);
		
		}
		
		return Response.ok(messages).build();
	}
	
	private static List<MCQChoiceMessage> toMCQChoiceMessageList(List<MCQChoice> list) {
		List<MCQChoiceMessage> mcqList=new ArrayList<>();
		
		for (int i=0;i<list.size();i++) {
 			mcqList.add(fromMCQChoiceToMessagee(list.get(i)));
		}
		return mcqList;
	}

	private static MCQChoiceMessage fromMCQChoiceToMessagee(MCQChoice mcq) {
		MCQChoiceMessage mcqm=new MCQChoiceMessage();
		mcqm.setId(mcq.getId());
		mcqm.setLabel(mcq.getChoiceLabel());
		mcqm.setValid(mcq.getValid());
 		return mcqm;
 	}

	/*Get question by id*/
	@GET
	@Path("/{id}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response getOneQuestion( @PathParam("id") String id) {
		
		Question question = qDao.getById(Long.valueOf(id));
		
		if (question == null) {
		
			return Response.status(Status.NOT_FOUND).entity("{\"message\" : 'Not found'}").build();
		}
		
		QuestionMessage message = new QuestionMessage();
		
		message.setQuestionLabel(question.getQuestionLabel());
		
		return Response.ok(message).build();
	}
	
	/*update question by id*/
	@PUT
	@Path("/{id}")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response updateOneQuestion(QuestionMessage message) {
		
		Question question = qDao.getById(Long.valueOf(message.getId()));
		
		if (question == null) {
		
			return Response.status(Status.NOT_FOUND).entity("{\"message\" : 'Not found'}").build();
		}
		
		applyToQuestion(message, question);
		
		qDao.update(question);
		
		return Response.ok(message).build();
	}

/*	Delete question by Id*/

	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam("id")Long id){	
		
		List<QuestionMessage> messages = new ArrayList<QuestionMessage>();
		Question question = new Question();
		question.setId(id);
 		
		Map<Question, List<MCQChoice>> map = ds.searchAllQuestions(question);
		
		for(Entry<Question,List<MCQChoice>> entry : map.entrySet()) {
			QuestionMessage qm = new QuestionMessage();
			qm.setId(entry.getKey().getId());
			qm.setQuestionLabel(entry.getKey().getQuestionLabel());
			qm.setMcqChoices(toMCQChoiceMessageList(entry.getValue()));			
			messages.add(qm);
			System.out.println(">>>>>>>>>>>>>d....."+qm.getQuestionLabel());

		
		}
		
		return Response.ok(question).build();
	}
	
	
	private static Question toQuestion(QuestionMessage qm) {
		
		Question question = new Question();
		
		question.setId(qm.getId());
		
		question.setQuestionLabel(qm.getQuestionLabel());
		
		return question;
	}
	
	private static QuestionMessage fromQuestion(Question question) {
		
		QuestionMessage questionMessage = new QuestionMessage();
		
		questionMessage.setId(question.getId());
		
		questionMessage.setQuestionLabel(question.getQuestionLabel());
		
		return questionMessage;
	}
	
	private static void applyToQuestion(QuestionMessage qmessage, Question question) {
		
		question.setQuestionLabel(qmessage.getQuestionLabel());
	
	}
	
	private static MCQChoice toMCQChoice(MCQChoiceMessage mcqChoiceMessage) {
		
		MCQChoice mcqChoice = new MCQChoice();
	
		mcqChoice.setId(mcqChoiceMessage.getId());
		
		mcqChoice.setChoiceLabel(mcqChoiceMessage.getLabel());
		
		return mcqChoice;
	}
	
	private static MCQChoiceMessage fromMCQChoice(MCQChoice mcqChoice) {
		
		MCQChoiceMessage mcqChoiceMessage = new MCQChoiceMessage();
		
		mcqChoiceMessage.setId(mcqChoice.getId());
		
		mcqChoiceMessage.setLabel(mcqChoice.getChoiceLabel());
		
		return mcqChoiceMessage;
	}
	
	private void addMCQChoiceListToQuestionMessage(List<MCQChoice>list, QuestionMessage qm) {
		
		List<MCQChoiceMessage> resultList = new ArrayList<>();
		
		for (MCQChoice choice : list) {
		
			resultList.add(fromMCQChoice(choice));
		
		}

		qm.setMcqChoiceList(resultList);
	}
	
	
	//Convert MCQChoiceMessage to MCQChoice list
		private static List<MCQChoice> toMCQChoiceList(List<MCQChoiceMessage> list) {
			List<MCQChoice> mcqList=new ArrayList<>();
			for (MCQChoiceMessage mcqm : list) {
				mcqList.add(fromMCQChoiceMessageeToMCQ(mcqm));
			}
			return mcqList;
		}
		
		private static MCQChoice fromMCQChoiceMessageeToMCQ(MCQChoiceMessage mcqm) {
			MCQChoice mcq=new MCQChoice();
			mcq.setChoiceLabel(mcqm.getLabel());
			mcq.setValid(mcqm.getValid());
			mcq.setQuestionLabel(mcq.getQuestionLabel());
			mcq.setQuestion(mcq.getQuestion());
			return mcq;
		}
		
		
		
		private QuestionMCQPozo messageToQuestionMCQPozo(QuestionMessage message) {
			QuestionMCQPozo mcqPozo=new QuestionMCQPozo();
			List<MCQChoice> mcqChoices=toMCQChoiceList(message.getMcqChoices());
			mcqPozo.setQuestionLabel(message.getQuestionLabel());
			mcqPozo.setMcq(mcqChoices);
			return mcqPozo;
		}
}