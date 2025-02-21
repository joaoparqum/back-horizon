package com.api.casadoconstrutor.horizonte.service;

import com.api.casadoconstrutor.horizonte.model.Documento;
import com.api.casadoconstrutor.horizonte.repository.DocumentoRepository;
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
public class DocumentoService {

    private final String uploadDir = "uploadsVistas/";

    @Autowired
    DocumentoRepository documentoRepository;

    public Documento uploadFile(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        Documento documento = new Documento();
        documento.setNomeVista(fileName);
        documento.setTipoVista(file.getContentType());
        documento.setTamanhoVista(file.getSize());

        return documentoRepository.save(documento);
    }

    public byte[] downloadFile(Long id) throws IOException {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vista Explodida não encontrada!!"));

        Path filePath = Paths.get(uploadDir, documento.getNomeVista());
        return Files.readAllBytes(filePath);
    }

    public void deleteFile(Long id) throws IOException {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vista explodida não encontrada!!"));

        Path filePath = Paths.get(uploadDir, documento.getNomeVista());
        Files.deleteIfExists(filePath);

        documentoRepository.delete(documento);
    }

    public List<Documento> getAllDocuments() {
        return documentoRepository.findAll();
    }

    public List<Documento> findByNomeVista(String nomeVista) {
        return documentoRepository.findByNomeVistaContaining(nomeVista);
    }

    public Optional<Documento> findById(Long id) {
        return documentoRepository.findById(id);
    }



}
