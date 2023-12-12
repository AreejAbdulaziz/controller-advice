package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Publisher;
import com.example.capstone2.Repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.AccessException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public List<Publisher> getAllPublisher(){
        return publisherRepository.findAll();
    }
    public void addPublisher(Publisher publisher){
        publisherRepository.save(publisher);
    }
    public void updatePublisher(Integer id,Publisher publisher){
        Publisher oldPublisher=publisherRepository.findPublisherByPubId(id);
        if(oldPublisher==null){
            throw new ApiException("wrong publisher id");
        }
        oldPublisher.setName(publisher.getName());
        oldPublisher.setAddress(publisher.getAddress());

        publisherRepository.save(oldPublisher);
    }
    public void deletePublisher(Integer id){
        Publisher publisher=publisherRepository.findPublisherByPubId(id);
        if(publisher==null){
            throw new ApiException("wrong publisher id");
        }
        publisherRepository.delete(publisher);
    }
}
