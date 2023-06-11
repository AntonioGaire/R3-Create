package es.gaire.r3create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    private String text;
    private String username;
    private long idPost;
    private long idParentComment;
}
