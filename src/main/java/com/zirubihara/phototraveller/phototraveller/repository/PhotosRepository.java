package com.zirubihara.phototraveller.phototraveller.repository;

import com.zirubihara.phototraveller.phototraveller.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long> {

    Optional<Photos> findByName(String photosName);
}
