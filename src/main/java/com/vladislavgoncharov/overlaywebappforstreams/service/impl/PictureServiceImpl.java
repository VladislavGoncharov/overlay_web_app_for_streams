package com.vladislavgoncharov.overlaywebappforstreams.service.impl;

import com.vladislavgoncharov.overlaywebappforstreams.entity.CharacterPicture;
import com.vladislavgoncharov.overlaywebappforstreams.entity.Role;
import com.vladislavgoncharov.overlaywebappforstreams.entity.User;
import com.vladislavgoncharov.overlaywebappforstreams.repository.CharacterPictureRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.PictureOfDropRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.PictureOfRankRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.UserRepository;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureOfDropRepository pictureOfDropRepository;
    private final PictureOfRankRepository pictureOfRankRepository;
    private final CharacterPictureRepository characterPictureRepository;
    private final UserRepository userRepository;

    public PictureServiceImpl(PictureOfDropRepository pictureOfDropRepository, PictureOfRankRepository pictureOfRankRepository, CharacterPictureRepository characterPictureRepository, UserRepository userRepository) {
        this.pictureOfDropRepository = pictureOfDropRepository;
        this.pictureOfRankRepository = pictureOfRankRepository;
        this.characterPictureRepository = characterPictureRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<CharacterPicture> findAllCharacterPicture() {
        return characterPictureRepository.findAll();
    }

    @Override
    public List<CharacterPicture> findAllUnoccupiedCharacters(String currentUser) {
        List<CharacterPicture> characterPictures = characterPictureRepository.findAll();
        List<User> users = userRepository.findAllByRole(Role.USER);

        for (User user : users) {
            if (!user.getUsername().equals(currentUser))
                characterPictures.remove(user.getPicture());
        }

        return characterPictures;
    }
}
