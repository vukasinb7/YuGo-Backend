package org.yugo.backend.YuGo.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yugo.backend.YuGo.annotation.AuthorizeSelfAndAdmin;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.service.DocumentService;
import org.yugo.backend.YuGo.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {
    private final DocumentService documentService;
    private final UserService userService;
    @Autowired
    public ImageController(DocumentService documentService, UserService userService){
        this.documentService=documentService;
        this.userService = userService;
    }

    @GetMapping(
            value = "/{name}"
    )
    ResponseEntity<byte[]> getImage(@NotBlank(message = "Field (name) is required")
                                    @PathVariable String name){

        if(name == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(name.contains("/") || name.contains("..")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        File img = new File("./src/main/resources/img/" + name);
        if(img.exists() && !img.isDirectory()){
            try {
                byte[] imgBytes = Files.readAllBytes(img.toPath());
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(Files.probeContentType(img.toPath())))
                        .body(imgBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @GetMapping(
            value = "/document/{name}"
    )
    ResponseEntity<byte[]> getDocumentPicture(@PathVariable String name){
        File img = new File("./src/main/resources/documents/" + name);
        if(img.exists() && !img.isDirectory()){
            try {
                byte[] imgBytes = Files.readAllBytes(img.toPath());
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(Files.probeContentType(img.toPath())))
                        .body(imgBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new NotFoundException("Document not found!");
    }

    @GetMapping(
            value = "/profilePicture/{name}"
    )
    ResponseEntity<byte[]> getProfilePicture(@PathVariable String name){
        File img = new File("./src/main/resources/profilePictures/" + name);
        if(img.exists() && !img.isDirectory()){
            try {
                byte[] imgBytes = Files.readAllBytes(img.toPath());
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(Files.probeContentType(img.toPath())))
                        .body(imgBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new NotFoundException("Profile picture not found!");
    }

    @PostMapping(value = "/{id}/profilePicture",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER', 'PASSENGER')")
    @AuthorizeSelfAndAdmin(pathToUserId = "[0]", message = "User not found!")
    public ResponseEntity uploadProfilePicture(@NotNull(message = "Field (id) is required")
                                               @Positive(message = "Id must be positive")
                                               @PathVariable(value="id") Integer id,
                                               @NotNull(message = "Field (file) is required")
                                               @RequestParam("image") MultipartFile file)
            throws IOException {
        String pictureName = file.hashCode()+".jpg";
        String path="src\\main\\resources\\profilePictures\\" + pictureName;
        Files.write(Paths.get(path),file.getBytes());
        HashMap<String, String> response = new HashMap<>();
        response.put("pictureName", pictureName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
