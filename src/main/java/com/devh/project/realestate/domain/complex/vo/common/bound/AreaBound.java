package com.devh.project.realestate.domain.complex.vo.common.bound;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AreaBound {
    @Field(name = "min", type = FieldType.Float)
    private float min;
    @Field(name = "max", type = FieldType.Float)
    private float max;

    private AreaBound(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public static AreaBound of(float min, float max) {
        return new AreaBound(min, max);
    }

    public static AreaBound ofDefault() {
        return new AreaBound(0, 0);
    }
}
