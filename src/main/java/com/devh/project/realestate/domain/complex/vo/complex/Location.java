package com.devh.project.realestate.domain.complex.vo.complex;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Field(name = "address", type = FieldType.Text)
    private String address;
    @Field(name = "detailAddress", type = FieldType.Keyword)
    private String detailAddress;

    @Field(name = "coordinates", type = FieldType.Nested)
    private Coordinates coordinates;


    private Location(String address, String detailAddress, Coordinates coordinates) {
        this.address = address;
        this.detailAddress = detailAddress;
        this.coordinates = coordinates;
    }

    public static Location of(String address, String detailAddress, Coordinates coordinates) {
        return new Location(address, detailAddress, coordinates);
    }
}
