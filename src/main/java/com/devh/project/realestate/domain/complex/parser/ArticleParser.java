package com.devh.project.realestate.domain.complex.parser;

import com.devh.project.realestate.domain.common.parser.Parser;
import com.devh.project.realestate.domain.complex.dto.NaverArticle;
import com.devh.project.realestate.domain.complex.dto.NaverArticleList;
import com.devh.project.realestate.domain.complex.vo.article.Article;
import com.devh.project.realestate.exception.ParserException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ArticleParser extends Parser<NaverArticleList> {

    private final ObjectMapper objectMapper;

    @Autowired
    public ArticleParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Article> parseArticles(Document document) {
        try {
            return parse(document).getArticleList().stream().map(NaverArticle::toArticle).collect(Collectors.toList());
        } catch (ParserException e) {
            return Collections.emptyList();
        }
    }

    @Override
    protected NaverArticleList parse(Document document) {
        try {
            return objectMapper.readValue(
                    document.parser(org.jsoup.parser.Parser.xmlParser()).body().text(), new TypeReference<>() {});
        } catch (Exception e) {
//            log.error("failed to parse document.", e);
            throw new ParserException(e.getMessage());
        }
    }
}
