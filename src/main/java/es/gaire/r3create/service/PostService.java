package es.gaire.r3create.service;

import es.gaire.r3create.domain.*;
import es.gaire.r3create.exception.PostNotFoundException;
import es.gaire.r3create.repository.*;
import es.gaire.r3create.request.PostRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigInteger;
import java.util.*;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final TypeRepository postTypeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    public PostService(PostRepository postRepository, TypeRepository postTypeRepository, UserRepository userRepository, CategoryRepository categoryRepository, ImageRepository imageRepository){
        this.postRepository = postRepository;
        this.postTypeRepository = postTypeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    public List<Post> all(){
        return this.postRepository.findAll();
    }

    public Map<String, Object> all(int page, int size){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllPaginated(paginated);

        return responseCreator(pageAll);

    }

    public Map<String, Object> all(int page, int size, String type, int kudos){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllByPostTypePaginated(type,kudos, paginated);

        return responseCreator(pageAll);

    }

    public Map<String, Object> all(int page, int size, String[] categories, int kudos){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllByCategoriesPaginated(categories,kudos, paginated);

        return responseCreator(pageAll);

    }
    public Map<String, Object> all(int page, int size, String type, String[] categories, int kudos){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findAllByPostTypeAndCategoriesPaginated(type, categories, kudos, paginated);

        return responseCreator(pageAll);

    }

    public Map<String, Object> search(int page, int size, String title){

        Pageable paginated = PageRequest.of(page, size, Sort.by("idPost").ascending());
        Page<Post> pageAll = this.postRepository.findByTitleContainingIgnoreCase(title, paginated);

        return responseCreator(pageAll);

    }

    public Post find(Long id) {return this.postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));}

    public List<Post> allByType(Long id){ return this.postRepository.findAllByPostType(id);}

    public void create(PostRequest postRequest){

        List<Category> categories, requestCategories;
        categories = new ArrayList<>();
        requestCategories = postRequest.getCategories();

        requestCategories.forEach( c -> {
            categories.add(categoryRepository.findById(c.getIdCategory()).get());
        });

        Post post = Post.builder()
                .title(postRequest.getTitle())
                .description(postRequest.getDescription())
                .kudos(BigInteger.ZERO)
                .deleted(false)
                .creationDate(new Date())
                .lastModificationDate(new Date())
                .postType(postTypeRepository.findByName(postRequest.getPostType()).get())
                .user(userRepository.findByUsername(postRequest.getUser()).get())
                .recompensa(postRequest.getReward())
                .categories(categories)
                .build();

        List<Image> images = postRequest.getImages();
        List<Image> createdImages = new ArrayList<>();

        images.forEach( i ->{
            Image image = Image.builder()
                    .post(post)
                    .title(i.getTitle())
                    .alt(i.getAlt())
                    .src(i.getSrc())
                    .creationDate(new Date())
                    .lastModificationDate(new Date())
                    .deleted(false)
                    .build();
            createdImages.add(image);
        });

        postRepository.save(post);
        imageRepository.saveAll(createdImages);
    }

    private Map<String, Object> responseCreator(Page<Post> page){
        Map<String, Object> response = new HashMap<>();

        response.put("posts", page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());

        return response;
    }

    @Transactional
    public void delete(long post){
        postRepository.delete(post);
    }
}
