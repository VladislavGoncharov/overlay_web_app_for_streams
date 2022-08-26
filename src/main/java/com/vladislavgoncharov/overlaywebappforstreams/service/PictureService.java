package com.vladislavgoncharov.overlaywebappforstreams.service;

import com.vladislavgoncharov.overlaywebappforstreams.entity.CharacterPicture;

import java.util.List;

public interface PictureService {

    List<CharacterPicture> findAllCharacterPicture();
    List<CharacterPicture> findAllUnoccupiedCharacters(String currentUser);
}
