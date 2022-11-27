package com.devh.project.realestate.domain.complex.service;

import com.devh.project.realestate.domain.complex.entity.Complex;
import com.devh.project.realestate.domain.complex.repository.ComplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplexService {
    private final ComplexRepository complexRepository;

    @Autowired
    public ComplexService(ComplexRepository complexRepository) {
        this.complexRepository = complexRepository;
    }

    public void saveAll(List<Complex> sales) {
        this.complexRepository.saveAll(sales);
    }

}
