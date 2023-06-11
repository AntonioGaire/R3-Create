package es.gaire.r3create.service;

import es.gaire.r3create.domain.Category;
import es.gaire.r3create.dto.CategoryDTO;
import es.gaire.r3create.exception.CategoryNotFoundException;
import es.gaire.r3create.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Category> topCategories(int top) { return this.categoryRepository.findTopCategories(top);}

    public Category find(Long id) {return this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));}

    public List<CategoryDTO> findAllCategoriesWNofPosts(){

        List<CategoryDTO> aux = new ArrayList<>();
        this.categoryRepository.findAllCategoriesWNofPosts().forEach(t -> aux.add(new CategoryDTO((Category) t[0], (int) t[1])));
        return aux;

    }

    @Transactional
    public void delete(long category){categoryRepository.delete(categoryRepository.findById(category).get());}
}
