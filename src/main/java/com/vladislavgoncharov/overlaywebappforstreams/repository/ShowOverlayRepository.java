package com.vladislavgoncharov.overlaywebappforstreams.repository;

import com.vladislavgoncharov.overlaywebappforstreams.entity.ShowOverlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowOverlayRepository extends JpaRepository<ShowOverlay,Long> {
}
