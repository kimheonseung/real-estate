package com.devh.project.realestate.domain.complex.vo.overview;

import com.devh.project.realestate.domain.complex.vo.common.bound.FloorBound;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Building {
    @Field(name = "id", type = FieldType.Keyword)
    private String id;
    @Field(name = "name", type = FieldType.Keyword)
    private String name;
    private FloorBound floorBound;
    @Field(name = "sort", type = FieldType.Keyword)
    private String sort;

    private Building(String name, String id, FloorBound floorBound, String sort) {
        this.name = name;
        this.id = id;
        this.floorBound = floorBound;
        this.sort = sort;
    }

    public static Building of(String name, String id, FloorBound floorBound, String sort) {
        return new Building(name, id, floorBound, sort);
    }
}
