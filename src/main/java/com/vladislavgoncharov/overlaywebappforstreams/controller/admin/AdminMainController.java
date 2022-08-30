package com.vladislavgoncharov.overlaywebappforstreams.controller.admin;

import com.vladislavgoncharov.overlaywebappforstreams.dto.UserDTO;
import com.vladislavgoncharov.overlaywebappforstreams.service.FontService;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import com.vladislavgoncharov.overlaywebappforstreams.service.ShowOverlayService;
import com.vladislavgoncharov.overlaywebappforstreams.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

    private final FontService fontService;
    private final UserService userService;
    private final PictureService pictureService;

    public AdminMainController(FontService fontService, UserService userService, PictureService pictureService) {
        this.fontService = fontService;
        this.userService = userService;
        this.pictureService = pictureService;
    }

    private void addModel(Model model,String username,String error){
        model.addAttribute("font", fontService.getFont());
        model.addAttribute("player",userService.findUserByUsername(username));
        model.addAttribute("allCharacterPicture", pictureService.findAllCharacterPicture());
        model.addAttribute("allUnoccupiedCharacters", pictureService.findAllUnoccupiedCharacters(username));
        model.addAttribute("error", error);
    }

    @RequestMapping("/{username}")
    public String changePlayerForAdmin(@PathVariable String username, Model model){
        addModel(model,username,null);
        return "change-player-for-admin";
    }

    @PostMapping("/{username}/update-rank")
    public String updateRank(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            userService.updateRank(userDTO);
        } catch (ValidationException e) {
            addModel(model,userDTO.getUsername(),e.getMessage());
            return "change-player-for-admin";
        }

        return "forward:/admin/" + userDTO.getUsername();
    }


    @PostMapping("/{username}/update-drop")
    public String updateDrop(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            userService.updateDrop(userDTO);
        } catch (ValidationException e) {
            addModel(model,userDTO.getUsername(),e.getMessage());
            return "change-player-for-admin";
        }
        return "forward:/admin/" + userDTO.getUsername();
    }

    @PostMapping("/{username}/update-user")
    public String updateUser(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            userService.updateUser(userDTO);
        } catch (ValidationException e) {
            addModel(model,userDTO.getUsername(),e.getMessage());
            return "change-player-for-admin";
        }

        return "forward:/admin/" + userDTO.getUsername();
    }
}
