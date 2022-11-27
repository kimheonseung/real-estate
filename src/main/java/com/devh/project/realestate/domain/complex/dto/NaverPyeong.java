package com.devh.project.realestate.domain.complex.dto;

import com.devh.project.realestate.domain.complex.vo.overview.Area;
import com.devh.project.realestate.domain.complex.vo.overview.Pyeong;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverPyeong {
    // 전용면적 - 59.86 area.exclusive
    private float exclusiveArea;
    // 전용평 - 18.1 exclusive
    private float exclusivePyeong;
    // 평 이름 - 85A - name1
    private String pyeongName;
    // 평 이름 2 - 25A - name2
    private String pyeongName2;
    // 평 순서 - 3
    private int pyeongNo;
    // 공급 면적 - 85.64 area.supply
    private float supplyArea;
    // 공급 면적 소수값 - 85.64
    private float supplyAreaDouble;

    public Pyeong toPyeong() {
        return Pyeong.of(
                Area.of(this.exclusiveArea, this.supplyArea),
                this.exclusivePyeong,
                this.pyeongName,
                this.pyeongName2
        );
    }
}
