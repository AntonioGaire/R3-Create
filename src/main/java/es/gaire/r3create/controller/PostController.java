package es.gaire.r3create.controller;

import es.gaire.r3create.domain.Post;
import es.gaire.r3create.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping(value = {"","/"}, params = {"!page", "!type", "!categories"})
    public List<Post> all(){
        log.info("Accediendo a todos los posts");
        return this.postService.all();
    }
    @GetMapping(value = {"","/"}, params = {"!type", "!categories"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page){
        log.info("Accediendo a todos los posts con paginacion");
        Map<String, Object> resposeAll = this.postService.all(page, 12);
        return ResponseEntity.ok(resposeAll);
    }
    @GetMapping(value = {"","/"}, params = {"!type"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page,@RequestParam(value = "categories", defaultValue = "*")String[] categories){
        log.info("Accediendo a algunos posts con paginacion");
        Map<String, Object> resposeAll = this.postService.all(page, 12, categories);
        return ResponseEntity.ok(resposeAll);
    }

    @GetMapping(value = {"","/"}, params = {"!categories"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value = "type", defaultValue = "") String type){
        log.info("Accediendo a algunos posts con paginacion");
        Map<String, Object> resposeAll = this.postService.all(page, 12, type);
        return ResponseEntity.ok(resposeAll);
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value = "type", defaultValue = "") String type, @RequestParam(value = "categories", defaultValue = "*")String[] categories){
        log.info("Accediendo a algunos posts con paginacion");
        Map<String, Object> resposeAll = this.postService.all(page, 12, type, categories);
        return ResponseEntity.ok(resposeAll);
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
