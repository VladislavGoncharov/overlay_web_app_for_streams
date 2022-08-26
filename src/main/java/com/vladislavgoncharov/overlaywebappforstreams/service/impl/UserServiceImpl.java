package com.vladislavgoncharov.overlaywebappforstreams.service.impl;

import com.vladislavgoncharov.overlaywebappforstreams.dto.UserDTO;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfDrop;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfRank;
import com.vladislavgoncharov.overlaywebappforstreams.entity.Role;
import com.vladislavgoncharov.overlaywebappforstreams.entity.User;
import com.vladislavgoncharov.overlaywebappforstreams.mapper.UserMapper;
import com.vladislavgoncharov.overlaywebappforstreams.repository.CharacterPictureRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.PictureOfDropRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.PictureOfRankRepository;
import com.vladislavgoncharov.overlaywebappforstreams.repository.UserRepository;
import com.vladislavgoncharov.overlaywebappforstreams.service.UserService;
import com.vladislavgoncharov.overlaywebappforstreams.util.ReloadPageAfterUpdateDB;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final UserMapper MAPPER = UserMapper.MAPPER;

    private final UserRepository userRepository;
    private final PictureOfDropRepository dropRepository;
    private final PictureOfRankRepository rankRepository;
    private final CharacterPictureRepository characterPictureRepository;

    public UserServiceImpl(UserRepository userRepository, PictureOfDropRepository pictureOfDropRepository, PictureOfRankRepository pictureOfRankRepository, CharacterPictureRepository characterPictureRepository) {
        this.userRepository = userRepository;
        this.dropRepository = pictureOfDropRepository;
        this.rankRepository = pictureOfRankRepository;
        this.characterPictureRepository = characterPictureRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь " + username + " не найден");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserDTO> findAllByRole(Role role) {
        return MAPPER.fromUserList(userRepository.findAllByRole(role));
    }

    @Override
    public UserDTO findUserByUsername(String name) {
        return MAPPER.fromUser(userRepository.findUserByUsername(name));
    }

    @Override
    public void updateRank(UserDTO userDTO) throws ValidationException {
        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();

        PictureOfRank rank = rankRepository.getByNumbersOfRank(userDTO.getRank());
        if (rank == null)
            throw new ValidationException("Ранг " + userDTO.getRank() + " не существует");

        User user = userRepository.getById(userDTO.getId());
        user.setRank(rank);
        userRepository.save(user);
    }

    @Override
    public void updateDrop(UserDTO userDTO) throws ValidationException {
        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();

        PictureOfDrop drop = dropRepository.getByNumbersOfDrop(userDTO.getDrop());
        if (drop == null)
            throw new ValidationException("Капля " + userDTO.getDrop() + " не существует");

        User user = userRepository.getById(userDTO.getId());
        user.setDrop(drop);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws ValidationException{
        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();


        if (checkUsername(userDTO.getUsername(), userDTO.getId()))
            throw new ValidationException("Игрок с именем " + userDTO.getUsername() + " уже существует");

        if (!Objects.equals(userDTO.getPassword(),userDTO.getMatchingPassword()))
            throw new ValidationException("Пароли не совпадают");

        User user = userRepository.getById(userDTO.getId());
        user.setUsername(userDTO.getUsername());

        if (!userDTO.getPassword().isBlank())
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        if (!user.getPicture().getId().equals(userDTO.getPictureId()))
            user.setPicture(characterPictureRepository.getById(userDTO.getPictureId()));

        userRepository.save(user);
    }

    public boolean checkUsername(String username, Long id) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) return false;
        return !Objects.equals(id, user.getId());
    }
}
