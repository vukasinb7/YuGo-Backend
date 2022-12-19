package org.yugo.backend.YuGo.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import java.io.InputStream;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @GetMapping(
            value = ""
    )
    ResponseEntity<InputStreamResource> getImage(@RequestParam(name = "image_path") String imgPath){
        if(imgPath == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        String imageExtension = getImageExtension(imgPath);
        if(imageExtension == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        MediaType contentType;
        if(imageExtension.equals("jpg") || imageExtension.equals("jpeg")){
            contentType = MediaType.IMAGE_JPEG;
        } else if (imageExtension.equals("png")) {
            contentType = MediaType.IMAGE_PNG;
        }else {
            contentType = MediaType.IMAGE_JPEG;
        }
        InputStream in = getClass().getResourceAsStream("src/main/resources/static/img/vehicle_type/car_model.png");
        if(in == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().contentType(contentType).body(new InputStreamResource(in));
    }

    private String getImageExtension(String imgPath){
        int index = imgPath.lastIndexOf('.');
        if(index == -1){
            return null;
        }
        String ext = imgPath.substring(index + 1);
        if(ext.isBlank()){
            return null;
        }
        return ext;
    }
}
