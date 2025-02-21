package com.api.casadoconstrutor.horizonte.service;

import com.api.casadoconstrutor.horizonte.config.ArquivoConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ArquivoService {

    private final String uploadComp;

    public ArquivoService(ArquivoConfig arquivoConfig) {
        this.uploadComp = arquivoConfig.getUploadComp();
    }

    public String saveFile(MultipartFile file) throws IOException {
        // Verifica se o diretório existe, senão cria
        Path uploadPath = Paths.get(uploadComp);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Gera um nome único para o arquivo
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // Salva o arquivo no sistema de arquivos
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retorna o caminho relativo do arquivo
        return fileName;
    }

}
