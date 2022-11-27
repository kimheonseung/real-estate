package com.devh.project.realestate.domain.region.repository;

import com.devh.project.realestate.domain.region.entity.Dong;
import com.devh.project.realestate.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DongRepository extends JpaRepository<Dong, String> {
    List<Dong> findAllByParent(Region parent);
}
