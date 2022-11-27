package com.devh.project.realestate.domain.region.dto;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Dong;
import com.devh.project.realestate.domain.region.entity.Gu;
import com.devh.project.realestate.domain.region.entity.Region;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverRegion {
    private String cortarNo;
    private float centerLat;
    private float centerLon;
    private String cortarName;
    private String cortarType;

    public City toCity() {
        return City.create(
                cortarNo,
                Coordinates.of(centerLat, centerLon),
                cortarName);
    }

    public Gu toGu(Region parent) {
        return Gu.create(
                cortarNo,
                parent,
                Coordinates.of(centerLat, centerLon),
                cortarName);
    }

    public Dong toDong(Region parent) {
        return Dong.create(
                cortarNo,
                parent,
                Coordinates.of(centerLat, centerLon),
                cortarName);
    }
}
