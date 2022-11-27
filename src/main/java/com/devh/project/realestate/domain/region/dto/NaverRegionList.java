package com.devh.project.realestate.domain.region.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverRegionList {
    private List<NaverRegion> regionList;
}
