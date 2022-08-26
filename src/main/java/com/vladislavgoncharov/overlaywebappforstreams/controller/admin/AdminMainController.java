package com.vladislavgoncharov.overlaywebappforstreams.controller.admin;

import com.vladislavgoncharov.overlaywebappforstreams.dto.UserDTO;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import com.vladislavgoncharov.overlaywebappforstreams.service.ShowOverlayService;
import com.vladislavgoncharov.overlaywebappforstreams.service.UserService;
import com.vladislavgoncharov.overlaywebappforstreams.util.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.ValidationException;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

    private final ShowOverlayService showOverlayService;
    private final UserService userService;
    private final PictureService pictureService;

    public AdminMainController(ShowOverlayService showOverlayService, UserService userService, PictureService pictureService) {
        this.showOverlayService = showOverlayService;
        this.userService = userService;
        this.pictureService = pictureService;
    }

    private void addModel(Model model,String username,String error){
        model.addAttribute("link", new Link());
        model.addAttribute("player",userService.findUserByUsername(username));
        model.addAttribute("allCharacterPicture", pictureService.findAllCharacterPicture());
        model.addAttribute("allUnoccupiedCharacters", pictureService.findAllUnoccupiedCharacters(username));
        model.addAttribute("error", error);
    }

    @PostMapping("/switcher-overlay")
    public String switcherShowOverlay(@ModelAttribute Link link) {
        showOverlayService.overlaySwitch();
        return "redirect:" + link.getAddress();
    }

    @RequestMapping("/{username}")
    public String changePlayerForAdmin(@PathVariable String username, Model model){
        addModel(model,username,null);
        return "change-player-for-admin";
    }

    @PostMapping("/{username}/update-rank")
    public String updateRank(@ModelAttribute UserDTO userDTO, Model model) {
        System.out.println(1);
        try {
            userService.updateRank(userDTO);
        } catch (ValidationException e) {
            addModel(model,userDTO.getUsername(),e.getMessage());
            return "change-player-for-admin";
        }
        System.out.println(2);

        return "redirect:/admin/" + userDTO.getUsername();
    }


    @PostMapping("/{username}/update-drop")
    public String updateDrop(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            userService.updateDrop(userDTO);
        } catch (ValidationException e) {
            addModel(model,userDTO.getUsername(),e.getMessage());
            return "change-player-for-admin";
        }
        return "redirect:/admin/" + userDTO.getUsername();
    }

    @PostMapping("/{username}/update-user")
    public String updateUser(@ModelAttribute UserDTO userDTO, Model model) {
        try {
            userService.updateUser(userDTO);
        } catch (ValidationException e) {
            addModel(model,userDTO.getUsername(),e.getMessage());
            return "change-player-for-admin";
        }
        return "redirect:/admin/" + userDTO.getUsername();
    }
}
