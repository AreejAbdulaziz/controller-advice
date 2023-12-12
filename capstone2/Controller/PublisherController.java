package com.example.capstone2.Controller;

import com.example.capstone2.Model.Orders;
import com.example.capstone2.Model.Publisher;
import com.example.capstone2.Service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
    Logger logger= LoggerFactory.getLogger(PublisherController.class);
    @GetMapping("/get")
    public ResponseEntity getAllPublishers(){
        logger.info("controller get");
        return ResponseEntity.status(200).body(publisherService.getAllPublisher());
    }
    @PostMapping("/add")
    public ResponseEntity addPublisher(@RequestBody@Valid Publisher publisher){
        logger.info("controller add");
        publisherService.addPublisher(publisher);
        return ResponseEntity.status(200).body("publisher added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updatePublisher(@PathVariable Integer id,@RequestBody@Valid Publisher publisher){
        logger.info("controller update");
        publisherService.updatePublisher(id, publisher);
        return ResponseEntity.status(200).body("publisher updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePublisher(@PathVariable Integer id){
        logger.info("controller delete");
        publisherService.deletePublisher(id);
        return ResponseEntity.status(200).body("publisher deleted!");
    }
}
