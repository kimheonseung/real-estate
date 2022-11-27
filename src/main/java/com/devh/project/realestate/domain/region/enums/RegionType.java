package com.devh.project.realestate.domain.region.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum RegionType {
    CITY("C", "city"),
    GU("G", "dvsn"),
    DONG("D", "sec"),
    UNKNOWN("", "");

    private final String discriminatorValue;
    private final String naverValue;

    RegionType(
            String discriminatorValue,
            String naverValue) {
        this.discriminatorValue = discriminatorValue;
        this.naverValue = naverValue;
    }

    public static RegionType toRegionType(String value) {
        if(StringUtils.equalsIgnoreCase(CITY.getNaverValue(), value)) {
            return CITY;
        } else if(StringUtils.equalsIgnoreCase(GU.getNaverValue(), value)) {
            return GU;
        } else if(StringUtils.equalsIgnoreCase(DONG.getNaverValue(), value)) {
            return DONG;
        }
        return UNKNOWN;
    }
}
