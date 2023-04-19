package es.gaire.r3create.exception;


public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id) {super("Not found Category with id " +id);}
}
