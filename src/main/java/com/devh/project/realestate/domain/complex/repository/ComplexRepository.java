package com.devh.project.realestate.domain.complex.repository;

import com.devh.project.realestate.domain.complex.entity.Complex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplexRepository extends ElasticsearchRepository<Complex, String> {
}
