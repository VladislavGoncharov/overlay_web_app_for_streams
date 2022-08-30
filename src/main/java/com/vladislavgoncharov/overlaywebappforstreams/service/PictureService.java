package com.vladislavgoncharov.overlaywebappforstreams.service;

import com.vladislavgoncharov.overlaywebappforstreams.entity.CharacterPicture;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfDrop;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfRank;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;

public interface PictureService {

    List<CharacterPicture> findAllCharacterPicture();
    List<CharacterPicture> findAllUnoccupiedCharacters(String currentUser);
    void saveGif(MultipartFile file) throws IOException;
    void saveCharacterPicture(MultipartFile fileSmall, MultipartFile fileBig) throws IOException;

    void deleteCharacterPicture(Long id);

    List<PictureOfDrop> findAllPictureOfDrop();
    List<PictureOfRank> findAllPictureOfRank();

    void updatePictureOfDrop(Long id, MultipartFile fileDrop) throws IOException;
    void updatePictureOfRank(Long id, MultipartFile fileDrop) throws IOException;

    PictureOfDrop getPictureOfDropById(Long id);

    PictureOfRank getPictureOfRankById(Long id);

    void updateIsDeadCharacterValueTrue(Long id);
    void updateIsDeadAllCharacterValueFalse();

    void updateIsDeadCharacter(Long id) throws ValidationException;
}
