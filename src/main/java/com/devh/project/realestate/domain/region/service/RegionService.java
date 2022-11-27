package com.devh.project.realestate.domain.region.service;

import com.devh.project.realestate.domain.common.provider.JsoupDocumentProvider;
import com.devh.project.realestate.domain.region.constant.RegionConstant;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Dong;
import com.devh.project.realestate.domain.region.entity.Gu;
import com.devh.project.realestate.domain.region.parser.RegionParser;
import com.devh.project.realestate.domain.region.repository.CityRepository;
import com.devh.project.realestate.domain.region.repository.DongRepository;
import com.devh.project.realestate.domain.region.repository.GuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionService {
    private final String urlPrefix = "https://new.land.naver.com/api/regions/list?cortarNo=";

    private final CityRepository cityRepository;
    private final GuRepository guRepository;
    private final DongRepository dongRepository;
    private final JsoupDocumentProvider jsoupDocumentProvider;
    private final RegionParser regionParser;

    @Autowired
    public RegionService(
            CityRepository cityRepository,
            GuRepository guRepository,
            DongRepository dongRepository,
            JsoupDocumentProvider jsoupDocumentProvider,
            RegionParser regionParser) {
        this.cityRepository = cityRepository;
        this.guRepository = guRepository;
        this.dongRepository = dongRepository;
        this.jsoupDocumentProvider = jsoupDocumentProvider;
        this.regionParser = regionParser;
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public List<Gu> getGus(City city) {
        return guRepository.findAllByParent(city);
    }

    public List<Dong> getDongsByGu(Gu gu) {
        return dongRepository.findAllByParent(gu);
    }

    @Transactional
    public void upsertAll() {
        dongRepository.deleteAll();
        guRepository.deleteAll();
        cityRepository.deleteAll();

        List<City> cities = regionParser.toCities(
                jsoupDocumentProvider.doGet(urlPrefix+RegionConstant.ROOT_REGION_ID));

        cities.forEach(city -> {
            List<Gu> gus = regionParser.toGus(
                    jsoupDocumentProvider.doGet(urlPrefix+city.getRegionId()),
                    city);

            gus.forEach(gu -> {
                List<Dong> dongs = regionParser.toDongs(
                        jsoupDocumentProvider.doGet(urlPrefix+gu.getRegionId()),
                        gu);

                cityRepository.saveAll(cities);
                guRepository.saveAll(gus);
                dongRepository.saveAll(dongs);
            });
        });
    }

}
