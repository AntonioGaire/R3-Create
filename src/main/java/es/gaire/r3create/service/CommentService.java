package es.gaire.r3create.service;

import es.gaire.r3create.domain.Comment;
import es.gaire.r3create.repository.CommentRepository;
import es.gaire.r3create.request.CommentRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService){
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public List<Comment> all(){return this.commentRepository.findAll();}

    public void create(CommentRequest commentRequest){

        Comment comment;

        if(commentRequest.getIdParentComment()==-1){
            comment = Comment.builder()
                    .user(userService.find(commentRequest.getUsername()))
                    .text(commentRequest.getText())
                    .post(postService.find(commentRequest.getIdPost()))
                    .deleted(false)
                    .creationDate(new Date())
                    .lastModificationDate(new Date())
                    .kudos(BigInteger.ZERO)
                    .build();

        }else{
            comment = Comment.builder()
                    .user(userService.find(commentRequest.getUsername()))
                    .text(commentRequest.getText())
                    .post(postService.find(commentRequest.getIdPost()))
                    .parentComment(commentRepository.find(commentRequest.getIdParentComment()).get())
                    .deleted(false)
                    .creationDate(new Date())
                    .lastModificationDate(new Date())
                    .kudos(BigInteger.ZERO)
                    .build();

        }

        commentRepository.save(comment);
    }
    @Transactional
    public void delete(long comment){ this.commentRepository.delete(comment);}
}
