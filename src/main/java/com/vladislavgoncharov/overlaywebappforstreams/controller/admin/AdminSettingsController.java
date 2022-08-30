package com.vladislavgoncharov.overlaywebappforstreams.controller.admin;

import com.vladislavgoncharov.overlaywebappforstreams.configuration.GifStorageCloudinary;
import com.vladislavgoncharov.overlaywebappforstreams.entity.CharacterPicture;
import com.vladislavgoncharov.overlaywebappforstreams.entity.FontLink;
import com.vladislavgoncharov.overlaywebappforstreams.service.FontService;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import com.vladislavgoncharov.overlaywebappforstreams.service.ShowOverlayService;
import com.vladislavgoncharov.overlaywebappforstreams.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;

@Controller
@MultipartConfig
@RequestMapping("/admin")
public class AdminSettingsController {

    private final FontService fontService;
    private final ShowOverlayService showOverlayService;
    private final PictureService pictureService;
    private final UserService userService;

    public AdminSettingsController(FontService fontService, ShowOverlayService showOverlayService, PictureService pictureService, UserService userService) {
        this.fontService = fontService;
        this.showOverlayService = showOverlayService;
        this.pictureService = pictureService;
        this.userService = userService;
    }

    @PostMapping("/switcher-overlay")
    public String switcherShowOverlay() {
        showOverlayService.overlaySwitch();
        return "redirect:/";
    }

    @RequestMapping("/settings")
    public String viewSettings(Model model) {
        model.addAttribute("font", fontService.getFont());
        model.addAttribute("allUser",userService.findAll());
        return "admin-settings";
    }


    @RequestMapping("/settings/gif")
    public String uploadGif(Model model) {
        model.addAttribute("font", fontService.getFont());
        model.addAttribute("newGif",new CharacterPicture());
        model.addAttribute("gif", GifStorageCloudinary.getGifURL());
        return "admin-settings-gif";
    }

    @PostMapping("/settings/gif/upload")
    public String saveGif(@RequestParam("saveGif") MultipartFile file,Model model) {
        try {
            pictureService.saveGif(file);
        } catch (IOException e) {
            model.addAttribute("font", fontService.getFont());
            model.addAttribute("newGif",new CharacterPicture());
            model.addAttribute("gif", GifStorageCloudinary.getGifURL());
            model.addAttribute("error",e.getMessage());
            return "admin-settings-gif";
        }
        return "redirect:/admin/settings/gif";
    }

    @RequestMapping("/settings/font")
    public String uploadFont(Model model) {
        model.addAttribute("font", fontService.getFont());
        model.addAttribute("newFont",new FontLink());
        return "admin-settings-font";
    }

    @PostMapping("/settings/font/upload")
    public String saveFont(@ModelAttribute FontLink font) {
        fontService.saveFont(font);
        return "redirect:/admin/settings/font";
    }
}
