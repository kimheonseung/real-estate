package com.devh.project.realestate.configuration;

import com.devh.project.realestate.domain.complex.entity.Complex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.index.PutTemplateRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
@Slf4j
public class TemplateInitializer {
    private final ElasticsearchOperations elasticsearchOperations;

    public TemplateInitializer(
            @Qualifier("elasticsearchOperations") ElasticsearchOperations elasticsearchOperations
    )
    {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Autowired
    public void setup()
    {
        var indexOps = elasticsearchOperations.indexOps(Complex.class);
        String templateName = "complex";

        try
        {
            indexOps.deleteTemplate(templateName);
        }
        catch (Exception e)
        {
            log.warn("delete template failed. {}", e.getMessage());
        }

        var mapping = indexOps.createMapping();

        String templatePattern = "complex*";
        var request = PutTemplateRequest.builder(templateName, templatePattern)
                .withMappings(mapping)
                .build();

        indexOps.putTemplate(request);

        log.info("Complete to create template... alias : {}, index patterns: {}"
                , templateName
                , Arrays.toString(request.getIndexPatterns()));
        log.info(Objects.requireNonNull(request.getMappings()).toJson());
    }
}
