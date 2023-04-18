package es.gaire.r3create.exception;


public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(Long id) {super("Not found Post with id " +id);}
}
