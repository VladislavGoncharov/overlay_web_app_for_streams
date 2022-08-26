package com.vladislavgoncharov.overlaywebappforstreams.repository;

import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfDrop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureOfDropRepository extends JpaRepository<PictureOfDrop, Long> {
    PictureOfDrop getByNumbersOfDrop(int number);
}
