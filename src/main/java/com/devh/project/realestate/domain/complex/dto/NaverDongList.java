package com.devh.project.realestate.domain.complex.dto;

import com.devh.project.realestate.domain.region.entity.Dong;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverDongList {
    private List<Dong> dongs;
}
