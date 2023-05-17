package es.gaire.r3create.service;

import es.gaire.r3create.domain.Post;
import es.gaire.r3create.domain.PostType;
import es.gaire.r3create.exception.PostNotFoundException;
import es.gaire.r3create.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> all(){
        return this.postRepository.findAll();
    }

    public Map<String, Object> all(int page, int size){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllPaginated(paginated);
        Map<String, Object> response = new HashMap<>();

        response.put("posts", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;

    }

    public Map<String, Object> all(int page, int size, String type){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllByPostTypePaginated(type, paginated);
        Map<String, Object> response = new HashMap<>();

        response.put("posts", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;

    }

    public Map<String, Object> all(int page, int size, String[] categories){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllByCategoriesPaginated(categories, paginated);
        Map<String, Object> response = new HashMap<>();

        response.put("posts", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;

    }
    public Map<String, Object> all(int page, int size, String type, String[] categories){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllByPostTypeAndCategoriesPaginated(type, categories, paginated);
        Map<String, Object> response = new HashMap<>();

        response.put("posts", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;

    }

    public Post find(Long id) {return this.postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));}

    public List<Post> allByType(Long id){ return this.postRepository.findAllByPostType(id);}
}
