package com.devh.project.realestate.domain.complex.vo.overview;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Area {
    @Field(name = "exclusive", type = FieldType.Float)
    private float exclusive;
    @Field(name = "supply", type = FieldType.Float)
    private float supply;

    private Area(float exclusive, float supply) {
        this.exclusive = exclusive;
        this.supply = supply;
    }

    public static Area of(float exclusive, float supply) {
        return new Area(exclusive, supply);
    }
}
