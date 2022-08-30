package com.vladislavgoncharov.overlaywebappforstreams.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vladislavgoncharov.overlaywebappforstreams.configuration.GifStorageCloudinary;
import com.vladislavgoncharov.overlaywebappforstreams.entity.*;
import com.vladislavgoncharov.overlaywebappforstreams.repository.CharacterPictureRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.PictureOfDropRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.PictureOfRankRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.UserRepository;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import com.vladislavgoncharov.overlaywebappforstreams.util.ReloadPageAfterUpdateDB;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final Cloudinary cloudinary;
    private final GifStorageCloudinary gif;
    private final PictureOfDropRepository pictureOfDropRepository;
    private final PictureOfRankRepository pictureOfRankRepository;
    private final CharacterPictureRepository characterPictureRepository;
    private final UserRepository userRepository;

    public PictureServiceImpl(Cloudinary cloudinary, GifStorageCloudinary gif, PictureOfDropRepository pictureOfDropRepository, PictureOfRankRepository pictureOfRankRepository, CharacterPictureRepository characterPictureRepository, UserRepository userRepository) {
        this.cloudinary = cloudinary;
        this.gif = gif;
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
        characterPictures.removeIf(CharacterPicture::isDied);
        return characterPictures;
    }

    @Override
    public void saveGif(MultipartFile multipartFile) throws IOException {

        File file = new File("gif_" + multipartFile.getOriginalFilename());
        try (InputStream inputStreamReader = multipartFile.getInputStream();
             OutputStream outputStream = new FileOutputStream(file)) {
            IOUtils.copy(inputStreamReader, outputStream);
        }

        Map uploadResult = cloudinary.uploader()
                .upload(file, ObjectUtils.asMap("folder", "owerlay/Default gif"));

        file.delete();

        GifStorageCloudinary.setGifURL(String.valueOf(uploadResult.get("url")));

        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();

    }

    @Override
    public void saveCharacterPicture(MultipartFile fileSmall, MultipartFile fileBig) throws IOException {

        File small = new File("small_" + fileSmall.getOriginalFilename());
        try (InputStream inputStreamReader = fileSmall.getInputStream();
             OutputStream outputStream = new FileOutputStream(small)) {
            IOUtils.copy(inputStreamReader, outputStream);
        }

        File big = new File("big_" + fileBig.getOriginalFilename());
        try (InputStream inputStreamReader = fileBig.getInputStream();
             OutputStream outputStream = new FileOutputStream(big)) {
            IOUtils.copy(inputStreamReader, outputStream);
        }

        Map uploadResultSmall = cloudinary.uploader()
                .upload(small, ObjectUtils.asMap("folder", "owerlay/character_small"));

        Map uploadResultBig = cloudinary.uploader()
                .upload(big, ObjectUtils.asMap("folder", "owerlay/character_big"));

        small.delete();
        big.delete();

        characterPictureRepository.save(CharacterPicture.builder()
                .addressOfSmallPicture(String.valueOf(uploadResultSmall.get("url")))
                .addressOfBigPicture(String.valueOf(uploadResultBig.get("url")))
                .isDied(false)
                .build());


        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();
    }

    @Override
    public void deleteCharacterPicture(Long id) {

        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            if (user.getPicture() != null && Objects.equals(user.getPicture().getId(), id)) {
                user.setPicture(null);
            }
        });
        userRepository.saveAll(users);
        characterPictureRepository.deleteById(id);

        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();

    }

    @Override
    public List<PictureOfDrop> findAllPictureOfDrop() {
        return pictureOfDropRepository.findAll();
    }

    @Override
    public List<PictureOfRank> findAllPictureOfRank() {
        return pictureOfRankRepository.findAll().stream()
                .sorted((o1, o2) -> {
                    if (o1.getId() > o2.getId()) return 1;
                    return -1;
                }).collect(Collectors.toList());
    }

    @Override
    public void updatePictureOfDrop(Long id, MultipartFile fileDrop) throws IOException {

        File drop = new File("drop_" + fileDrop.getOriginalFilename());
        try (InputStream inputStreamReader = fileDrop.getInputStream();
             OutputStream outputStream = new FileOutputStream(drop)) {
            IOUtils.copy(inputStreamReader, outputStream);
        }

        Map uploadResult = cloudinary.uploader()
                .upload(drop, ObjectUtils.asMap("folder", "owerlay/drop"));

        drop.delete();

        PictureOfDrop pictureOfDrop = pictureOfDropRepository.getById(id);
        pictureOfDrop.setAddressPicture(String.valueOf(uploadResult.get("url")));
        pictureOfDropRepository.save(pictureOfDrop);

        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();

    }

    @Override
    public void updatePictureOfRank(Long id, MultipartFile fileRank) throws IOException {


        File rank = new File("rank_" + fileRank.getOriginalFilename());
        try (InputStream inputStreamReader = fileRank.getInputStream();
             OutputStream outputStream = new FileOutputStream(rank)) {
            IOUtils.copy(inputStreamReader, outputStream);
        }

        Map uploadResult = cloudinary.uploader()
                .upload(rank, ObjectUtils.asMap("folder", "owerlay/rank"));

        rank.delete();

        PictureOfRank pictureOfRank = pictureOfRankRepository.getById(id);
        pictureOfRank.setAddressPicture(String.valueOf(uploadResult.get("url")));
        pictureOfRankRepository.save(pictureOfRank);

        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();
    }

    @Override
    public PictureOfDrop getPictureOfDropById(Long id) {
        return pictureOfDropRepository.getById(id);
    }

    @Override
    public PictureOfRank getPictureOfRankById(Long id) {
        return pictureOfRankRepository.getById(id);
    }

    @Override
    public void updateIsDeadCharacterValueTrue(Long id) {
        CharacterPicture character = characterPictureRepository.getById(id);
        character.setDied(true);
        characterPictureRepository.save(character);
    }

    @Override
    public void updateIsDeadAllCharacterValueFalse() {
        List<CharacterPicture> characters = characterPictureRepository.findAll();
        characters.forEach(character -> character.setDied(false));
        characterPictureRepository.saveAll(characters);
    }

    @Override
    public void updateIsDeadCharacter(Long id) throws ValidationException {
        List<User> users = userRepository.findAllByRole(Role.USER);
        for (User user: users){
            if (user.getPicture().getId().equals(id))
                throw new ValidationException("Данный персонаж жив");
        }

        CharacterPicture character = characterPictureRepository.getById(id);
        character.setDied(!character.isDied());
        characterPictureRepository.save(character);
    }
}
