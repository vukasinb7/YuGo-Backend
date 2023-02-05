package org.yugo.backend.YuGo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yugo.backend.YuGo.exception.BadRequestException;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.Document;
import org.yugo.backend.YuGo.repository.DocumentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @Override
    public Document insert(Document document) throws IOException {
        return documentRepository.save(document);
    }

    @Override
    public Document upload(Document document, MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String extension=fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg"))
            throw new BadRequestException("File is not an image!");
        if (file.getSize()>5000000)
            throw new BadRequestException("File is bigger than 5mb!");

        String path="src\\main\\resources\\documents\\"+document.getName();
        Files.write(Paths.get(path),file.getBytes());
        return documentRepository.save(document);
    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public Document get(Integer id) {
        Optional<Document> doc = documentRepository.findById(id);
        if(doc.isEmpty()){
            throw new NotFoundException("Document does not exist!");
        }
        return doc.get();
    }

    @Override
    public void delete(Integer id){
        get(id);
        documentRepository.deleteById(id);
    }
    @Override
    @Transactional
    public void deleteAllForDriver(Integer driverId) {
        documentRepository.deleteAllByDriverId(driverId);
    }
}
