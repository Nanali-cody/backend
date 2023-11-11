package Nanali.controller;

import Nanali.domain.Member.Member;
import Nanali.domain.Member.Style;
import Nanali.domain.cody.cloth.Outfit;
import Nanali.domain.cody.cloth.Sex;
import Nanali.dtos.outfit.InsertOutfitDto;
import Nanali.dtos.outfit.OutfitRequestDto;
import Nanali.dtos.outfit.OutfitResponseDto;
import Nanali.dtos.weather.OutfitWeatherRequest;
import Nanali.service.MemberService;
import Nanali.service.OutfitService;
import Nanali.service.WeatherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/outfit")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OutfitController {

    private final OutfitService outfitService;
    private final WeatherService weatherService;
    private final MemberService memberService;

    @GetMapping
    public OutfitResponseDto Outfit(@Valid @RequestBody OutfitRequestDto request) throws Exception {
        LocalDateTime time = request.getTime();
        ResponseEntity<Map<String, Map<String, Double>>> weather = weatherService.weather(time);

        Member member = memberService.findById(1L);

        Style style;
        Sex sex = Sex.BOTH;

        if (request.getStyle() == null) {
            style = member.getStyle();
        } else {
            style = request.getStyle();
        }

        if (request.getSex() == null) {
            if(member.isSex()) sex = Sex.MAN;
            else if(!member.isSex()) sex = Sex.WOMAN;
        } else {
            sex = request.getSex();
        }

        Map<String, Double> currentWeather = weatherService.getCurrentWeather(weather.getBody(), request.getTime());

        double temperature = currentWeather.get("temperature");
        double precipitation = currentWeather.get("precipitation");
        double uvIndex = currentWeather.get("uv_index");

        Outfit outfit = outfitService.findOutfit(temperature, precipitation, uvIndex, style, sex);
        String imgUrl = outfit.getImgUrl();

        return new OutfitResponseDto(outfit.getId(), imgUrl, weather);
    }

    @PostMapping
    public void InsertOutfit(@Valid @RequestPart InsertOutfitDto request,
                             @RequestPart MultipartFile outfitImg) {

        OutfitWeatherRequest weather = OutfitWeatherRequest.builder()
                .tempFrom(request.getTempFrom())
                .tempTo(request.getTempTo())
                .uvFrom(request.getUvFrom())
                .uvTo(request.getUvTo())
                .rainFrom(request.getRainFrom())
                .rainTo(request.getRainTo()).build();

        outfitService.save(outfitImg, request.getStyle(), request.getSex(), weather);
    }
}
