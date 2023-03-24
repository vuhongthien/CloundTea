package com.service.cloudtea.controller.admin;

import com.service.cloudtea.model.ImageProduct;
import com.service.cloudtea.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("/api/cloud-tea")
public class ImageController {
    @Autowired
    ImageServiceImpl imageService;
    @GetMapping("/image/{pageNumber}/{pageSize}")
    public Page<ImageProduct> listAll(@PathVariable(name = "pageNumber") Integer pageNumber,
                                      @PathVariable(name = "pageSize") Integer pageSize){
        return imageService.findAllImage(PageRequest.of(pageNumber,pageSize));
    }
    @PostMapping("/image")
    public ImageProduct addImage(@RequestParam("image_name") MultipartFile file){
        ImageProduct image = new ImageProduct();
        image.setName(file.getName());
        image.setActive(1);
        image.setPath(file.getOriginalFilename());
        return imageService.create(image);
    }
    @PutMapping("/image")
    public ImageProduct editImage(@RequestParam("image_name") MultipartFile image,
                                 @RequestParam("Active")int active,
                                 @RequestParam("id") Long id){
        ImageProduct imageProduct = imageService.findById(id);
        imageProduct.setName(image.getName());
        imageProduct.setActive(active);
        imageProduct.setPath(image.getOriginalFilename());
        return imageService.create(imageProduct);
    }
    @DeleteMapping("/image")
    public ResponseEntity dropImage(@RequestParam("id") Long id){
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
