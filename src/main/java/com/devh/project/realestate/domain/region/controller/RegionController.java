package com.devh.project.realestate.domain.region.controller;

import com.devh.project.common.constant.ApiStatus;
import com.devh.project.common.dto.ApiResponse;
import com.devh.project.realestate.domain.region.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping
    public ApiResponse<Boolean> post() {
        regionService.upsertAll();
        return ApiResponse.success(ApiStatus.Success.OK, true);
    }
}
