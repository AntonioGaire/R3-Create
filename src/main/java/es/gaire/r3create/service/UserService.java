package es.gaire.r3create.service;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.domain.User;
import es.gaire.r3create.exception.CategoryNotFoundException;
import es.gaire.r3create.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findTopUsers(int top ){ return userRepository.findTopUsers(top); }

    public List<User> all(){
        return this.userRepository.findAll();
    }

    public User find(Long id) {return this.userRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));}
}
