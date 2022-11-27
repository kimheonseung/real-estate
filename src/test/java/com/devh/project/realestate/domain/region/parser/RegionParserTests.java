package com.devh.project.realestate.domain.region.parser;

import com.devh.project.realestate.domain.common.provider.JsoupDocumentProvider;
import com.devh.project.realestate.domain.region.constant.RegionConstant;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Dong;
import com.devh.project.realestate.domain.region.entity.Gu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RegionParserTests {
    @Autowired
    JsoupDocumentProvider jsoupDocumentProvider;
    @Autowired
    RegionParser regionParser;

    @Test
    void 조회() {
        final String urlPrefix = "https://new.land.naver.com/api/regions/list?cortarNo=";

        List<City> cities = regionParser.toCities(
                jsoupDocumentProvider.doGet(urlPrefix+RegionConstant.ROOT_REGION_ID));

        cities.forEach(city -> {
            System.out.printf("%s\n%s: %s", urlPrefix+city.getRegionId(), city.getRegionId(), city.getName());
            List<Gu> gus = regionParser.toGus(
                    jsoupDocumentProvider.doGet(urlPrefix+city.getRegionId()),
                    city);

            gus.forEach(gu -> {
                List<Dong> dongs = regionParser.toDongs(
                        jsoupDocumentProvider.doGet(urlPrefix+gu.getRegionId()),
                        gu);

            });
        });

    }

}
