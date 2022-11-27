package com.devh.project.realestate.domain.region.repository;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import com.devh.project.realestate.domain.region.entity.City;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityRepositoryTests {
    @Autowired
    CityRepository cityRepository;

    @Test
    void 시_단일_저장_성공() {
        // given
        String regionId = "testId";
        City city = City.create(
                regionId,
                Coordinates.of(37.4839238f, 42.4983954f),
                "스울시");
        // when
        cityRepository.save(city);
        // then
        City savedRegion = cityRepository.findById(regionId).orElseThrow();
        Assertions.assertThat(savedRegion).isEqualTo(city);
    }

    @Test
    void 시_복수_저장_성공() {
        // given
        String regionId1 = "testId1";
        String regionId2 = "testId2";
        String regionId3 = "testId3";
        List<City> cities = new ArrayList<>();
        cities.add(
                City.create(
                        regionId1,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "스울시"));
        cities.add(
                City.create(
                        regionId2,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "스울시"));
        cities.add(
                City.create(
                        regionId3,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "스울시"));
        // when
        cityRepository.saveAll(cities);
        // then
        List<City> savedCities = cityRepository.findAll();
        Assertions.assertThat(savedCities.size()).isEqualTo(cities.size());
        Assertions.assertThat(savedCities.get(0).getRegionId()).isEqualTo(regionId1);
        Assertions.assertThat(savedCities.get(1).getRegionId()).isEqualTo(regionId2);
        Assertions.assertThat(savedCities.get(2).getRegionId()).isEqualTo(regionId3);
    }
}
