package com.devh.project.realestate.domain.complex.vo.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InformationFrom {
    @Field(name = "id", type = FieldType.Keyword)
    private String id;
    @Field(name = "name", type = FieldType.Keyword)
    private String name;
    @Field(name = "url", type = FieldType.Text)
    private String url;

    private InformationFrom(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public static InformationFrom of(String id, String name, String url) {
        return new InformationFrom(id, name, url);
    }
}
