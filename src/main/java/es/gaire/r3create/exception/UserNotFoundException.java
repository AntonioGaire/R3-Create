package es.gaire.r3create.exception;


public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {super("Not found User with id" +id);}
    public UserNotFoundException(String username) {super("Not found User with username" + username);}

}

