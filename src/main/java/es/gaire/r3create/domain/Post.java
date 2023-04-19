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

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private long idPost;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "recompensa")
    private BigDecimal recompensa;

    @Column(name = "kudos")
    private BigInteger kudos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_posttype", nullable = false, foreignKey = @ForeignKey(name = "FK_POSTTYPE"))
    private PostType postType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="post_category",
            joinColumns = @JoinColumn(name = "id_post", referencedColumnName = "id_post"),
            inverseJoinColumns = @JoinColumn(name= "id_category", referencedColumnName = "id_category"))
    Set<Category> categories;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_POST_USER"))
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;
}
