package com.vladislavgoncharov.overlaywebappforstreams.mapper;

import com.vladislavgoncharov.overlaywebappforstreams.dto.UserDTO;
import com.vladislavgoncharov.overlaywebappforstreams.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    default UserDTO fromUser(User user) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password("")
                .matchingPassword("")
                .role(user.getRole())
                .isDead(false)
                .build();

        if (user.getDrop() !=null){
            userDTO.setDrop(user.getDrop().getNumbersOfDrop());
            userDTO.setAddressDrop(user.getDrop().getAddressPicture());
        }
        if (user.getRank() !=null){
            userDTO.setRank(user.getRank().getNumbersOfRank());
            userDTO.setAddressRank(user.getRank().getAddressPicture());
        }
        if (user.getPicture() !=null){
            userDTO.setPicture(user.getPicture());
            userDTO.setPictureId(user.getPicture().getId());
        }
        return userDTO;
    }

    default List<UserDTO> fromUserList(List<User> users) {
        return users.stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }

}
