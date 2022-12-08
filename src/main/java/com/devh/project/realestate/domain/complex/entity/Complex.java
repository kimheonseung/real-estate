package com.devh.project.realestate.domain.complex.entity;

import com.devh.project.realestate.domain.complex.vo.article.Article;
import com.devh.project.realestate.domain.complex.vo.complex.Household;
import com.devh.project.realestate.domain.complex.vo.complex.Location;
import com.devh.project.realestate.domain.complex.vo.overview.Overview;
import com.devh.project.realestate.domain.complex.vo.complex.Sales;
import com.devh.project.realestate.domain.complex.vo.common.Type;
import lombok.Getter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

import javax.persistence.Id;
import java.util.List;

@Document(
        indexName = "complex-#{@dateStringProvider.getCurrentDateString()}",
        writeTypeHint = WriteTypeHint.FALSE
)
@Getter
public class Complex {
    @Id
    private String id;
    @Field(name = "regionId", type = FieldType.Keyword)
    private String regionId;
    @Field(name = "location", type = FieldType.Nested)
    private Location location;
    @Field(name = "name", type = FieldType.Text)
    private String name;
    @Field(name = "approved", type = FieldType.Keyword)
    private String approved;
    @Field(name = "type", type = FieldType.Nested)
    private Type type;
    @Field(name = "household", type = FieldType.Nested)
    private Household household;
    @Field(name = "sales", type = FieldType.Nested)
    private Sales sales;
    @Field(name = "overview", type = FieldType.Nested)
    private Overview overview;
    @Field(name = "articles", type = FieldType.Nested)
    private List<Article> articles;

    private Complex(
            String id,
            String regionId,
            Location location,
            String name,
            String approved,
            Type type,
            Household household,
            Sales sales,
            Overview overview,
            List<Article> articles) {
        this.id = id;
        this.regionId = regionId;
        this.location = location;
        this.name = name;
        this.approved = approved;
        this.type = type;
        this.household = household;
        this.sales = sales;
        this.overview = overview;
        this.articles = articles;
    }

    public static Complex create(
            String id,
            String regionId,
            Location location,
            String name,
            String approved,
            Type type,
            Household household,
            Sales sales,
            Overview overview,
            List<Article> articles) {
        return new Complex(id, regionId, location, name, approved, type, household, sales, overview, articles);
    }
}
