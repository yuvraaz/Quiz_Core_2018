package fr.epita.quiz.datamodel;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 	
	private String questionLabel;
	
	public Question() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestionLabel(String questionLabel) {
		this.questionLabel = questionLabel;
	}
	
}
