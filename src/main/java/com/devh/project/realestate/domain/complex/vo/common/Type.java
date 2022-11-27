package com.devh.project.realestate.domain.complex.vo.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Type {
    @Field(name = "code", type = FieldType.Keyword)
    private String code;
    @Field(name = "name", type = FieldType.Keyword)
    private String name;

    private Type(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Type of(String code, String name) {
        return new Type(code, name);
    }

    public static Type ofDefault() {
        return new Type("", "");
    }
}
