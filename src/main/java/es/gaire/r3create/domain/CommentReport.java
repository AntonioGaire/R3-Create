package es.gaire.r3create.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "comment_report")

@Entity
@Builder

public class CommentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_report")
    private long idUserReport;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_reporter_user", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENTREPORT_RR_USER"))
    private User reporter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_reported_comment", nullable = false, foreignKey = @ForeignKey(name = "FK_COMMENTREPORT_POST"))
    private Comment reported;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date")
    @JsonIgnore
    private Date creationDate;

    @Column(name = "last_modification_date")
    @JsonIgnore
    private Date lastModificationDate;
}
