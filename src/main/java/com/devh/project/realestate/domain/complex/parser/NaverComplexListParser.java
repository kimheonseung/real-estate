package com.devh.project.realestate.domain.complex.parser;

import com.devh.project.realestate.domain.common.parser.Parser;
import com.devh.project.realestate.domain.complex.dto.NaverComplexList;
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
public class NaverComplexListParser extends Parser<NaverComplexList> {

    private final ObjectMapper objectMapper;

    @Autowired
    public NaverComplexListParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public NaverComplexList parseNaverComplexList(Document document) {
        return parse(document);
    }

    @Override
    protected NaverComplexList parse(Document document) {
        try {
            return objectMapper.readValue(
                    document.parser(org.jsoup.parser.Parser.xmlParser()).body().text(), new TypeReference<>() {});
        } catch (Exception e) {
            log.error("failed to parse document.", e);
            throw new ParserException(e.getMessage());
        }
    }
}
