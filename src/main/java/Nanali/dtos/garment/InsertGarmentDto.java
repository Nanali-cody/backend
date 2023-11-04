package Nanali.dtos.garment;

import Nanali.domain.cody.Category;
import Nanali.domain.cody.cloth.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertGarmentDto {

    private Category category;
    private Sex sex;
    private Double temp_from;
    private Double temp_to;
    private Double uv_from;
    private Double uv_to;
    private Double rain_from;
    private Double rain_to;
}