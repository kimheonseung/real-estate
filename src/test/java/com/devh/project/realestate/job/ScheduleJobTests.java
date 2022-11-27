package com.devh.project.realestate.job;

import com.devh.project.realestate.domain.common.provider.JsoupDocumentProvider;
import com.devh.project.realestate.domain.complex.dto.NaverComplexList;
import com.devh.project.realestate.domain.complex.vo.article.Article;
import com.devh.project.realestate.domain.complex.vo.overview.Overview;
import com.devh.project.realestate.domain.complex.entity.Complex;
import com.devh.project.realestate.domain.complex.parser.ArticleParser;
import com.devh.project.realestate.domain.complex.parser.NaverComplexListParser;
import com.devh.project.realestate.domain.complex.parser.OverviewParser;
import com.devh.project.realestate.domain.complex.repository.ComplexRepository;
import com.devh.project.realestate.domain.region.repository.CityRepository;
import com.devh.project.realestate.domain.region.repository.DongRepository;
import com.devh.project.realestate.domain.region.repository.GuRepository;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ScheduleJobTests {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private GuRepository guRepository;
    @Autowired
    private DongRepository dongRepository;
    @Autowired
    private ComplexRepository complexRepository;
    @Autowired
    private JsoupDocumentProvider jsoupDocumentProvider;

    @Autowired
    private NaverComplexListParser naverComplexListParser;
    @Autowired
    private OverviewParser overviewParser;
    @Autowired
    private ArticleParser articleParser;

    @Test
    void testComplex() {
//        City city = cityRepository.findAll().get(0);
//        Gu gu = guRepository.findAllByParent(city).get(0);
//        Dong dong = dongRepository.findAllByParent(gu).get(0);
//        final String dongId = dong.getRegionId();

        Document document = jsoupDocumentProvider.doGetComplex("1154510200");
        NaverComplexList naverComplexList = naverComplexListParser.parseNaverComplexList(document);
        System.out.println(naverComplexList.toString());
//        List<Complex> complexes = naverComplexList.getComplexList().stream()
//                .map(NaverComplex::toComplex).collect(Collectors.toList());
//
//        complexRepository.saveAll(complexes);
    }

    @Test
    void testComplexOverview() {
        Document document = jsoupDocumentProvider.doGetOverview("115472");
        Overview overview = overviewParser.parseOverview(document);
        System.out.println(overview);
    }

    @Test
    void testArticles() {
        Document document = jsoupDocumentProvider.doGetArticle("115472");
        List<Article> articles = articleParser.parseArticles(document);
        articles.forEach(System.out::println);
    }

    @Test
    void testTotal() {

        NaverComplexList naverComplexList =
                naverComplexListParser.parseNaverComplexList(jsoupDocumentProvider.doGetComplex("1154510200"));

        List<Complex> complexes = naverComplexList.getComplexList().stream()
                .map(naverComplex -> {
                    final String complexNo = naverComplex.getComplexNo();
                    return naverComplex.toComplex(
                            overviewParser.parseOverview(jsoupDocumentProvider.doGetOverview(complexNo)),
                            articleParser.parseArticles(jsoupDocumentProvider.doGetArticle(complexNo)));
                }).collect(Collectors.toList());

        complexes.forEach(System.out::println);
        complexRepository.saveAll(complexes);
    }







}
