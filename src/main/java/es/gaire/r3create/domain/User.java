package es.gaire.r3create.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")

@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "username")
    private String username;

    @Column(name = "pfp")
    private String pfp;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Comment> comments;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name ="id_accesslevel", nullable = false, foreignKey = @ForeignKey(name = "FK_ACCESSLEVEL"))
    @JsonIgnore
    private AccessLevel accessLevel;

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
