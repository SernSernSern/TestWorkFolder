package com.simple.test.work.TestWork.dto;
import lombok.*;

import org.springframework.hateoas.RepresentationModel;


@Data
public class IngredientDTO extends RepresentationModel<IngredientDTO> {

    private Long id;
    private String name;
    private Integer amount;

}
