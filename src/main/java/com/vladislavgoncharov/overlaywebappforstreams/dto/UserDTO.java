package com.vladislavgoncharov.overlaywebappforstreams.dto;


import com.vladislavgoncharov.overlaywebappforstreams.entity.CharacterPicture;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfDrop;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfRank;
import com.vladislavgoncharov.overlaywebappforstreams.entity.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {

    private Long id;

    private String username;
    private String password;
    private String matchingPassword;
    private Role role;
    private int rank;
    private String addressRank;
    private int drop;
    private String addressDrop;
    private Long pictureId;
    private CharacterPicture picture;

    public static String getUsername(CharacterPicture picture, List<UserDTO> listUsers){
        for (UserDTO user : listUsers)
            if (user.getPicture().equals(picture))
                return user.getUsername();
        return "";
    }


}
