package com.devh.project.realestate.domain.region.repository;

import com.devh.project.realestate.domain.common.embeddable.geo.Coordinates;
import com.devh.project.realestate.domain.region.entity.City;
import com.devh.project.realestate.domain.region.entity.Dong;
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
public class DongRepositoryTests {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    GuRepository guRepository;
    @Autowired
    DongRepository dongRepository;

    @Test
    void 동_단일_저장_성공() {
        // given
        City parentParent = City.create(
                "C1234",
                Coordinates.of(0, 0),
                "서울시");
        cityRepository.save(parentParent);
        Gu parent = Gu.create(
                "D1234",
                parentParent,
                Coordinates.of(0, 0),
                "은평구");
        guRepository.save(parent);
        String regionId = "testId";
        Dong dong = Dong.create(
                regionId,
                parent,
                Coordinates.of(37.4839238f, 42.4983954f),
                "역촌동");
        // when
        dongRepository.save(dong);
        // then
        Dong savedDong = dongRepository.findById(regionId).orElseThrow();
        Assertions.assertThat(savedDong).isEqualTo(dong);
    }

    @Test
    void 동_복수_저장_성공() {
        // given
        City parentParent = City.create(
                "C1234",
                Coordinates.of(0, 0),
                "서울시");
        cityRepository.save(parentParent);
        Gu parent = Gu.create(
                "D1234",
                parentParent,
                Coordinates.of(0, 0),
                "은평구");
        guRepository.save(parent);
        String regionId1 = "testId1";
        String regionId2 = "testId2";
        String regionId3 = "testId3";
        List<Dong> dongs = new ArrayList<>();
        dongs.add(
                Dong.create(
                        regionId1,
                        parent,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "역촌동"));
        dongs.add(
                Dong.create(
                        regionId2,
                        parent,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "불광동"));
        dongs.add(
                Dong.create(
                        regionId3,
                        parent,
                        Coordinates.of(37.4839238f, 42.4983954f),
                        "신사동"));
        // when
        dongRepository.saveAll(dongs);
        // then
        List<Dong> savedDongs = dongRepository.findAll();
        Assertions.assertThat(savedDongs.size()).isEqualTo(dongs.size());
        Assertions.assertThat(savedDongs.get(0).getRegionId()).isEqualTo(regionId1);
        Assertions.assertThat(savedDongs.get(1).getRegionId()).isEqualTo(regionId2);
        Assertions.assertThat(savedDongs.get(2).getRegionId()).isEqualTo(regionId3);
    }
}
