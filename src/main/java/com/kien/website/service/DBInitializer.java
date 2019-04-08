package com.kien.website.service;

import com.kien.website.model.Category;
import com.kien.website.model.Location;
import com.kien.website.model.SEOObject;
import com.kien.website.model.post.Post;
import com.kien.website.model.post.Tag;
import com.kien.website.model.user.User;
import com.kien.website.repository.CategoryRepository;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.UserRepository;
import com.kien.website.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DBInitializer {

    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @PersistenceContext
    EntityManager em;

    private NewsService newsService;
    private PostService postService;
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DBInitializer(NewsService newsService, PostService postService, CategoryRepository categoryRepository) {
        this.newsService = newsService;
        this.postService = postService;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initializaition.....");

        /* Initilization of Category */
        Category datDuAn = new Category("Mua Bán Nhà Đất Đà Nẵng");
        categoryRepository.save(datDuAn);
        Category batDongSan = new Category("Cho Thuê Nhà Đất Đà Nẵng");
        categoryRepository.save(batDongSan);

        /* Initilization of Location */
        Location quanHaiChau = new Location("Quận Hải Châu");
        locationRepository.save(quanHaiChau);
        System.err.println("ID first : " + quanHaiChau.getId());
        Location quanCamLe = new Location("Quận Cẩm Lệ");
        locationRepository.save(quanCamLe);
        Location quanThanhKhe = new Location("Quận Thanh Khê");
        locationRepository.save(quanThanhKhe);
        Location quanLienChieu = new Location("Quận Liên CHiểu");
        locationRepository.save(quanLienChieu);
        Location quanNguHanhSon = new Location("Quận Ngũ Hành Sơn");
        locationRepository.save(quanNguHanhSon);
        Location quanSonTra = new Location("Quận Sơn Trà");
        locationRepository.save(quanSonTra);
        Location huyenHoaVang = new Location("Huyện Hòa Vang");
        locationRepository.save(huyenHoaVang);
        Post post = new Post();
        post.setTitle("Nhà 10 tỷ");
        post.setBlock("adsfads");
        post.setLo(3);
        post.setSquare(3434);
        post.setPrice(new BigDecimal(343434));
        post.setStreet("Nguyễn Tất Thành");
        post.setPhapLy("asdfasdfsdafasdfasdfhaskdjfh");
        post.setLocation(quanSonTra);
        post.setCategory(datDuAn);
        post.setDescription("Thong tin mo ta " +
                "adsfá" +
                "dfá" +
                "df" +
                "sdf" +
                "sdf" +
                "sda" +
                "f" +
                "sadf" +
                "asdfasdfdsfdsf");
        Tag tag = new Tag();
        tag.setDirection("Đông Bắc");
        tag.setStatus("Đang giao dịch");
        tag.setName("Kiên");
        tag.setPhonenumber("0868788245");
        tag.setZalo("0868788245");
        tag.setSkype("kien868788@gmail.com");
        tag.setEmail("kien868788@gmail.com");
        post.setTag(tag);
        SEOObject seoObject = new SEOObject();
        seoObject.setUrl(UrlUtil.handleTitle(post.getTitle()));
        post.setSeoObject(seoObject);
        postService.save(post);
    }
}
