package com.devh.project.realestate.domain.complex.vo.overview;

import com.devh.project.realestate.domain.complex.vo.common.Type;
import com.devh.project.realestate.domain.complex.vo.common.bound.AreaBound;
import com.devh.project.realestate.domain.complex.vo.common.bound.PriceBound;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Overview {
    private Type type;
    private AreaBound areaBound;
    private PriceBound priceBound;
    private PriceBound leasePriceBound;
    private List<Building> buildings;
    private List<Pyeong> pyeongs;

    private Overview(
            Type type,
            AreaBound areaBound,
            PriceBound priceBound,
            PriceBound leasePriceBound,
            List<Building> buildings,
            List<Pyeong> pyeongs) {
        this.type = type;
        this.areaBound = areaBound;
        this.priceBound = priceBound;
        this.leasePriceBound = leasePriceBound;
        this.buildings = buildings;
        this.pyeongs = pyeongs;
    }

    public static Overview create(
            Type type,
            AreaBound areaBound,
            PriceBound priceBound,
            PriceBound leasePriceBound,
            List<Building> buildings,
            List<Pyeong> pyeongs) {
        return new Overview(type, areaBound, priceBound, leasePriceBound, buildings, pyeongs);
    }

    public static Overview createDefault() {
        return new Overview(
                Type.ofDefault(),
                AreaBound.ofDefault(),
                PriceBound.ofDefault(),
                PriceBound.ofDefault(),
                Collections.emptyList(),
                Collections.emptyList());
    }
}
