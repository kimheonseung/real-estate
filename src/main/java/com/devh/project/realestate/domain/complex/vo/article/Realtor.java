package com.devh.project.realestate.domain.complex.vo.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Realtor {
    @Field(name = "id", type = FieldType.Keyword)
    private String id;
    @Field(name = "name", type = FieldType.Keyword)
    private String name;

    private Realtor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Realtor of(String id, String name) {
        return new Realtor(id, name);
    }
}
