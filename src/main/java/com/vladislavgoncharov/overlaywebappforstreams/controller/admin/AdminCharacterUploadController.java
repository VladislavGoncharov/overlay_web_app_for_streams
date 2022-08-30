package com.vladislavgoncharov.overlaywebappforstreams.controller.admin;

import com.vladislavgoncharov.overlaywebappforstreams.configuration.GifStorageCloudinary;
import com.vladislavgoncharov.overlaywebappforstreams.entity.CharacterPicture;
import com.vladislavgoncharov.overlaywebappforstreams.service.FontService;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import com.vladislavgoncharov.overlaywebappforstreams.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@Controller
@MultipartConfig
@RequestMapping("/admin/settings")
public class AdminCharacterUploadController {

    private final FontService fontService;
    private final PictureService pictureService;

    public AdminCharacterUploadController(FontService fontService, PictureService pictureService) {
        this.fontService = fontService;
        this.pictureService = pictureService;
    }

    @RequestMapping("/character")
    public String uploadCharacter(Model model) {
        model.addAttribute("font", fontService.getFont());
        model.addAttribute("newCharacter",new CharacterPicture());
        model.addAttribute("allCharacter",pictureService.findAllCharacterPicture());

        return "admin-settings-character-pic";
    }

    @PostMapping("/character/upload")
    public String saveCharacter(@RequestParam("saveCharacterSmall") MultipartFile fileSmall,
                                @RequestParam("saveCharacterBig") MultipartFile fileBig,Model model) {
        try {
            pictureService.saveCharacterPicture(fileSmall,fileBig);
        } catch (IOException e) {
            model.addAttribute("font", fontService.getFont());
            model.addAttribute("newCharacter",new CharacterPicture());
            model.addAttribute("allCharacter",pictureService.findAllCharacterPicture());
            model.addAttribute("error",e.getMessage());
            return "admin-settings-character-pic";
        }
        return "redirect:/admin/settings/character";
    }

    @GetMapping("/character/delete-{id}")
    public String saveCharacter(@PathVariable Long id) {
        pictureService.deleteCharacterPicture(id);
        return "redirect:/admin/settings/character";
    }

    @GetMapping("/character/died-{id}")
    public String updateDied(@PathVariable Long id, Model model) {
        try {
            pictureService.updateIsDeadCharacter(id);
        } catch (ValidationException e) {
            model.addAttribute("font", fontService.getFont());
            model.addAttribute("newCharacter",new CharacterPicture());
            model.addAttribute("allCharacter",pictureService.findAllCharacterPicture());
            model.addAttribute("error",e.getMessage());
            return "admin-settings-character-pic";
        }
        return "redirect:/admin/settings/character";
    }

    @GetMapping("/character/died-all")
    public String updateDiedAll() {
        pictureService.updateIsDeadAllCharacterValueFalse();
        return "redirect:/admin/settings/character";
    }

}
