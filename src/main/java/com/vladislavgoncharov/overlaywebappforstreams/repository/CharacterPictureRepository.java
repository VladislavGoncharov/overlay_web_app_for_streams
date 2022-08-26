package com.vladislavgoncharov.overlaywebappforstreams.repository;

import com.vladislavgoncharov.overlaywebappforstreams.entity.CharacterPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterPictureRepository extends JpaRepository<CharacterPicture,Long> {
}
