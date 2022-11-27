package com.devh.project.realestate.domain.complex.vo.complex;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sales {
    @Field(name = "deal", type = FieldType.Integer)
    private int deal;
    @Field(name = "lease", type = FieldType.Integer)
    private int lease;
    @Field(name = "rent", type = FieldType.Integer)
    private int rent;
    @Field(name = "shortTermRent", type = FieldType.Integer)
    private int shortTermRent;

    private Sales(int deal, int lease, int rent, int shortTermRent) {
        this.deal = deal;
        this.lease = lease;
        this.rent = rent;
        this.shortTermRent = shortTermRent;
    }

    public static Sales create(int deal, int lease, int rent, int shortTermRent) {
        return new Sales(deal, lease, rent, shortTermRent);
    }
}
