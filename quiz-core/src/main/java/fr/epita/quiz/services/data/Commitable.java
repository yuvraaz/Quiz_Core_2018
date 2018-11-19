package fr.epita.quiz.services.data;

public interface Commitable<T> {

	
	public T getInstance();
	
	public boolean isCommitable();
	
	public void commit();
}
