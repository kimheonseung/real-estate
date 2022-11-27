package com.devh.project.realestate.domain.complex.vo.article;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    private String id;
    private InformationFrom informationFrom;
    private Realtor realtor;
    private ArticleDetail articleDetail;

    private Article(String id, InformationFrom informationFrom, Realtor realtor, ArticleDetail articleDetail) {
        this.id = id;
        this.informationFrom = informationFrom;
        this.realtor = realtor;
        this.articleDetail = articleDetail;
    }

    public static Article of(String id, InformationFrom informationFrom, Realtor realtor, ArticleDetail articleDetail) {
        return new Article(id, informationFrom, realtor, articleDetail);
    }
}
