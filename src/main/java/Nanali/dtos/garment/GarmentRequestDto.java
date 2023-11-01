package Nanali.dtos.garment;

import Nanali.domain.Member.Style;
import Nanali.domain.cody.Category;
import Nanali.domain.cody.cloth.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GarmentRequestDto {

    private Long temp;
    private Long uv;
    private Long rain;

    private Category category;

    private Sex sex;
}
