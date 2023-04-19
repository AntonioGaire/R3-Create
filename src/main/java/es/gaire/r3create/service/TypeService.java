package es.gaire.r3create.service;

import es.gaire.r3create.domain.PostType;
import es.gaire.r3create.exception.TypeNotFoundException;
import es.gaire.r3create.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    public List<PostType> all(){
        return this.typeRepository.findAll();
    }

    public PostType find(Long id) {return this.typeRepository.findById(id).orElseThrow(() -> new TypeNotFoundException(id));}
}
