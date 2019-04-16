package com.kien.website.model.post;

import com.kien.website.model.SEOObject;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "NEWS")
public class News implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false)
    @Generated(
            GenerationTime.ALWAYS
    )
    private Date lastModified;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SEO_ID",nullable = false)
    private SEOObject seoObject;

    public News() {}

}
