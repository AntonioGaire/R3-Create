package es.gaire.r3create.service;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.exception.CategoryNotFoundException;
import es.gaire.r3create.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> all(){
        return this.categoryRepository.findAll();
    }

    public Category find(Long id) {return this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));}
}
