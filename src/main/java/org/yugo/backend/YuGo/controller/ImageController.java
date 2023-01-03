package org.yugo.backend.YuGo.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
@RequestMapping(value = "/api/image")
public class ImageController {

    @GetMapping(
            value = "/{name}"
    )
    ResponseEntity<byte[]> getImage(@PathVariable String name){

        if(name == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(name);
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

}
