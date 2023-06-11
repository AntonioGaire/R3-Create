package es.gaire.r3create.controller;


import es.gaire.r3create.domain.Comment;
import es.gaire.r3create.request.CommentRequest;
import es.gaire.r3create.request.DeleteRequest;
import es.gaire.r3create.service.CommentService;
import es.gaire.r3create.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService){
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping(value = {"/create", "create"})
    public ResponseEntity<?> commentPost(@RequestBody CommentRequest commentRequest){

        System.out.println("\u001B[32m" + commentRequest + "\u001B[0m");

        commentService.create(commentRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = {"", "/"})
    public List<Comment> all(){
        System.out.println("\u001B[32m" + "COMENTARIOS" + "\u001B[0m");
        return this.commentService.all();
    }

    @PostMapping(value = {"delete", "delete/"})
    public ResponseEntity<?> commentDelete(@RequestBody DeleteRequest commentDeleteRequest){

        if(userService.find(commentDeleteRequest.getUser()).getAccessLevel().getName().equals("admin")){
            commentService.delete(commentDeleteRequest.getToDelete());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
