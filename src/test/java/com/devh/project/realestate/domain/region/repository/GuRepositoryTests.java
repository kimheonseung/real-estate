package com.devh.project.realestate.domain.region.repository;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Gu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GuRepositoryTests {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    GuRepository guRepository;

    @Test
    void 구_단일_저장_성공() {
        // given
        City parent = City.create(
                "C1234",
                Coordinates.of(0, 0),
                "서울시");
        cityRepository.save(parent);
        String regionId = "testId";
        Gu gu = Gu.create(
                regionId,
                parent,
                Coordinates.of(37.4839238f, 42.4983954f),
                "스울시");
        // when
        guRepository.save(gu);
        // then
        Gu savedGu = guRepository.findById(regionId).orElseThrow();
        Assertions.assertThat(savedGu).isEqualTo(gu);
    }

    @Test
    void 구_복수_저장_성공() {
        // given
        City parent = City.create(
                "C1234",
                Coordinates.of(0, 0),
                "서울시");
        cityRepository.save(parent);
        String regionId1 = "testId1";
        String regionId2 = "testId2";
        String regionId3 = "testId3";
        List<Gu> gus = new ArrayList<>();
        gus.add(
                Gu.create(
                        regionId1,
                        parent,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "스울시"));
        gus.add(
                Gu.create(
                        regionId2,
                        parent,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "스울시"));
        gus.add(
                Gu.create(
                        regionId3,
                        parent,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "스울시"));
        // when
        guRepository.saveAll(gus);
        // then
        List<Gu> savedGus = guRepository.findAll();
        Assertions.assertThat(savedGus.size()).isEqualTo(gus.size());
        Assertions.assertThat(savedGus.get(0).getRegionId()).isEqualTo(regionId1);
        Assertions.assertThat(savedGus.get(1).getRegionId()).isEqualTo(regionId2);
        Assertions.assertThat(savedGus.get(2).getRegionId()).isEqualTo(regionId3);
    }
}
