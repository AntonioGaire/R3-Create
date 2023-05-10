package es.gaire.r3create.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "post")

@Entity
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private long idPost;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "recompensa")
    private BigDecimal recompensa;

    @Column(name = "kudos")
    private BigInteger kudos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_posttype", nullable = false, foreignKey = @ForeignKey(name = "FK_POSTTYPE"))
    private PostType postType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="post_category",
            joinColumns = @JoinColumn(name = "id_post", referencedColumnName = "id_post"),
            inverseJoinColumns = @JoinColumn(name= "id_category", referencedColumnName = "id_category"))
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_POST_USER"))
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

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
