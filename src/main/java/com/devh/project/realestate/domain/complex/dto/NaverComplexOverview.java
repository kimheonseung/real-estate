package com.devh.project.realestate.domain.complex.dto;


import com.devh.project.realestate.domain.complex.vo.common.Type;
import com.devh.project.realestate.domain.complex.vo.common.bound.AreaBound;
import com.devh.project.realestate.domain.complex.vo.common.bound.PriceBound;
import com.devh.project.realestate.domain.complex.vo.overview.Overview;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverComplexOverview {
    // ? - ?
    private String[] complexExistTabs;
    // 단지명 - e편한세상독산더타워(주상복합)
    private String complexName;
    // 단지 구분번호 - 115472
    private String complexNo;
    // 단지 타입 번호 - A01 (type.code)
    private String complexType;
    // 단지 타입 이름 - 아파트 (type.name)
    private String complexTypeName;
    // 동 정보들 (buildings)
    private List<NaverDong> dongs;
    // ? - N
    private String isaleDealRestrictionCode;
    // 위도
    private float latitude;
    // 경도
    private float longitude;
    // ? - 0
    private int leasePerDealRate;
    // ? - N
    private String livingResidenceYn;
    // 최대 면적 (areaBound.max)
    private float maxArea;
    // 최소 면적 (areaBound.min)
    private float minArea;
    // 전세 최대값 (leasePriceBound.max)
    private int maxLeasePrice;
    // 전세 최대값 한글 - 5억 4,000
    private String maxLeasePriceByLetter;
    // 전세 최소값 (leasePriceBound.min)
    private int minLeasePrice;
    // 전세 최소값 한글
    private String minLeasePriceByLetter;
    // 매매 최대값 (priceBound.max)
    private int maxPrice;
    // 매매 최대값 한글
    private String maxPriceByLetter;
    // 매매 최소값 (priceBound.min)
    private int minPrice;
    // 매매 최소값 한글
    private String minPriceByLetter;
    // 평 정보들 (pyeongs)
    private List<NaverPyeong> pyeongs;
    // 재건축 멤버쉽 ? - N
    private String rebuildMembershipTransYn;
    // 총 동수
    private int totalDongCount;
    // 총 세대수
    private int totalHouseHoldCount;
    // 사용 승인일
    private String useApproveYmd;

    public Overview toOverview() {
        return Overview.create(
                Type.of(this.complexType, this.complexTypeName),
                AreaBound.of(this.minArea, this.maxArea),
                PriceBound.of(this.minPrice, this.maxPrice),
                PriceBound.of(this.minLeasePrice, this.maxLeasePrice),
                this.dongs.stream().map(NaverDong::toBuilding).collect(Collectors.toList()),
                this.pyeongs.stream().map(NaverPyeong::toPyeong).collect(Collectors.toList())
        );
    }
}
