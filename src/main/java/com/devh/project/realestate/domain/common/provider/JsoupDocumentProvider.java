package com.devh.project.realestate.domain.common.provider;

import com.devh.project.realestate.domain.token.service.TokenService;
import com.devh.project.realestate.exception.JsoupException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class JsoupDocumentProvider {

    private final JsoupHeaderProvider jsoupHeaderProvider;
    private final String token;


    @Autowired
    public JsoupDocumentProvider(
            JsoupHeaderProvider jsoupHeaderProvider,
            TokenService tokenService) {
        this.jsoupHeaderProvider = jsoupHeaderProvider;
        this.token = tokenService.getToken().getToken();
    }

    public Document doGet(String url) {
        try {
            return Jsoup.connect(url).ignoreContentType(true).get();
        } catch (Exception e) {
            log.error("jsoup failed.", e);
            throw new JsoupException(e.getMessage());
        }
    }

    public Document doGetComplex(String complexNo) {
        try {
            return Jsoup.connect(complexUrl(complexNo)).ignoreContentType(true).get();
        } catch (Exception e) {
            log.error("jsoup failed.", e);
            throw new JsoupException(e.getMessage());
        }
    }

    public Document doGetOverview(String complexNo) {
        try {
            final String url = overviewUrl(complexNo);
            jsoupHeaderProvider.update("authorization", token);
            jsoupHeaderProvider.update("Referer", url+"?a=APT:ABYG:OPST:JGC:OBYG:JGB&b=A1");
            return Jsoup.connect(url)
                    .ignoreContentType(true)
                    .headers(jsoupHeaderProvider.getHeader())
                    .data(Collections.singletonMap("sameAddressGroup", "false"))
                    .get();
        } catch (Exception e) {
            log.error("jsoup failed.", e);
            throw new JsoupException(e.getMessage());
        }
    }

    public Document doGetArticle(String complexNo) {
        try {
            final String url = articleUrl(complexNo);
            jsoupHeaderProvider.update("authorization", token);
            jsoupHeaderProvider.update("Referer", defaultArticleUrl(complexNo)+"?a=APT:ABYG:JGC&b=A1&e=RETAIL&ad=true");
            return Jsoup.connect(url)
                    .ignoreContentType(true)
                    .headers(jsoupHeaderProvider.getHeader())
                    .get();
        } catch (Exception e) {
//            log.error("jsoup failed. - "+complexNo, e);
//            throw new JsoupException(e.getMessage());
            return doGetArticleFull(complexNo);
        }
    }

    public Document doGetArticleFull(String complexNo) {
        try {
            final String url = articleUrlFull(complexNo);
            jsoupHeaderProvider.update("authorization", token);
            jsoupHeaderProvider.update("Referer", defaultArticleUrl(complexNo)+"?a=APT:ABYG:OPST:JGC:OBYG:JGB&b=A1&e=RETAIL&ad=true");
            return Jsoup.connect(url)
                    .ignoreContentType(true)
                    .headers(jsoupHeaderProvider.getHeader())
                    .get();
        } catch (Exception e) {
            log.error("jsoup failed. - "+complexNo, e);
            throw new JsoupException(e.getMessage());
        }
    }

    private String complexUrl(String complexNo) {
        return String.format("https://new.land.naver.com/api/regions/complexes?cortarNo=%s", complexNo);
    }

    private String overviewUrl(String complexNo) {
        return String.format("https://new.land.naver.com/api/complexes/overview/%s", complexNo);
    }

    private String defaultArticleUrl(String complexNo) {
        return String.format("https://new.land.naver.com/api/articles/complex/%s", complexNo);
    }

    private String articleUrl(String complexNo) {
        // &tag=%3A%3A%3A%3A%3A%3A%3A%3A&rentPriceMin=0&rentPriceMax=900000000&priceMin=0&priceMax=900000000&areaMin=0&areaMax=900000000
//        return String.format("%s?realEstateType=APT%%3AABYG%%3AOPST%%3AJGC%%3AOBYG%%3AJGB&tradeType=A1&oldBuildYears&recentlyBuildYears&minHouseHoldCount&maxHouseHoldCount&showArticle=false&sameAddressGroup=true&minMaintenanceCost&maxMaintenanceCost&priceType=RETAIL&directions=&page=1&complexNo=%s&buildingNos=&areaNos=&type=list&order=rank",
        return String.format("%s?realEstateType=APT%%3AABYG%%3AJGC%%3AJGB&tradeType=A1&oldBuildYears&recentlyBuildYears&minHouseHoldCount&maxHouseHoldCount&showArticle=false&sameAddressGroup=true&minMaintenanceCost&maxMaintenanceCost&priceType=RETAIL&directions=&page=1&complexNo=%s&buildingNos=&areaNos=&type=list&order=rank",
                defaultArticleUrl(complexNo), complexNo);
    }

    private String articleUrlFull(String complexNo) {
        // &tag=%3A%3A%3A%3A%3A%3A%3A%3A&rentPriceMin=0&rentPriceMax=900000000&priceMin=0&priceMax=900000000&areaMin=0&areaMax=900000000
        return String.format("%s?realEstateType=APT%%3AABYG%%3AOPST%%3AJGC%%3AOBYG%%3AJGB&tradeType=A1&oldBuildYears&recentlyBuildYears&minHouseHoldCount&maxHouseHoldCount&showArticle=false&sameAddressGroup=true&minMaintenanceCost&maxMaintenanceCost&priceType=RETAIL&directions=&page=1&complexNo=%s&buildingNos=&areaNos=&type=list&order=rank",
                defaultArticleUrl(complexNo), complexNo);
    }
}
