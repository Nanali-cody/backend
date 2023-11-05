package Nanali.controller;

import Nanali.domain.Member.Member;
import Nanali.domain.cody.LikeClothes.LikeGarment;
import Nanali.domain.cody.LikeClothes.LikeOutfit;
import Nanali.domain.cody.LikeClothes.LikeStatus;
import Nanali.domain.cody.cloth.Garment;
import Nanali.domain.cody.cloth.Outfit;
import Nanali.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final MemberService memberService;
    private final GarmentService garmentService;
    private final OutfitService outfitService;
    private final LikeOutfitService likeOutfitService;
    private final LikeGarmentService likeGarmentService;

    @PostMapping("/outfit/{outfitId}") // 추천 페이지에서 좋아요 누르기
    public void LikeOutfit(@PathVariable Long outfitId) {
        Member member = memberService.findById(1L);
        Outfit outfit = outfitService.findOne(outfitId);

        LikeOutfit likeOutfit = new LikeOutfit(member, outfit, LikeStatus.LIKE);
        likeOutfitService.save(likeOutfit);
    } // 좋아요 누르면 프론트쪽에서 더 못누르게, 마이페이지에서 수정

    @PatchMapping("/outfit/change") // 마이페이지 좋아요 누르기, 취소
    public void changeOutfit(@RequestBody Long outfitId) {
        likeOutfitService.changeLikeOutfit(outfitId);
    }

    @PostMapping("/garment/{garmentId}") // 추천 페이지에서 좋아요 누르기
    public void LikeGarment(@PathVariable Long garmentId) {
        Member member = memberService.findById(1L);
        Garment garment = garmentService.findById(garmentId);

        LikeGarment likeGarment = new LikeGarment(member, garment, LikeStatus.LIKE);
        likeGarmentService.save(likeGarment);
    } // 좋아요 누르면 프론트쪽에서 더 못누르게, 마이페이지에서 수정

    @PatchMapping("/garment/change") // 마이페이지 좋아요 누르기, 취소
    public void changeGarment(@RequestBody Long garmentId) {
        likeOutfitService.changeLikeOutfit(garmentId);
    }
}
