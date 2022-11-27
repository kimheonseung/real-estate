package com.devh.project.realestate.domain.complex.parser;

import com.devh.project.realestate.domain.common.parser.Parser;
import com.devh.project.realestate.domain.complex.dto.NaverComplexOverview;
import com.devh.project.realestate.domain.complex.vo.overview.Overview;
import com.devh.project.realestate.exception.ParserException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OverviewParser extends Parser<NaverComplexOverview> {

    private final ObjectMapper objectMapper;

    @Autowired
    public OverviewParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Overview parseOverview(Document document) {
        try {
            return parse(document).toOverview();
        } catch (ParserException e) {
            return Overview.createDefault();
        }
    }

    @Override
    protected NaverComplexOverview parse(Document document) {
        try {
            return objectMapper.readValue(
                    document.parser(org.jsoup.parser.Parser.xmlParser()).body().text(), new TypeReference<>() {});
        } catch (Exception e) {
//            log.error("failed to parse document.", e);
            throw new ParserException(e.getMessage());
        }
    }
}
