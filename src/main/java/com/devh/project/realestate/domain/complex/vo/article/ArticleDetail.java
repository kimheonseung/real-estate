package com.devh.project.realestate.domain.complex.vo.article;

import com.devh.project.realestate.domain.complex.vo.common.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleDetail {
    private Type type;
    private Type tradeType;

    private String price;
    private String direction;
    private String floor;

    private String[] tags;
    private String verificationTypeCode;

    private ArticleSame same;

    private String area;
    private String lastConfirmed;
    private String description;
    private String status;
    private String buildingName;

    private boolean isComplex;
    private boolean isDirectTrade;
    private boolean isInterest;
    private boolean isLocationShow;
    private boolean isPriceModification;
    private boolean isCheckedByOwner;

    private ArticleDetail(
            Type type,
            Type tradeType,
            String price,
            String direction,
            String floor,
            String[] tags,
            String verificationTypeCode,
            ArticleSame same,
            String area,
            String lastConfirmed,
            String description,
            String status,
            String buildingName,
            boolean isComplex,
            boolean isDirectTrade,
            boolean isInterest,
            boolean isLocationShow,
            boolean isPriceModification,
            boolean isCheckedByOwner
    ) {
        this.type = type;
        this.tradeType = tradeType;
        this.price = price;
        this.direction = direction;
        this.floor = floor;
        this.tags = tags;
        this.verificationTypeCode = verificationTypeCode;
        this.same = same;
        this.area = area;
        this.lastConfirmed = lastConfirmed;
        this.description = description;
        this.status = status;
        this.buildingName = buildingName;
        this.isComplex = isComplex;
        this.isDirectTrade = isDirectTrade;
        this.isInterest = isInterest;
        this.isLocationShow = isLocationShow;
        this.isPriceModification = isPriceModification;
        this.isCheckedByOwner = isCheckedByOwner;
    }

    public static ArticleDetail of(
            Type type,
            Type tradeType,
            String price,
            String direction,
            String floor,
            String[] tags,
            String verificationTypeCode,
            ArticleSame same,
            String area,
            String lastConfirmed,
            String description,
            String status,
            String buildingName,
            boolean isComplex,
            boolean isDirectTrade,
            boolean isInterest,
            boolean isLocationShow,
            boolean isPriceModification,
            boolean isCheckedByOwner
    ) {
        return new ArticleDetail(
                type,
                tradeType,
                price,
                direction,
                floor,
                tags,
                verificationTypeCode,
                same,
                area,
                lastConfirmed,
                description,
                status,
                buildingName,
                isComplex,
                isDirectTrade,
                isInterest,
                isLocationShow,
                isPriceModification,
                isCheckedByOwner
        );
    }
}
