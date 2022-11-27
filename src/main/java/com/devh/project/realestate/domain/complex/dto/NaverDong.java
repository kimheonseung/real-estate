package com.devh.project.realestate.domain.complex.dto;

import com.devh.project.realestate.domain.complex.vo.common.bound.FloorBound;
import com.devh.project.realestate.domain.complex.vo.overview.Building;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverDong {
    // 동 이름 - 101
    private String bildName;
    // 동 번호 - 1
    private String dongNo;
    // 동 최고층
    private int highFloor;
    // 동 최저층
    private int lowFloor;
    // ?? - 3101, 3102, ...
    private String sortNo;

    public Building toBuilding() {
        return Building.of(bildName, dongNo, FloorBound.of(lowFloor, highFloor), sortNo);
    }
}
