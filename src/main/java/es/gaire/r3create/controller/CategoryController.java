package es.gaire.r3create.controller;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.dto.CategoryDTO;
import es.gaire.r3create.request.DeleteRequest;
import es.gaire.r3create.service.CategoryService;
import es.gaire.r3create.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService){
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping(value = {"","/"}, params = {"!top"})
    public List<Category> all(){
        log.info("Accediendo a todos los categories");
        return this.categoryService.all();
    }

    @GetMapping(value = {"","/"}, params = {"top"})
    public List<Category> top(@RequestParam("top") int top){
        log.info("Accediendo a top categories");
        return this.categoryService.topCategories(top);
    }

    @GetMapping(value = {"/dto","/dto/"})
    public List<CategoryDTO> categoryDto(){
        log.info("Accediendo a top categories");
        return this.categoryService.findAllCategoriesWNofPosts();
    }

    @GetMapping("/{id}")
    public Category find(@PathVariable("id") Long id) { return this.categoryService.find(id);}

    @PostMapping(value = {"delete", "delete/"})
    public ResponseEntity<?> categoryDelete(@RequestBody DeleteRequest categoryDeleteRequest){

        if(userService.find(categoryDeleteRequest.getUser()).getAccessLevel().getName().equals("admin")){
            categoryService.delete(categoryDeleteRequest.getToDelete()); //implemented but not used
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
