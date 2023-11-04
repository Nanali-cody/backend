package Nanali.service;

import Nanali.domain.Member.Member;
import Nanali.domain.Member.Style;
import Nanali.domain.cody.Category;
import Nanali.domain.cody.cloth.Garment;
import Nanali.domain.cody.cloth.Sex;
import Nanali.dtos.weather.GarmentWeatherRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SpringBootTest
public class GarmentServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    GarmentService garmentService;

    @DisplayName("카테고리별 날씨에 맞는 아이템 추출")
    @Test
    public void findGarment() {
        Member member = new Member("loginId", "password", "nickname", "email", true, 20, Style.CASUAL);
        memberService.save(member);

        byte[] fileContent = "Test file content".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("testfile.txt", "testfile.txt", "text/plain", fileContent);

        GarmentWeatherRequest garmentWeatherRequest1 = new GarmentWeatherRequest(0D, 10D, 0D, 1D, 0D, 30D);
        GarmentWeatherRequest garmentWeatherRequest2 = new GarmentWeatherRequest(10D, 20D, 0D, 1D, 0D, 50D);
        GarmentWeatherRequest garmentWeatherRequest3 = new GarmentWeatherRequest(10D, 25D, 0D, 2D, 20D, 100D);
        GarmentWeatherRequest garmentWeatherRequest4 = new GarmentWeatherRequest(10D, 20D, 0D, 3D, 70D, 100D);
        GarmentWeatherRequest garmentWeatherRequest5 = new GarmentWeatherRequest(10D, 14D, 0D, 3D, 80D, 100D);
        GarmentWeatherRequest garmentWeatherRequest6 = new GarmentWeatherRequest(15D, 20D, 0D, 2D, 0D, 50D);

        garmentService.save(multipartFile, Category.TOP, Sex.BOTH, garmentWeatherRequest1);
        garmentService.save(multipartFile, Category.TOP, Sex.BOTH, garmentWeatherRequest2);
        garmentService.save(multipartFile, Category.TOP, Sex.BOTH, garmentWeatherRequest3);
        garmentService.save(multipartFile, Category.TOP, Sex.BOTH, garmentWeatherRequest4);
        garmentService.save(multipartFile, Category.TOP, Sex.BOTH, garmentWeatherRequest5);
        garmentService.save(multipartFile, Category.TOP, Sex.BOTH, garmentWeatherRequest6);

        garmentService.save(multipartFile, Category.SHOES, Sex.BOTH, garmentWeatherRequest1);
        garmentService.save(multipartFile, Category.SHOES, Sex.BOTH, garmentWeatherRequest2);
        garmentService.save(multipartFile, Category.SHOES, Sex.BOTH, garmentWeatherRequest3);
        garmentService.save(multipartFile, Category.SHOES, Sex.BOTH, garmentWeatherRequest4);
        garmentService.save(multipartFile, Category.SHOES, Sex.BOTH, garmentWeatherRequest5);
        garmentService.save(multipartFile, Category.SHOES, Sex.BOTH, garmentWeatherRequest6);

        garmentService.save(multipartFile, Category.OUTER, Sex.BOTH, garmentWeatherRequest1);
        garmentService.save(multipartFile, Category.OUTER, Sex.BOTH, garmentWeatherRequest2);
        garmentService.save(multipartFile, Category.OUTER, Sex.BOTH, garmentWeatherRequest3);
        garmentService.save(multipartFile, Category.OUTER, Sex.BOTH, garmentWeatherRequest4);
        garmentService.save(multipartFile, Category.OUTER, Sex.BOTH, garmentWeatherRequest5);
        garmentService.save(multipartFile, Category.OUTER, Sex.BOTH, garmentWeatherRequest6);

        garmentService.save(multipartFile, Category.PANTS, Sex.BOTH, garmentWeatherRequest1);
        garmentService.save(multipartFile, Category.PANTS, Sex.BOTH, garmentWeatherRequest2);
        garmentService.save(multipartFile, Category.PANTS, Sex.BOTH, garmentWeatherRequest3);
        garmentService.save(multipartFile, Category.PANTS, Sex.BOTH, garmentWeatherRequest4);
        garmentService.save(multipartFile, Category.PANTS, Sex.BOTH, garmentWeatherRequest5);
        garmentService.save(multipartFile, Category.PANTS, Sex.BOTH, garmentWeatherRequest6);

        List<Garment> outers = garmentService.findOuters(10D, 1D, 20D, Sex.BOTH);
        List<Garment> tops = garmentService.findTops(10D, 1D, 20D, Sex.BOTH);
        List<Garment> pants = garmentService.findPants(10D, 1D, 20D, Sex.BOTH);
        List<Garment> shoes = garmentService.findShoes(10D, 1D, 20D, Sex.BOTH);

        // 1, 2번이 출력되어야 함

        for (Garment o : outers) {
            System.out.println("outer = " + o.getImgName());
        }

        for (Garment o : tops) {
            System.out.println("top = " + o.getImgName());
        }

        for (Garment o : pants) {
            System.out.println("pants = " + o.getImgName());
        }

        for (Garment o : shoes) {
            System.out.println("shoes = " + o.getImgName());
        }

        System.out.println("==========================");

        List<Garment> outerList = garmentService.findOuters(20D, 1D, 20D, Sex.BOTH);
        List<Garment> topsList = garmentService.findTops(20D, 1D, 20D, Sex.BOTH);
        List<Garment> pantsList = garmentService.findPants(20D, 1D, 20D, Sex.BOTH);
        List<Garment> shoesList = garmentService.findShoes(20D, 1D, 20D, Sex.BOTH);

        // 1, 5번이 출력되어야 함

        for (Garment o : outerList) {
            System.out.println("outer = " + o.getImgName());
        }

        for (Garment o : topsList) {
            System.out.println("top = " + o.getImgName());
        }

        for (Garment o : pantsList) {
            System.out.println("pants = " + o.getImgName());
        }

        for (Garment o : shoesList) {
            System.out.println("shoes = " + o.getImgName());
        }
    }
}
