package com.kien.website.service;

import com.kien.website.model.SEOObject;
import com.kien.website.model.post.RealEstate;
import com.kien.website.model.post.Tag;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.RealEstateRepository;
import com.kien.website.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class RealEstateDBInitializer {

    private Logger logger = LoggerFactory.getLogger(RealEstateDBInitializer.class);

    @Autowired
    RealEstateRepository realEstateRepository;

    @Autowired
    LocationRepository locationRepository;

    @PostConstruct
    public void init() {
        logger.info("Initializing Real Estate Object........");
        Tag tag1 = new Tag(
                "Trần Trung Kiên",
                "0868788245",
                "0868788245",
                "0868788245",
                "kien868788@gmail.com",
                "Đông Bắc",
                "Đang giao dịch"
        );


        RealEstate realEstate1 = new RealEstate(
                "Đất FPT 3,7 tỷ đường thông, đã trải nhựa ",
                "Số Lô : 17 \n" +
                        "Số Block : V5-B16 \n" +
                        "Dự Án : FPT \n" +
                        "Giá Bán : 3,7 tỷ đồng \n" +
                        "Nội Dung : Cần bán lô đất V5-B16.17, 90m2 khu Fpt Đà Nẵng, chính chủ, lô kề góc, 2 mặt thoáng, nhìn lệch công viên, trục đường thông, đã trải nhựa.",
               new BigDecimal(1000000000),
                100.00,
                tag1,
                "Nguyễn Tất Thành",
                locationRepository.findById(1l).get(),
                3,
                "3",
                "Sổ đỏ"
        );

        SEOObject seoObject1 = new SEOObject();

        seoObject1.setUrl(UrlUtil.handleTitle(realEstate1.getTitle()));

        realEstate1.setSeoObject(seoObject1);

        realEstateRepository.save(realEstate1);

        Tag tag2 = new Tag(
                "Ngu Như Bò",
                "6969696969",
                "6969696969",
                "6969696969",
                "6969696969@gmail.com",
                "Đông Bắc",
                "Đang giao dịch"
        );


        RealEstate realEstate2 = new RealEstate(
                " Ký Gửi: Đường Đường Kiều Oánh Mậu - Hoà Minh - Đường 7m5, 2 tỷ 8 ",
                "Số Lô : 17 \n" +
                        "Số Block : V5-B16 \n" +
                        "Dự Án : FPT \n" +
                        "Giá Bán : 3,7 tỷ đồng \n" +
                        "Nội Dung : Cần bán lô đất V5-B16.17, 90m2 khu Fpt Đà Nẵng, chính chủ, lô kề góc, 2 mặt thoáng, nhìn lệch công viên, trục đường thông, đã trải nhựa.",
                new BigDecimal(1000000000),
                100.00,
                tag2,
                "Nguyễn Tất Thành",
                locationRepository.findById(1l).get(),
                3,
                "3",
                "Sổ đỏ"
        );

        SEOObject seoObject2 = new SEOObject();

        seoObject2.setUrl(UrlUtil.handleTitle(realEstate2.getTitle()));

        realEstate2.setSeoObject(seoObject2);

        realEstateRepository.save(realEstate2);
    }
}
