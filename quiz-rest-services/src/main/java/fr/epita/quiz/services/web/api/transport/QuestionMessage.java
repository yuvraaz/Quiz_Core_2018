package fr.epita.quiz.services.web.api.transport;

 import java.util.List;


public class QuestionMessage {
	
	private Long id;
	
	private String questionLabel;
	
	private List<MCQChoiceMessage> mcqChoices;

	public String getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestionLabel(String questionLabel) {
		this.questionLabel = questionLabel;
	}

	@Override
	public String toString() {
		return "QuestionMessage [id=" + id + ", questionLabel=" + questionLabel + ", mcqChoices=" + mcqChoices + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public List<MCQChoiceMessage> getMcqChoiceList() {
		return mcqChoices;
	}

	public void setMcqChoiceList(List<MCQChoiceMessage> mcqChoices) {
		this.mcqChoices = mcqChoices;
	}
	
 
	public void setMcqChoices(List<MCQChoiceMessage> mcqChoices) {
		this.mcqChoices = mcqChoices;
	}

	
	
}
