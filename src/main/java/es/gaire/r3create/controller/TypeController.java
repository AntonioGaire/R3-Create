package es.gaire.r3create.controller;

import es.gaire.r3create.domain.PostType;
import es.gaire.r3create.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @GetMapping(value = {"","/"})
    public List<PostType> all(){
        log.info("Accediendo a todos los types");
        return this.typeService.all();
    }

    @GetMapping("/{id}")
    public PostType find(@PathVariable("id") Long id) { return this.typeService.find(id);}
}
