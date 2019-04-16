package com.kien.website.model.post;

import com.kien.website.model.Category;
import com.kien.website.model.Location;
import com.kien.website.model.SEOObject;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "RealEstate")
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_MODIFIED", insertable = false,updatable = false)
    @Generated(GenerationTime.ALWAYS)
    @ColumnDefault(value = "CURRENT_DATE")
    private Date lastModified;

    private BigDecimal price;

    private double square;

    @Type(type = "text")
    private String description;

    private Tag tag;

    private String street;

    private int lo;

    private String block;

    private String phapLy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SEO_ID",nullable = false)
    private SEOObject seoObject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID",nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID",nullable = false)
    private Category category;

    public RealEstate() {
    }

}

