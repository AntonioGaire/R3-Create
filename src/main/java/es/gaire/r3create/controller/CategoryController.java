package es.gaire.r3create.controller;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.domain.Post;
import es.gaire.r3create.service.CategoryService;
import es.gaire.r3create.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(value = {"","/"})
    public List<Category> all(){
        log.info("Accediendo a todos los categorys");
        return this.categoryService.all();
    }

    @GetMapping("/{id}")
    public Category find(@PathVariable("id") Long id) { return this.categoryService.find(id);}
}
