package com.devh.project.realestate.domain.region.repository;

import com.devh.project.realestate.domain.region.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {

}
