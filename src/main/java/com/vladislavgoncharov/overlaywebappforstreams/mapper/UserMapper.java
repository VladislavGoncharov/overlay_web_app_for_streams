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
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password("")
                .matchingPassword("")
                .role(user.getRole())
                .drop(user.getDrop().getNumbersOfDrop())
                .addressDrop(user.getDrop().getAddressPicture())
                .rank(user.getRank().getNumbersOfRank())
                .addressRank(user.getRank().getAddressPicture())
                .picture(user.getPicture())
                .pictureId(user.getPicture().getId())
                .build();
    }

    default List<UserDTO> fromUserList(List<User> users) {
        return users.stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }

}
