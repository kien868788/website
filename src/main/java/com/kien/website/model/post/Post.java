package com.kien.website.model.post;

import com.kien.website.model.SEOObject;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Post {

    public static String defaultPicture = "default-post-home.png";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Type(type = "text")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_MODIFIED", insertable = false,updatable = false)
    @Generated(GenerationTime.ALWAYS)
    @ColumnDefault(value = "CURRENT_DATE")
    private Date lastModified;

    @OneToOne(cascade = CascadeType.ALL)
    private SEOObject seoObject;

    public Post() {}

    public Post(@NotNull String title, String description) {
        this.title = title;
        this.description = description;
    }
}
