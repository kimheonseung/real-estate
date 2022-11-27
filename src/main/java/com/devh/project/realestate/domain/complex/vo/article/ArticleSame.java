package com.devh.project.realestate.domain.complex.vo.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleSame {
    private int count;
    private int directCount;
    private String minPriceString;
    private String maxPriceString;

    private ArticleSame(int count, int directCount, String minPriceString, String maxPriceString) {
        this.count = count;
        this.directCount = directCount;
        this.minPriceString = minPriceString;
        this.maxPriceString = maxPriceString;
    }

    public static ArticleSame of(int count, int directCount, String minPriceString, String maxPriceString) {
        return new ArticleSame(count, directCount, minPriceString, maxPriceString);
    }
}
