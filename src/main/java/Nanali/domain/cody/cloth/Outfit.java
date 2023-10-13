package Nanali.domain.cody.cloth;

import Nanali.domain.cody.Category;
import Nanali.domain.cody.LikeClothes.LikeOutfit;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Outfit {

    @Id
    @GeneratedValue
    private Long id;

    private String imgName;

    private String imgUrl;

    private Category category;

    @OneToMany(mappedBy = "outfit")
    private List<LikeOutfit> outfitList;
}
