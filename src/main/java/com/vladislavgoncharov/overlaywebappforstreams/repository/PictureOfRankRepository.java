package com.vladislavgoncharov.overlaywebappforstreams.repository;

import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureOfRankRepository extends JpaRepository<PictureOfRank, Long> {

    PictureOfRank getByNumbersOfRank(int number);
}
