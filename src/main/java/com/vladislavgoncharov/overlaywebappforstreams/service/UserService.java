package com.vladislavgoncharov.overlaywebappforstreams.service;

import com.vladislavgoncharov.overlaywebappforstreams.dto.UserDTO;
import com.vladislavgoncharov.overlaywebappforstreams.entity.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDTO> findAll();
    List<UserDTO> findAllByRole(Role role);

    UserDTO findUserByUsername(String name);

    void updateRank(UserDTO userDTO) throws ValidationException;

    void updateDrop(UserDTO userDTO) throws ValidationException;

    void updateUser(UserDTO userDTO) throws ValidationException;
}
