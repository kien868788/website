package com.kien.website.service;

import com.kien.website.model.SEOObject;
import com.kien.website.model.post.RentHouse;
import com.kien.website.model.post.Tag;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.RentHouseRepository;
import com.kien.website.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class RentHouseDBInitializer {
    private Logger logger = LoggerFactory.getLogger(RealEstateDBInitializer.class);

    @Autowired
    RentHouseRepository rentHouseRepository;

    @Autowired
    LocationRepository locationRepository;

    @PostConstruct
    public void init() {
        logger.info("Initializing Rent House Objects........");
        Tag tag1 = new Tag(
                "Trần Trung Kiên",
                "0868788245",
                "0868788245",
                "0868788245",
                "kien868788@gmail.com",
                "Đông Bắc",
                "Đang giao dịch"
        );

        RentHouse rentHouse1 = new RentHouse(
                " Cho thuê mặt bằng Trần Phú - Đà Nẵng - Giá 15 triệu ",
                "Cho thuê mặt bằng phía trước ngay đường Trần Phú. Diện tích 4m x17\n" +
                        "\n" +
                        "Nhà phù hợp thuê lại kinh doanh.\n" +
                        "\n" +
                        "Khu đông đúc dân cư. Giá cho thuê rẻ.\n" +
                        "\n" +
                        "Gọi ngay 0917 365 882\n" +
                        "\n" +
                        "Vị trí: gần chợ Hàn, gần nhà thờ Con Gà, tuyến đường tập trung đông khách du lịch tại Đà Nẵng. Thích hợp kinh doanh mọi thức hình phục vụ cho du lịch.",

                new BigDecimal(200000000),
                40,
                tag1,
                "Nguyễn Tất Thành",
                locationRepository.findById(4l).get()
        );

        SEOObject seoObject1 = new SEOObject();
        seoObject1.setUrl(UrlUtil.handleTitle(rentHouse1.getTitle()));
        rentHouse1.setSeoObject(seoObject1);
        rentHouseRepository.save(rentHouse1);


        Tag tag2 = new Tag(
                "Chí Phèo",
                "6969696969",
                "6969696969",
                "6969696969",
                "69696969@gmail.com",
                "Đông Bắc",
                "Đang giao dịch"
        );

        RentHouse rentHouse2 = new RentHouse(
                " Căn Hộ cho thuê giá chỉ 2 triệu 8, có wifi, camera, siêu sạch, siêu thoán ",
                "Cho thuê mặt bằng phía trước ngay đường Trần Phú. Diện tích 4m x17\n" +
                        "\n" +
                        "Nhà phù hợp thuê lại kinh doanh.\n" +
                        "\n" +
                        "Khu đông đúc dân cư. Giá cho thuê rẻ.\n" +
                        "\n" +
                        "Gọi ngay 0917 365 882\n" +
                        "\n" +
                        "Vị trí: gần chợ Hàn, gần nhà thờ Con Gà, tuyến đường tập trung đông khách du lịch tại Đà Nẵng. Thích hợp kinh doanh mọi thức hình phục vụ cho du lịch.",

                new BigDecimal(200000000),
                40,
                tag2,
                "Nguyễn Tất Thành",
                locationRepository.findById(4l).get()
        );

        SEOObject seoObject2 = new SEOObject();
        seoObject2.setUrl(UrlUtil.handleTitle(rentHouse2.getTitle()));
        rentHouse2.setSeoObject(seoObject2);
        rentHouseRepository.save(rentHouse2);
    }
}
