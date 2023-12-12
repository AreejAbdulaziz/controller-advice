package com.example.capstone2.Repository;

import com.example.capstone2.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
    Publisher findPublisherByPubId(Integer id);
}
