package es.gaire.r3create.controller;

import es.gaire.r3create.domain.Post;
import es.gaire.r3create.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping(value = {"","/"})
    public List<Post> all(){
        log.info("Accediendo a todos los posts");
        return this.postService.all();
    }

    @GetMapping("/{id}")
    public Post find(@PathVariable("id") Long id) { return this.postService.find(id);}

    @GetMapping(value = {"papercraft","papercraft/"})
    public List<Post> allPapercraft(){
        log.info("Accediendo a todos los posts");
        return this.postService.allByType(1L);
    }

    @GetMapping(value = {"models","models/"})
    public List<Post> allModels(){
        log.info("Accediendo a todos los posts");
        return this.postService.allByType(2L);
    }

    @GetMapping(value = {"quests","quests/"})
    public List<Post> allQuests(){
        log.info("Accediendo a todos los posts");
        return this.postService.allByType(3L);
    }

    @GetMapping(value = {"forum","forum/"})
    public List<Post> allForumPosts(){
        log.info("Accediendo a todos los posts");
        return this.postService.allByType(4L);
    }
}
