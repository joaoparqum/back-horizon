package com.api.casadoconstrutor.horizonte.service;

import com.api.casadoconstrutor.horizonte.model.Comprovante;
import com.api.casadoconstrutor.horizonte.repository.ComprovanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ComprovanteService {

    private final String uploadComp = "uploadsComp/";

    @Autowired
    ComprovanteRepository comprovanteRepository;

    public Comprovante uploadFile(MultipartFile file) throws IOException {
        // Cria o diretório de upload se não existir
        Path uploadPath = Paths.get(uploadComp);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Salva o arquivo no diretório
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // Arquiva no banco de dados
        Comprovante comprovante = new Comprovante();
        comprovante.setNomeComprovante(fileName);
        comprovante.setTipoComprovante(file.getContentType());
        comprovante.setTamanhoComprovante(file.getSize());

        return comprovanteRepository.save(comprovante);
    }

    public byte[] downloadFile(Long id) throws IOException {
        Comprovante comprovante = comprovanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprovante não encontrado!!"));

        Path filePath = Paths.get(uploadComp, comprovante.getNomeComprovante());
        return Files.readAllBytes(filePath);
    }

    public List<Comprovante> getAllComprovantes() {
        return comprovanteRepository.findAll();
    }

    public List<Comprovante> findByNomeComprovante(String nomeComprovante) {
        return comprovanteRepository.findByNomeComprovanteContaining(nomeComprovante);
    }

    public Optional<Comprovante> findById(Long id) {
        return comprovanteRepository.findById(id);
    }

}

