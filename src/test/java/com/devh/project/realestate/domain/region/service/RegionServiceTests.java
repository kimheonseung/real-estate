package com.devh.project.realestate.domain.region.service;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import com.devh.project.realestate.domain.common.provider.JsoupDocumentProvider;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Dong;
import com.devh.project.realestate.domain.region.entity.Gu;
import com.devh.project.realestate.domain.region.parser.RegionParser;
import com.devh.project.realestate.domain.region.repository.CityRepository;
import com.devh.project.realestate.domain.region.repository.DongRepository;
import com.devh.project.realestate.domain.region.repository.GuRepository;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTests {
    @InjectMocks
    RegionService regionService;
    @Mock
    RegionParser regionParser;
    @Mock
    CityRepository cityRepository;
    @Mock
    GuRepository guRepository;
    @Mock
    DongRepository dongRepository;
    @Mock
    JsoupDocumentProvider jsoupDocumentProvider;

    @Test
    void upsertAll() {
        List<City> cities = new ArrayList<>();
        List<Gu> gus = new ArrayList<>();
        List<Dong> dongs = new ArrayList<>();
        City city1 = City.create(
                "C1",
                Coordinates.of(0, 0),
                "서울시");
        Gu gu11 = Gu.create(
                "G11",
                city1,
                Coordinates.of(0, 0),
                "은평구");
        Dong dong111 = Dong.create(
                "D111",
                gu11,
                Coordinates.of(0, 0),
                "역촌동");
        Dong dong112 = Dong.create(
                "D112",
                gu11,
                Coordinates.of(0, 0),
                "불광동");
        Gu gu12 = Gu.create(
                "G12",
                city1,
                Coordinates.of(0, 0),
                "금천구");
        Dong dong121 = Dong.create(
                "D121",
                gu12,
                Coordinates.of(0, 0),
                "독산동");

        City city2 = City.create(
                "C2",
                Coordinates.of(0, 0),
                "인천시");
        Gu gu21 = Gu.create(
                "G21",
                city1,
                Coordinates.of(0, 0),
                "부평구");
        Dong dong211 = Dong.create(
                "D211",
                gu21,
                Coordinates.of(0, 0),
                "부평동");

        City city3 = City.create(
                "C3",
                Coordinates.of(0, 0),
                "경기도");
        Gu gu31 = Gu.create(
                "G31",
                city1,
                Coordinates.of(0, 0),
                "용인시 처인구");

        cities.add(city1);
        cities.add(city2);
        cities.add(city3);

        gus.add(gu11);
        gus.add(gu12);
        gus.add(gu21);
        gus.add(gu31);

        dongs.add(dong111);
        dongs.add(dong112);
        dongs.add(dong121);
        dongs.add(dong211);

        BDDMockito.doNothing().when(dongRepository).deleteAll();
        BDDMockito.doNothing().when(guRepository).deleteAll();
        BDDMockito.doNothing().when(cityRepository).deleteAll();

        BDDMockito.given(jsoupDocumentProvider.doGet(BDDMockito.any())).willReturn(null);

        BDDMockito.given(cityRepository.saveAll(BDDMockito.any())).willReturn(Collections.emptyList());
        BDDMockito.given(guRepository.saveAll(BDDMockito.any())).willReturn(Collections.emptyList());
        BDDMockito.given(dongRepository.saveAll(BDDMockito.any())).willReturn(Collections.emptyList());

        BDDMockito.when(regionParser.toCities(null)).thenReturn(cities);
        BDDMockito.when(
                regionParser.toGus(BDDMockito.nullable(Document.class), BDDMockito.any(City.class))).thenReturn(gus);
        BDDMockito.when(
                regionParser.toDongs(BDDMockito.nullable(Document.class), BDDMockito.any(Gu.class))).thenReturn(dongs);

        regionService.upsertAll();
    }
}
