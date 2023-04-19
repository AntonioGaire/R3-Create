package es.gaire.r3create.exception;


public class TypeNotFoundException extends RuntimeException{

    public TypeNotFoundException(Long id) {super("Not found Type with id " +id);}
}
