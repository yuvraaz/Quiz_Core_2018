package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MCQChoice {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private Boolean valid;
	
	private String choiceLabel;
	private String questionLabel;
	
	@ManyToOne
	private Question question;
	
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	public MCQChoice() {

	}
	
	public MCQChoice(Question q) {
this.question=q;
	}

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


	public String getChoiceLabel() {
		return choiceLabel;
	}


	public void setChoiceLabel(String choiceLabel) {
		this.choiceLabel = choiceLabel;
	}
	
	public String getQuestionLabel() {
		return question.getQuestionLabel();
	}
	public void setQuestionLabel(String questionLabel) {
		this.questionLabel = questionLabel;
	}
	 
}
