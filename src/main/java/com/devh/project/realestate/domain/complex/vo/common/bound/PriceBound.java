package com.devh.project.realestate.domain.complex.vo.common.bound;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PriceBound {
    @Field(name = "min", type = FieldType.Integer)
    private int min;
    @Field(name = "max", type = FieldType.Integer)
    private int max;

    private PriceBound(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static PriceBound of(int min, int max) {
        return new PriceBound(min, max);
    }

    public static PriceBound ofDefault() {
        return new PriceBound(0, 0);
    }
}
