package com.kien.website.service;

import com.kien.website.model.Category;
import com.kien.website.model.Location;
import com.kien.website.model.SEOObject;
import com.kien.website.model.post.RealEstate;
import com.kien.website.model.post.Tag;
import com.kien.website.repository.CategoryRepository;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.NewsRepository;
import com.kien.website.repository.RealEstateRepository;
import com.kien.website.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Service
public class DBInitializer {

    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @PersistenceContext
    EntityManager em;

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private RealEstateRepository realEstateRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        RealEstate realEstate = new RealEstate();
        realEstate.setTitle("Nhà 10 tỷ");
        realEstate.setBlock("adsfads");
        realEstate.setLo(3);
        realEstate.setSquare(3434);
        realEstate.setPrice(new BigDecimal(343434));
        realEstate.setStreet("Nguyễn Tất Thành");
        realEstate.setPhapLy("asdfasdfsdafasdfasdfhaskdjfh");
        realEstate.setLocation(quanSonTra);
        realEstate.setCategory(datDuAn);
        realEstate.setDescription("Thong tin mo ta " +
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
        realEstate.setTag(tag);
        SEOObject seoObject = new SEOObject();
        seoObject.setUrl(UrlUtil.handleTitle(realEstate.getTitle()));
        realEstate.setSeoObject(seoObject);
        realEstateRepository.save(realEstate);
    }
}
