package es.gaire.r3create.controller;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.dto.CategoryDTO;
import es.gaire.r3create.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
}
