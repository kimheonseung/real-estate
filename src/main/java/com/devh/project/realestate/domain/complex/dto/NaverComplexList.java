package com.devh.project.realestate.domain.complex.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverComplexList {
    private List<NaverComplex> complexList;
}
