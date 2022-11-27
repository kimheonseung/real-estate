package com.devh.project.realestate.domain.complex.vo.common.bound;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FloorBound {
    @Field(name = "low", type = FieldType.Integer)
    private int low;
    @Field(name = "high", type = FieldType.Integer)
    private int high;

    private FloorBound(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public static FloorBound of(int low, int high) {
        return new FloorBound(low, high);
    }
}
