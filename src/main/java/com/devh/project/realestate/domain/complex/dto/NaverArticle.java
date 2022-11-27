package com.devh.project.realestate.domain.complex.dto;

import com.devh.project.realestate.domain.complex.vo.article.Article;
import com.devh.project.realestate.domain.complex.vo.article.ArticleDetail;
import com.devh.project.realestate.domain.complex.vo.article.ArticleSame;
import com.devh.project.realestate.domain.complex.vo.article.InformationFrom;
import com.devh.project.realestate.domain.complex.vo.article.Realtor;
import com.devh.project.realestate.domain.complex.vo.common.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverArticle {
    // 면적 1 - 120
    private int area1;
    // 면적 2 - 84
    private int area2;
    // 면적 - 120
    private String areaName;
    // 마지막 확인일자 - 20221126 - articleDetail.lastConfirmed
    private String articleConfirmYmd;
    // 매물 설명 - 가격 조정 가능합니다 - articleDetail.description
    private String articleFeatureDesc;
    // 매물명 - e편한세상독산더타워(주상복합)
    private String articleName;
    // 매물번호 - 2242309870 - article.id
    private String articleNo;
    // 매물 타입 - A01 - articleDetail.type.code
    private String articleRealEstateTypeCode;
    // 매물 타입명 - 아파트 - articleDetail.type.name
    private String articleRealEstateTypeName;
    // 매물 상태 - R0 - articleDetail.status
    private String articleStatus;
    // 매물 동 이름 - 102동 - articleDetail.buildingName
    private String buildingName;
    // ?
    private boolean cpMobileArticleLinkUseAtArticleTitleYn;
    // ?
    private boolean cpMobileArticleLinkUseAtCpNameYn;
    // ?
    private String cpMobileArticleUrl;
    // 정보제공 - 부동산써브 - informationFrom.name
    private String cpName;
    private String cpPcArticleBridgeUrl;
    private boolean cpPcArticleLinkUseAtArticleTitleYn;
    private boolean cpPcArticleLinkUseAtCpNameYn;
    // 정보제공 매물링크 - informationFrom.url
    private String cpPcArticleUrl;
    // 정보제공 고유ID - SERVE - informationFrom.id
    private String cpid;
    // 금액 - 11억 - price - articleDetail.price
    private String dealOrWarrantPrc;
    private String detailAddress;
    private String detailAddressYn;
    // 방향 - 남향 - articleDetail.direction
    private String direction;
    // 층 정보 - 15/39 - articleDetail.floor
    private String floorInfo;
    // 대단지여부? - articleDetail.is...
    private boolean isComplex;
    private boolean isDirectTrade;
    private boolean isInterest;
    private boolean isLocationShow;
    private boolean isPriceModification;
    private String latitude;
    private String longitude;
    // 건물 타입 - APT - articleDetail.type.code
    private String realEstateTypeCode;
    // 건물 타입명 - 아파트 - articleDetail.type.name
    private String realEstateTypeName;
    // 부동산 ID - realtor.id
    private String realtorId;
    // 부동산명 - realtor.name
    private String realtorName;
    // 대표 이미지 섬네일 - f130_98
    private String representativeImgThumb;
    // 대표 이미지 타입 - 10
    private String representativeImgTypeCode;
    // 대표 이미지 url
    private String representativeImgUrl;
    // 동일매물 갯수 - articleDetail.same.count
    private int sameAddrCnt;
    // ? - articleDetail.same.directCount
    private int sameAddrDirectCnt;
    // 동일 매물 최대가 - articleDetail.same.priceBound.min
    private String sameAddrMaxPrc;
    // 동일 매물 최소가 - articleDetail.same.priceBound.max
    private String sameAddrMinPrc;
    // ?
    private int siteImageCount;
    // 태그 - ["4년이내", "마당", "화장실두개", "방세개"] - articleDetail.tags
    private String[] tagList;
    // 주인 확인 매물 - articleDetail.isCheckedByOwner
    private boolean tradeCheckedByOwner;
    // 거래 유형 타입 - A1 - articleDetail.tradeType.code
    private String tradeTypeCode;
    // 거래 유형 타입명 - 매매 - articleDetail.tradeType.name
    private String tradeTypeName;
    // 확인 타입 - OWNER [집주인], DOC - articleDetail.verificationTypeCode
    private String verificationTypeCode;

    public Article toArticle() {
        return Article.of(
                this.articleNo,
                InformationFrom.of(this.cpid, this.cpName, this.cpPcArticleUrl),
                Realtor.of(this.realtorId, this.realtorName),
                ArticleDetail.of(
                        Type.of(this.articleRealEstateTypeCode, this.articleRealEstateTypeName),
                        Type.of(this.tradeTypeCode, this.tradeTypeName),
                        this.dealOrWarrantPrc,
                        this.direction,
                        this.floorInfo,
                        this.tagList,
                        this.verificationTypeCode,
                        ArticleSame.of(this.sameAddrCnt, this.sameAddrDirectCnt, this.sameAddrMinPrc, this.sameAddrMaxPrc),
                        String.format("%s (%s/%s)", this.areaName, this.area1, this.area2),
                        this.articleConfirmYmd,
                        this.articleFeatureDesc,
                        this.articleStatus,
                        this.buildingName,
                        this.isComplex,
                        this.isDirectTrade,
                        this.isInterest,
                        this.isLocationShow,
                        this.isPriceModification,
                        this.tradeCheckedByOwner)
        );
    }
}
