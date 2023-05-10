package es.gaire.r3create.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "image")

@Entity
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private long idImage;

    @Column(name = "title")
    private String title;

    @Column(name = "alt")
    private String alt;

    @Column(name = "src")
    private String src;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_post", nullable = false, foreignKey = @ForeignKey(name = "FK_POST_IMAGE"))
    @JsonIgnore
    private Post post;

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
