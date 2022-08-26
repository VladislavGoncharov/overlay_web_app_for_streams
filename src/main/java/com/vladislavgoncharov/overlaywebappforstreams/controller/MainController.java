package com.vladislavgoncharov.overlaywebappforstreams.controller;

import com.vladislavgoncharov.overlaywebappforstreams.dto.UserDTO;
import com.vladislavgoncharov.overlaywebappforstreams.entity.Role;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import com.vladislavgoncharov.overlaywebappforstreams.service.ShowOverlayService;
import com.vladislavgoncharov.overlaywebappforstreams.service.UserService;
import com.vladislavgoncharov.overlaywebappforstreams.util.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.bind.ValidationException;
import java.security.Principal;

@Controller
public class MainController {

    private final ShowOverlayService showOverlayService;
    private final UserService userService;
    private final PictureService pictureService;

    public MainController(ShowOverlayService showOverlayService, UserService userService, PictureService pictureService) {
        this.showOverlayService = showOverlayService;
        this.userService = userService;
        this.pictureService = pictureService;
    }


    @RequestMapping("/")
    public String firstView(Model model) {

        model.addAttribute("link", new Link());
        model.addAttribute("valueSwitcherOverlay", showOverlayService.getValueSwitcher());
        model.addAttribute("listUsers", userService.findAllByRole(Role.USER));
        model.addAttribute("listPicture", pictureService.findAllCharacterPicture());

        return "firstView";
    }


    @RequestMapping("/change-player")
    public String changePlayer(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));

        return "change-player";
    }


    @PostMapping("/change-player/update-rank")
    public String updateRank(@ModelAttribute UserDTO userDTO, Model model, Principal principal) {
        try {
            userService.updateRank(userDTO);
        } catch (ValidationException e) {
            model.addAttribute("user", userService.findUserByUsername(principal.getName()));
            model.addAttribute("error", e.getMessage());
            return "change-player";
        }
        return "redirect:/change-player";
    }


    @PostMapping("/change-player/update-drop")
    public String updateDrop(@ModelAttribute UserDTO userDTO, Model model, Principal principal) {
        try {
            userService.updateDrop(userDTO);
        } catch (ValidationException e) {
            model.addAttribute("user", userService.findUserByUsername(principal.getName()));
            model.addAttribute("error", e.getMessage());
            return "change-player";
        }
        return "redirect:/change-player";
    }


}
