package es.gaire.r3create.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "category")

@Entity

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private long idCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    List<Post> posts = new ArrayList<>();

    @Column(name = "creation_date")
    @JsonIgnore
    private Date creationDate;

    @Column(name = "last_modification_date")
    @JsonIgnore
    private Date lastModificationDate;

}
