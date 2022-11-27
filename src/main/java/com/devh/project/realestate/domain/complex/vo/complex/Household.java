package com.devh.project.realestate.domain.complex.vo.complex;

import com.devh.project.realestate.domain.complex.vo.common.bound.FloorBound;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Household {
    @Field(name = "total", type = FieldType.Integer)
    private int total;
    @Field(name = "buildings", type = FieldType.Integer)
    private int buildings;
    private FloorBound floorBound;

    private Household(int total, int buildings, FloorBound floorBound) {
        this.total = total;
        this.buildings = buildings;
        this.floorBound = floorBound;
    }

    public static Household create(int total, int buildings, FloorBound floorBound) {
        return new Household(total, buildings, floorBound);
    }
}
