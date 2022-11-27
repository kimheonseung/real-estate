package com.devh.project.realestate.domain.complex.dto;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import com.devh.project.realestate.domain.complex.entity.Complex;
import com.devh.project.realestate.domain.complex.vo.article.Article;
import com.devh.project.realestate.domain.complex.vo.common.Type;
import com.devh.project.realestate.domain.complex.vo.common.bound.FloorBound;
import com.devh.project.realestate.domain.complex.vo.complex.Household;
import com.devh.project.realestate.domain.complex.vo.complex.Location;
import com.devh.project.realestate.domain.complex.vo.complex.Sales;
import com.devh.project.realestate.domain.complex.vo.overview.Overview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverComplex {
    // 매물 번호 (151232) - id
    private String complexNo;
    // 지역 고유값 (1154510200) - regionID
    private String cortarNo;

    // 동 주소 (서울시 금천구 독산동) - location.address
    private String cortarAddress;
    // 번지수 1006-21 - location.detailAddress
    private String detailAddress;
    // 위도 37.466183 - location.coordinates.latitude
    private float latitude;
    // 경도 126.89376 - location.coordinates.longitude
    private float longitude;

    // 매물명 (A-클래스(도시형)) - name
    private String complexName;

    // 매물 타입 코드 (APT, OPST) - type.code
    private String realEstateTypeCode;
    // 매물 타입명 (아파트, 오피스텔) - type.name
    private String realEstateTypeName;

    // 사용 승인일 - approvedAt
    private String useApproveYmd;

    // 세대수 - household.total
    private int totalHouseholdCount;
    // 동 갯수 - householde.buildings
    private int totalBuildingCount;
    // 최대 층 - household.highFloor
    private int highFloor;
    // 최저 층 - houseHold.lowFloor
    private int lowFloor;

    // 동일매물 묶어서 총 몇개의 매물이 있는지 - sales.total
    private int dealCount;
    // 매매 갯수 - sales.lease
    private int leaseCount;
    // 월세 갯수 - sales.rent
    private int rentCount;
    // 단기임대 갯수 - sales.shortTermRent
    private int shortTermRentCount;

    public Complex toComplex(Overview overview, List<Article> articles) {
        return Complex.create(
                this.complexNo,
                this.cortarNo,
                Location.of(
                        this.cortarAddress,
                        this.detailAddress,
                        Coordinates.of(this.latitude, this.longitude)),
                this.complexName,
                this.useApproveYmd,
                Type.of(this.realEstateTypeCode, this.realEstateTypeName),
                Household.create(
                        this.totalHouseholdCount,
                        this.totalBuildingCount,
                        FloorBound.of(this.lowFloor, this.highFloor)),
                Sales.create(
                        this.dealCount,
                        this.leaseCount,
                        this.rentCount,
                        this.shortTermRentCount),
                overview,
                articles);
    }
}
