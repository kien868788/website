package com.kien.website.service;

import com.kien.website.model.Location;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.NewsRepository;
import com.kien.website.repository.RealEstateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    private LocationRepository locationRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initializaition.....");

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

    }
}
