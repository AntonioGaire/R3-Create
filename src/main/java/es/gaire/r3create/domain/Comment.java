package es.gaire.r3create.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "comment")

@Entity
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private long idComment;

    @Column(name = "kudos")
    private BigInteger kudos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id_comment_sr", nullable = true, foreignKey = @ForeignKey(name = "FK_COMMENT"))
    @JsonIgnore
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_post", nullable = false, foreignKey = @ForeignKey(name = "FK_POST"))
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    @JsonIgnore
    private boolean deleted;

    @Column(name = "creation_date")
    @JsonIgnore
    private Date creationDate;

    @Column(name = "last_modification_date")
    @JsonIgnore
    private Date lastModificationDate;
}
