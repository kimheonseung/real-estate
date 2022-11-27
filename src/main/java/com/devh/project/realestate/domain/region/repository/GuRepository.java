package com.devh.project.realestate.domain.region.repository;

import com.devh.project.realestate.domain.region.entity.Gu;
import com.devh.project.realestate.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuRepository extends JpaRepository<Gu, String> {
    List<Gu> findAllByParent(Region parent);
}
