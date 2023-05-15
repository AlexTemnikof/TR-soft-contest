package com.example.contestapp.core.controller;

import com.example.contestapp.core.dto.UserDTO;
import com.example.contestapp.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/{id}/upload", consumes = {
            "multipart/form-data"
    })
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public UserDTO uploadImage(@PathVariable("id") String id, @RequestParam("file") MultipartFile file) throws Exception {
        return service.addImageAttachment(file, id);
    }

    @GetMapping(value = "/{id}")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public UrlResource getImage(@PathVariable("id") String id) throws Exception {
        return service.getImageByUserId(id);
    }

    @PatchMapping(value = "/{id}/update", consumes = {
            "multipart/form-data"
    })
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public UserDTO updateImage(@PathVariable("id") String id, @RequestParam("file") MultipartFile file) throws Exception {
        return service.updateImageById(file, id);
    }

    @DeleteMapping(value = "/{id}/delete")
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<String> deleteImage(@PathVariable("id") String id) throws Exception {
        service.deleteImageById(id);
        return ResponseEntity.ok().build();
    }
}
