package fr.epita.quiz.services.web.api.transport;

import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;


public class MCQChoiceMessage {
	
	private Long id;
	private Boolean valid;
	private String label;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	/*Convert to choice string*/
	public MCQChoice toMCQChoice(Question question) {
		MCQChoice choice = new MCQChoice();
		choice.setChoiceLabel(this.label);
		choice.setValid(valid);
		choice.setId(id);
		choice.setQuestion(question);
		return choice;
		
	}

}
