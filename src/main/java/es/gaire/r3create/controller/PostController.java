package es.gaire.r3create.controller;

import es.gaire.r3create.domain.Post;
import es.gaire.r3create.request.DeleteRequest;
import es.gaire.r3create.request.PostRequest;
import es.gaire.r3create.service.PostService;
import es.gaire.r3create.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final UserService userService;

    public PostController(PostService postService, UserService userService){

        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping(value = {"","/"}, params = {"!page", "!type", "!categories"})
    public List<Post> all(){
        log.info("Accediendo a todos los posts");
        return this.postService.all();
    }
    @GetMapping(value = {"","/"}, params = {"!type", "!categories"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page){
        log.info("Accediendo a todos los posts con paginacion");
        Map<String, Object> responseAll = this.postService.all(page, 24);
        return ResponseEntity.ok(responseAll);
    }
    @GetMapping(value = {"","/"}, params = {"!type"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page,@RequestParam(value = "categories", defaultValue = "*")String[] categories, @RequestParam(value="kudos", defaultValue = "0") int kudos){
        log.info("Accediendo a algunos posts con paginacion");
        Map<String, Object> responseAll = this.postService.all(page, 24, categories, kudos);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"","/"}, params = {"!categories"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value = "type", defaultValue = "") String type, @RequestParam(value="kudos", defaultValue = "0") int kudos){
        log.info("Accediendo a algunos posts con paginacion");
        Map<String, Object> responseAll = this.postService.all(page, 24, type, kudos);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value = "type", defaultValue = "") String type, @RequestParam(value = "categories", defaultValue = "*")String[] categories, @RequestParam(value="kudos", defaultValue = "0") int kudos){
        log.info("Accediendo a algunos posts con paginacion");
        Map<String, Object> responseAll = this.postService.all(page, 24, type, categories, kudos);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"search","search/"})
    public ResponseEntity<Map<String, Object>> search(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value = "search", defaultValue = "")String search){
        log.info("Accediendo a busqueda");
        Map<String, Object> responseAll = this.postService.search(page, 24, search);
        return ResponseEntity.ok(responseAll);
    }

    @PostMapping(value = {"/create", "create"})
    public ResponseEntity<?> postCreate(@RequestBody PostRequest postRequest){
        System.out.println("\u001B[32m" + postRequest + "\u001B[0m");
        postService.create(postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @PostMapping(value = {"delete", "delete/"})
    public ResponseEntity<?> postDelete(@RequestBody DeleteRequest postDeleteRequest){

        if(userService.find(postDeleteRequest.getUser()).getAccessLevel().getName().equals("admin")){
            postService.delete(postDeleteRequest.getToDelete());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
