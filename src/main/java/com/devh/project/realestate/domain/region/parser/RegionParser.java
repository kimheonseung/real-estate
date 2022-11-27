package com.devh.project.realestate.domain.region.parser;

import com.devh.project.realestate.domain.region.dto.NaverRegion;
import com.devh.project.realestate.domain.region.dto.NaverRegionList;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Dong;
import com.devh.project.realestate.domain.region.entity.Gu;
import com.devh.project.realestate.domain.region.entity.Region;
import com.devh.project.realestate.exception.ParserException;
import com.devh.project.realestate.domain.common.parser.Parser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class RegionParser extends Parser<NaverRegionList> {

    private final ObjectMapper objectMapper;

    @Autowired
    public RegionParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<City> toCities(Document document) {
        return parse(document).getRegionList().stream()
                .map(NaverRegion::toCity).collect(Collectors.toList());
    }

    public List<Dong> toDongs(Document document, Region parent) {
        return parse(document).getRegionList().stream()
                .map(naverRegion -> naverRegion.toDong(parent)).collect(Collectors.toList());
    }

    public List<Gu> toGus(Document document, Region parent) {
        return parse(document).getRegionList().stream()
                .map(naverRegion -> naverRegion.toGu(parent)).collect(Collectors.toList());
    }

    @Override
    protected NaverRegionList parse(Document document) {
        try {
            return objectMapper.readValue(
                    document.parser(org.jsoup.parser.Parser.xmlParser()).body().text(), new TypeReference<>() {});
        } catch (Exception e) {
            log.error("failed to parse document.", e);
            throw new ParserException(e.getMessage());
        }
    }
}
