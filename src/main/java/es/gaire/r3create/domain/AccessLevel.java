package es.gaire.r3create.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "accesslevel")

@Entity
@Builder
public class AccessLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_accesslevel")
    private long idAccessLevel;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "accessLevel", fetch = FetchType.EAGER)
    private List<User> users;

    @Column(name = "deprecated", columnDefinition = "boolean default false")
    private Boolean deprecated;

    @Column(name = "creation_date")
    @JsonIgnore
    private Date creationDate;

    @Column(name = "last_modification_date")
    @JsonIgnore
    private Date lastModificationDate;
}
