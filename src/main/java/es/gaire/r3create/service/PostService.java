package es.gaire.r3create.service;

import es.gaire.r3create.domain.Post;
import es.gaire.r3create.exception.PostNotFoundException;
import es.gaire.r3create.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> all(){
        return this.postRepository.findAll();
    }

    public Post find(Long id) {return this.postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));}
}
