package com.api.casadoconstrutor.horizonte.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ArquivoConfig {

    @Value("${file.upload-comp}")
    private String uploadComp;

}
