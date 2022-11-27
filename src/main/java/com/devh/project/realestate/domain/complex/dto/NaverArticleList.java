package com.devh.project.realestate.domain.complex.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverArticleList {
    private String isMoreData;
    private List<NaverArticle> articleList;
    private int mapExposedCount;
    private boolean nonMapExposedIncluded;
}
