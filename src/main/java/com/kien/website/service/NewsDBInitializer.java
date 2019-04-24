package com.kien.website.service;

import com.kien.website.model.SEOObject;
import com.kien.website.model.post.News;
import com.kien.website.model.post.NewsCategory;
import com.kien.website.repository.LocationRepository;
import com.kien.website.repository.NewsCategoryRepository;
import com.kien.website.repository.NewsRepository;
import com.kien.website.util.UrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class NewsDBInitializer {
    private Logger logger = LoggerFactory.getLogger(RealEstateDBInitializer.class);

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    NewsCategoryRepository newsCategoryRepository;


    @PostConstruct
    public void init() {
        /* Initilization of News Category */
        NewsCategory dauTuDuAn = new NewsCategory("Thông Tin Đầu Tư Dự Án");
        NewsCategory phongThuyTheoTuoi = new NewsCategory("Phong Thủy Theo Tuổi");
        newsCategoryRepository.save(dauTuDuAn);
        newsCategoryRepository.save(phongThuyTheoTuoi);
        logger.info("Initializing News Objects........");

        News news1 = new News(
                "Các dự án đất nền Sun Group tại Đà Nẵng thu hút nhà đầu tư cỡ nào ",
                " Đất nền luôn là kênh đầu tư vua trong lĩnh vực bất động sản. Chính vì vậy dự án đất nền Sun Group tại Đà Nẵng đang làm nên cơn sốt mới cho các nhà đầu tư tầm cỡ. Cùng Danangland cập nhật những tin tức các dự án nhà đất sunland Đà Nẵng mới nhất.\n" +
                        "\n" +
                        "    The Sun Avenue – Thông tin giá mua bán căn hộ The Sun Avenue Q2, TPHCM\n" +
                        "    Mua bán đất vườn Đà Nẵng giá rẻ lên ngôi thu hút nhà đầu tư\n" +
                        "    【CHỦ ĐẦU TƯ】Risemount Apartment Đà Nẵng, Căn hộ trong mơ giá Chiết Khấu\n" +
                        "\n" +
                        "Nội dung bài viết\n" +
                        "\n" +
                        "    Giới thiệu về chủ đầu tư Sun Group\n" +
                        "    Các dự án đất nền Sun Group tại Đà Nẵng thu hút nhà đầu tư\n" +
                        "        Tiện ích dịch vụ nổi bật\n" +
                        "        Giá cả hợp lý\n" +
                        "    Một số dự án đất nền Sun Group tại Đà Nẵng nổi bật\n" +
                        "        Khu đất nền dự án KĐT Nam Hòa Xuân Đà Nẵng\n" +
                        "        Đất nền dự án KĐT Nam Cầu Nguyễn Tri Phương\n" +
                        "        Đất nền dự án KĐT Nguyễn Chí Công\n" +
                        "\n" +
                        "Có thể nói những năm gần đây bất động sản đã và đang là lĩnh vực thu hút nhiều nhà đầu tư nhất. Bởi lẽ, việc đầu tư bất động sản ít xảy ra rủi ro và có giá trị sinh lời cao. Trong đó các dự án đất nền Sun Group Đà Nẵng đang làm mưa làm gió trên thị trường. Vậy nơi đây thu hút nhà đầu tư tầm cỡ nào? Nếu bạn thắc mắc về vấn đề này, hãy cùng tìm lời giải đáp ngay bây giờ nhé.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Đất nền Sun Group thu hút nhà đầu tư trong và ngoài nước\n" +
                        "\n" +
                        "Đất nền Sun Group thu hút nhà đầu tư trong và ngoài nước\n" +
                        "Giới thiệu về chủ đầu tư Sun Group\n" +
                        "\n" +
                        "\n" +
                        "Trước khi tìm hiểu lời giải đáp cho câu hỏi đất nền dự án Sun Group Đà Nẵng thu hút nhà đầu tư tầm cỡ nào? Bạn hãy cùng điểm qua đôi nét về chủ đầu tư Sun Group và các chi nhánh của công ty. Điều này sẽ giúp bạn có cái nhìn chân thực hơn về các dự án đất nền. Hơn nữa còn có cho mình nhiều thông tin bổ ích để nhận định sự quan tâm của nhà đầu tư.\n" +
                        "\n" +
                        "Cụ thể, Sun Group được thành lập vào năm 2007, kinh doanh trên nhiều lĩnh vực. Trong đó lĩnh vực bất động sản được xem là chủ chốt nhất mà công ty hướng đến. Tính cho đến thời điểm hiện tại, Sun Group đã đóng góp hàng loạt dự án tầm cỡ quốc tế. Trong đó bao gồm như InterContinental Danang Sun Peninsula Resort, Công viên Đại Dương Hạ Long. Hoặc là Bà Nà Hills, cáp treo Fansipan, Premier Village Danang Resort,…Nhiều công trình bất động sản Sun Group được so sánh như những kiệt tác thiên đường giữa đời thực.\n" +
                        "\n" +
                        "Chính vì vậy mà đến nay Sun Group đã lập ra nhiều thành viên, chi nhánh phân bố trên thị trường. Một trong số đó chính là SunLand – công ty thành viên được thành lập vào năm 2009. Nơi đây tập hợp các dịch vụ tiêu biểu liên quan về BĐS, đất dự án Sunland hay Sun Group.\n" +
                        "Các dự án đất nền Sun Group tại Đà Nẵng thu hút nhà đầu tư\n" +
                        "\n" +
                        "Theo như thống kê đất Sun Group Đà Nẵng phân bố rất nhiều trên thị trường. Hầu như phân khu đất nào cũng đều thu hút được một lượng lớn nhà đầu tư trong và ngoài nước. Hơn nửa số tiền đầu tư cũng vượt mức yêu cầu đề ra. Điều này hình thành là do các phân khu đất nền mà Sun Group hội tụ nhiều ưu điểm hấp dẫn. Trong đó tiêu biểu là những ưu điểm sau đây:\n" +
                        "\n" +
                        "Vị trí địa lý hoàn hảo\n" +
                        "\n" +
                        "Ưu điểm đầu tiên làm cho các phân khu đất nơi đây trở nên hoàn hảo và thu hút nhà đầu tư chính là vị trí. Vị trí mà đất nền sở hữu hội đầy đủ những yếu tố vàng đắc địa.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Đất nền đẹp mắt của Sun Group\n" +
                        "Đất nền đẹp mắt của Sun Group\n" +
                        "\n" +
                        "\n" +
                        "Đầu tiên, đất nền đều được bao quanh bởi hệ thống giao thông hiện đại. Các con đường được thiết kế nâng cấp, tân trang và tích hợp cơ sở hạ tầng đầy đủ. Nhiều tuyến đường kết nối với nhau một cách hoàn thiện và hạn chế tình trạng tắc đường khi di chuyển. Đặc biệt vào mùa đông – mùa mưa gió về hệ thống đường không bị ngập nước hay xuất hiện ổ gà. Vì thế quá trình di chuyển nơi đây cũng trở nên an toàn và tiện lợi hơn.\n" +
                        "\n" +
                        "Mặt khác nhiều dự án Sunland Đà Nẵng còn nằm gần nhiều công trình quan trọng của thành phố. Trong đó chẳng hạn như sân bay, khu trung tâm văn hóa, kinh tế, xã hội,…Hoặc là nhiều công ty, xí nghiệp, xưởng sản xuất phát triển. Vì vậy mọi người có thể di chuyển đến các công trình nhanh chóng chỉ trong vòng ít phút.\n" +
                        "\n" +
                        "Hơn nữa, nhiều dự án đất nền còn nằm gần biển và hệ thống cây xanh bốn bề bát ngát. Hệ thống dân cư nhộn nhịp, thân thiện có đời sống phát triển. Bởi thế không gian nơi đây vô cùng thanh tịnh, mát mẻ và gần gũi với thiên nhiên xanh mát. Nhiều địa điểm còn gần với hệ thống khu du lịch nổi tiếng nên tiềm năng phát triển là rất lớn.\n" +
                        "Tiện ích dịch vụ nổi bật\n" +
                        "\n" +
                        "Bên cạnh vị trí đẹp mắt thì đất nền dự án Sunland Sun Group tại Đà Nẵng còn sở hữu tiện ích nổi bật. Hệ thống các phân khu đất được phân bố trong nhiều dự án nghỉ dưỡng, khu đô thị, chung cơ danh tiếng. Vì thế đất nền nơi đây sở hữu nhiều tiện ích dịch vụ nổi bật. Trong đó chẳng hạn như:\n" +
                        " \n" +
                        "\n" +
                        "Khu công viên xanh mát\n" +
                        "Hầu hết trong các dự án bất động sản của Sun Group Sunland Đà Nẵng mở bán đều tích hợp hệ thống cảnh quan xanh rộng lớn. Vì thế phân khu đất nền luôn mang trọn hương vị thanh mát của thiên nhiên. Không khí mát mẻ cho những ngày hè nóng nực và là nơi giúp con người có thể hòa mình vào thiên nhiên.\n" +
                        "\n" +
                        "Khu tiện ích hiện đại\n" +
                        "Phân khu tiện ích hiện đại bao gồm các công trình như bể bơi, khu spa làm đẹp, khu tập gym. Một số dự án đất nền của Sunland Đà Nẵng còn có khu du thuyền hấp dẫn, khu BBQ hay khu thác nước. Cùng với đó là khu vui chơi giải trí, khu nhà hàng, shophouse tuyệt đẹp,…\n" +
                        "\n" +
                        "Công trình nâng cấp hoàn thiện\n" +
                        "Bên cạnh 2 yếu tố trên, một số dự án của công ty còn được bao trọn bởi công trình hiện đại. Trong đó ví dụ như trường học từ mầm non đến THPT, khu trung tâm mua sắm, khu công ty văn phòng,...\n" +
                        "Giá cả hợp lý\n" +
                        "\n" +
                        "Ngoài ra một ưu điểm hoàn thiện thu hút sự quan tâm của nhà đầu tư là giá đất dự án Sun Group rất hợp lý. Theo như phân tích thì mức giá đất nền tại đây không quá cao như một số đất nền khác. Hơn nữa khi đầu tư đất nền khả năng sinh lời lại vô cùng vượt trội. Nhiều chuyên gia còn khẳng định rằng đất nền của Sun Group như gà đẻ trứng vàng. Vì thế mỗi năm qua đi, đặc biệt là những năm gần đây đất nền Sun Group luôn có mức đầu tư cao.\n" +
                        "Một số dự án đất nền Sun Group tại Đà Nẵng nổi bật\n" +
                        "\n" +
                        "Hiện tại có rất nhiều đất nền tại Đà Nẵng được Sun Group cũng như Sunland rao bán. Tuy nhiên đa phần các chủ đầu tư thường tập trung vào 4 dự án bán đất Sun Group sau đây:\n" +
                        "Khu đất nền dự án KĐT Nam Hòa Xuân Đà Nẵng\n" +
                        "\n" +
                        "KĐT Nam Hòa Xuân Đà Nẵng nằm trong khu phát triển bậc nhất. Nơi đây có thể kết nối được với nhiều điểm du lịch như khu du lịch Ngũ Hành Sơn, chùa Linh Ứng, Bà Nà Hills,…\n" +
                        "\n" +
                        "Hơn nữa dự án còn có 4 mặt tiếp giáp sông và kết nối với khu sinh thái Hòa Xuân. Nên phân khúc dự án hưởng trọn tầm view đẹp mặt, không gian thanh mát giữa lòng thành phố. Vì vậy nên phân khu đất nền nơi đây hội tụ đầy đủ những lợi thế hấp dẫn mà ít nơi nào có được.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Đất nền KĐT Nam Cầu Nguyễn Tri Phương\n" +
                        "\n" +
                        "Đất nền KĐT Nam Cầu Nguyễn Tri Phương\n" +
                        "Đất nền dự án KĐT Nam Cầu Nguyễn Tri Phương\n" +
                        "\n" +
                        "KĐT này có tổng diện tích lên đến 150 ha và được bao bọc bởi dòng sông Cẩm Lệ - Cổ Cò. Nơi đây là khu đô thị hiện đại với đầy đủ nhà phố, bệnh viện, trường học, khu sinh thái. Cùng với đó là hệ thống trung tâm mua sắm, khu tắm bùn suối nước nóng,…\n" +
                        "\n" +
                        "Mặt khác KĐT còn được quy hoạch đồng bộ có tính pháp lý hoàn thiện. Vì thế nên hệ thống các phân khu đất nền nơi đây đã được cấp sổ đỏ. Hơn nữa các dịch liên quan về mua bán, chuyển nhượng cũng được thực hiện nhanh chóng và dễ dàng.\n" +
                        "Đất nền dự án KĐT Nguyễn Chí Công\n" +
                        "\n" +
                        "Phân khu đất nền thuộc dự án KĐT Nguyễn Chí Công vẫn sôi động như bao giờ hết. Nơi đây hưởng trọn được những gì đẹp đẽ nhất mà thiên nhiên trao tặng. Hơn nữa còn được đầu tư kỹ lưỡng bởi Sun Group và nhà đất Sunland Đà Nẵng chịu trách nhiệm.\n" +
                        "\n" +
                        "Xem thêm các dự án tại:Các condotel đang mở bán tại Hội An với giá ưu đãi\n" +
                        "Đây là khu đô thị xanh được nhiều nhà đầu tư đánh giá rất cao về tiềm lực sinh lời. Giá đất nền hấp dẫn so với các dự án trước kia và có tiềm năng tăng giá rõ rệt trong tương lai. Vì thế dù là đợt bán nào thì đất nền nơi đây vẫn thu hút lượng lớn nhà đầu tư.\n" +
                        "\n" +
                        "Vậy câu hỏi các dự án đất nền Sun Group tại Đà Nẵng thu hút nhà đầu tư cỡ nào đã được giải mã. Mong rằng thông qua bài viết, bạn sẽ nắm gọn được những thông tin bổ ích cho mình.\n"
        );


        SEOObject seoObject1 = new SEOObject();
        seoObject1.setUrl(UrlUtil.handleTitle(news1.getTitle()));
        news1.setSeoObject(seoObject1);
        dauTuDuAn.add(news1);

        News news2 = new News(
                " Cập nhập những thông tin nhà đất Đà Nẵng tháng 2/2019: Đất dự án, căn hộ lên ngôi ",
                " Danangland.org sẽ cung cấp những Thông tin nhà đất Đà Nẵng mới nhất tháng 2/2019 để những ai có ý định mua bán, đầu tư nhà đất, căn hộ, đất nền sẽ có những quyết định chính xác cho mình.\n" +
                        "\n" +
                        "    Thông tin chi tiết dự án căn hộ condotel Malibu Hội An Quảng Nam\n" +
                        "    Nên mua đất Đà Nẵng ở đâu để đầu tư an cư tốt nhất\n" +
                        "    Thông tin mua bán các dự án ShopHouse tại Đà Nẵng mới nhất\n" +
                        "\n" +
                        "Có thể nói tình hình bất động sản Đà Nẵng trong những năm qua từ 2017, 2018, 2019 có rất nhiều những diễn biến khá phức tạp. Từ giá tăng vèo vèo cho đến chững lại rồi vỡ bong bóng. Những nhà đầu tư bất động sản, những ai thực sự có kinh nghiệm thì mới đứng vững được trên thị trường hiện giờ. Bên cạnh những tiêu cực về các vụ bê bối giấy tờ, sổ đỏ, giả công văn chính quyền, sàn giao dịch “ bẻ kèo” …. Thì cạnh đó cũng có những khởi sắc nhất định những ai có tài nhận định, nắm tin bất động sản Đà Nẵng rõ và kiên định thì có những đầu tư đúng đắn đem lại lợi nhuận cao từ trăm triệu cho đến hàng tỷ đồng. Hôm nay, Danangland.org sẽ cung cấp những Thông tin nhà đất Đà Nẵng mới nhất tháng 2/2019 để những ai có ý định mua bán, đầu tư nhà đất, căn hộ, đất nền sẽ có những quyết định chính xác cho mình.\n" +
                        "\n" +
                        "\n" +
                        "Thông tin đất dự án, Căn hộ tháng 2/2019\n" +
                        " \n" +
                        "\n" +
                        "Nếu bạn có ý định tìm hiểu đất dự án Đà Nẵng thì đây là những tin tức không thể bỏ qua. Có rất nhiều dự án tại Đà Nẵng được mở bán trong tháng 2 này bao gồm:\n" +
                        "\n" +
                        "\n" +
                        "-    PGT tung sản phẩm của dự án New Đà Nẵng City tại quận Liên Chiểu  với ước tính mỗi lô tầm 1 đến 1,8 tỷ bên canh trường đại học Duy Tân. Với vị thế đắc địa trung tâm thành phố  đây dự định sẽ là điểm nóng trong những tháng đầu năm 2019.\n" +
                        "\n" +
                        "Tổng quan dự án New Danang City\n" +
                        "\n" +
                        "Tiến độ dự án New City Đà Nẵng\n" +
                        "\n" +
                        "\n" +
                        "-    Đất dự án Golden Hills city thuộc phường Hòa Hiêp Nam, Liên Chiểu, Đà Nẵng đây cũng là tiêu điểm không kém cạnh sức nóng của New city.\n" +
                        "\n" +
                        "Phối cảnh dự án Golden Hills city Đà Nẵng\n" +
                        "\n" +
                        "\n" +
                        "Dự án Golden Hills city Đà Nẵng\n" +
                        "\n" +
                        "\n" +
                        "-    Tiếp đến là dự án của Sunland mở bán block mới khu nam Hòa Xuân, Võ Chí Công nối dài với mức chiếc khấu hơn 8%.\n" +
                        "\n" +
                        "-    Tiếp theo là dự án đất nền khu đô thị Phước Lý thuộc quận Cẩm Lệ cũng đang thu hút sự chú ý của rất nhiều nhà đầu tư.\n" +
                        "\n" +
                        "Nhiều tiện ích đất khu Phước Lý Đà Nẵng\n" +
                        " \n" +
                        "\n" +
                        "Và rất nhiều  đất dự án Đà Nẵng khác đã và đang mở bán được cập nhập chi tiết tại Danangland.org quý vị có thể tìm hiểu những thông tin thị trường bất động sản Đà Nẵng tại đây. Cũng như đăng tin rao bán, hoặc tìm mua những đất nền, căn hộ của cá nhân hoặc tổ chức.\n" +
                        "\n" +
                        "Mặt khác nếu bạn đang tìm các thông tin về  căn hộ Đà Nẵng thì chúng tôi cũng cập nhật một số tin tức đáng chú ý cụ thể như sau. Đà Nẵng – một thành phố được mệnh danh là đáng sống nhất tại Việt Nam, hằng năm thu hút rất nhiều người dân tại các tỉnh thành khác tới đây để định cư sinh sống, thậm chí là những người nước ngoài. Nên các căn hộ tại Đà Nẵng ngày càng mọc lên rất nhiều đáp ứng nhu cầu định cư sinh sống của nhiều người. Nên sức hút từ các dư án căn hộ Đà Nẵng luôn được các nhà đầu tư, mua bán bất động sản quan tâm. Các dự án căn hộ đã và đang được mở bán trong tháng 2 tại Đà Nẵng bao gồm:\n" +
                        "\n" +
                        "Dành cho giới “ thượng lưu” một tý thì dự án của Homeland Paradise Villa  đây là dự án về các căn hộ biệt thự nghỉ dưỡng ven sông. Các nhà đầu tư nhận định đây sẽ là một trong số các dự án tạo nên cơn sốt trên thị trường Đà Nẵng bởi vị thế vô cùng thuận lợi khi  mà mặt tiền của chúng là view sông cổ Cò và sở hữu lên đến 2 hồ sinh thái rộng trên 25 ha.\n" +
                        "\n" +
                        "\n" +
                        "Homeland Paradise Villa dành cho giới thượng lưu\n" +
                        "\n" +
                        "\n" +
                        "-    Tiếp là dự án của Singapore complex city, hẳn ai có mơ ước Đà Nẵng thành một Singapore trong mơ hẳn không thể bỏ sốt dự án này vì với tiêu chí cuộc sống tiện nghi, trong lành, văn minh, không rác thải, thân thiện với môi trường khu dành cho những người tri thức cao đây sẽ là điểm nhấn trong thời gian tới. Các căn hộ nằm trên tuyến đường sông du lịch Đà Nẵng – Hội An hứa hẹn sẽ thu hút nhiều người tìm mua và khai thác khi là khu đô thị xanh kiểu mẫu.\n" +
                        "\n" +
                        "Có một sự lựa chọn với mứ giá thấp hơn so với 2 dự án trên là  dự án căn hộ cao cấp Monarchy, dự án One River, dự án Condotel T & T Twin Tower, dự án Risemount Apartment, dự án Ocean View…. Thông tin vào những dự án căn hộ này cập nhập đầy đủ tại Dannangland.org.\n" +
                        "\n" +
                        "Ngoài ra các căn hộ Đà Nẵng cá nhân, nhỏ lẻ khác được rao bán hằng ngày đều được chúng tôi cập nhật chi tiết về diện tích, giá tiền để quý khách  có thể tiếp cận một cách nhanh nhất với mức giá phù hợp nhất. Các khu chung cư là một tâm điểm cho những người có thu nhập khá mong muốn một cuộc sống tiện ích đơn giản cũng được rao bán tại đây.\n" +
                        "\n" +
                        "Cùng đến với Danangland.org để tiếp cận các tin rao vặt mua bán đất nền, căn hộ đồng thời có những thông tin chính xác nhất về thị trường bất động sản từng gian đoạn , từng tháng, từng ngày nhanh nhất. Chúng tôi hân hạnh là đơn vị cụ thể hóa những giấc mơ đầu tư, định cư là nguồn cảm hứng cho những nhà đầu tư có chiến lược, tầm nhìn. Trong những giai đọan biến động nhiều về thị trường bất động sản trong thời gian gần đây thì việc tiếp cận các thông tin bất động sản Đà Nẵng là điều cần thiết để đưa ra những quyết định sáng suốt cho bạn. \n"
        );

        SEOObject seoObject2 = new SEOObject();
        seoObject2.setUrl(UrlUtil.handleTitle(news2.getTitle()));
        news2.setSeoObject(seoObject2);
        phongThuyTheoTuoi.add(news2);


        newsRepository.save(news1);
        newsRepository.save(news2);
    }
}
