package com.devh.project.realestate.job;

import com.devh.project.realestate.domain.common.provider.JsoupDocumentProvider;
import com.devh.project.realestate.domain.complex.dto.NaverComplexList;
import com.devh.project.realestate.domain.complex.entity.Complex;
import com.devh.project.realestate.domain.complex.parser.ArticleParser;
import com.devh.project.realestate.domain.complex.parser.NaverComplexListParser;
import com.devh.project.realestate.domain.complex.parser.OverviewParser;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Dong;
import com.devh.project.realestate.domain.region.entity.Gu;
import com.devh.project.realestate.domain.region.repository.CityRepository;
import com.devh.project.realestate.domain.region.repository.DongRepository;
import com.devh.project.realestate.domain.region.repository.GuRepository;
import com.devh.project.realestate.domain.complex.repository.ComplexRepository;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleJob {
    private final CityRepository cityRepository;
    private final GuRepository guRepository;
    private final DongRepository dongRepository;
    private final ComplexRepository complexRepository;
    private final NaverComplexListParser naverComplexListParser;
    private final OverviewParser overviewParser;
    private final ArticleParser articleParser;
    private final JsoupDocumentProvider jsoupDocumentProvider;

    @Autowired
    public ScheduleJob(
            CityRepository cityRepository,
            GuRepository guRepository,
            DongRepository dongRepository,
            ComplexRepository complexRepository,
            NaverComplexListParser naverComplexListParser,
            OverviewParser overviewParser,
            ArticleParser articleParser,
            JsoupDocumentProvider jsoupDocumentProvider) {
        this.cityRepository = cityRepository;
        this.guRepository = guRepository;
        this.dongRepository = dongRepository;
        this.complexRepository = complexRepository;
        this.naverComplexListParser = naverComplexListParser;
        this.overviewParser = overviewParser;
        this.articleParser = articleParser;
        this.jsoupDocumentProvider = jsoupDocumentProvider;
    }

    public void runJob() {
        List<City> cities = cityRepository.findAll();

        for (City city : cities) {
            List<Gu> gus = guRepository.findAllByParent(city);

            for (Gu gu : gus) {
                List<Dong> dongs = dongRepository.findAllByParent(gu);

                for (Dong dong : dongs) {
                    final String regionId = dong.getRegionId();

                    Document complexDocument = jsoupDocumentProvider.doGetComplex(regionId);
                    NaverComplexList naverComplexList = naverComplexListParser.parseNaverComplexList(complexDocument);

                    List<Complex> complexes = naverComplexList.getComplexList().stream()
                            .map(naverComplex -> {
                                final String complexNo = naverComplex.getComplexNo();

                                Document overviewDocument = jsoupDocumentProvider.doGetOverview(complexNo);
                                Document articleDocument = jsoupDocumentProvider.doGetArticle(complexNo);

                                return naverComplex.toComplex(
                                        overviewParser.parseOverview(overviewDocument),
                                        articleParser.parseArticles(articleDocument));
                            }).collect(Collectors.toList());

                    complexRepository.saveAll(complexes);
                }
            }
        }
    }
}
