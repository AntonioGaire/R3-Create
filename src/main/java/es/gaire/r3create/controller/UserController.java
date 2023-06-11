package es.gaire.r3create.controller;

import es.gaire.r3create.domain.User;
import es.gaire.r3create.request.DeleteRequest;
import es.gaire.r3create.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = {"","/"}, params = {"!top"})
    public List<User> all(){
        log.info("Accediendo a todos los users");
        return this.userService.all();
    }

    @GetMapping(value = {"","/"}, params = {"top"})
    public List<User> top(@RequestParam("top") int top){
        log.info("Accediendo a top categories");
        return this.userService.findTopUsers(top);
    }

//    @GetMapping("/{id}")
//    public User find(@PathVariable("id") Long id) { return this.userService.find(id);}

    @GetMapping("/{username}")
    public User find(@PathVariable("username") String username) { return this.userService.find(username);}

    @PostMapping(value = {"delete", "delete/"})
    public ResponseEntity<?> userDelete(@RequestBody DeleteRequest userDeleteRequest){

        System.out.println("DELETE");

        if(userService.find(userDeleteRequest.getUser()).getAccessLevel().getName().equals("admin")){
            userService.delete(userDeleteRequest.getToDelete());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
