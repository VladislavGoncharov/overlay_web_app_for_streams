package com.vladislavgoncharov.overlaywebappforstreams.controller.admin;

import com.vladislavgoncharov.overlaywebappforstreams.configuration.GifStorageCloudinary;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfDrop;
import com.vladislavgoncharov.overlaywebappforstreams.service.FontService;
import com.vladislavgoncharov.overlaywebappforstreams.service.PictureService;
import com.vladislavgoncharov.overlaywebappforstreams.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;

@Controller
@MultipartConfig
@RequestMapping("/admin/settings")
public class AdminDropUploadController {

    private final FontService fontService;
    private final PictureService pictureService;

    public AdminDropUploadController( FontService fontService, PictureService pictureService) {
        this.fontService = fontService;
        this.pictureService = pictureService;
    }

    @RequestMapping("/drop")
    public String allDrop(Model model) {
        model.addAttribute("allDrop",pictureService.findAllPictureOfDrop());
        model.addAttribute("font", fontService.getFont());
        return "admin-settings-drop";
    }
    @RequestMapping("/drop/update-{id}")
    public String updateDrop(@PathVariable Long id, Model model) {
        model.addAttribute("updateDrop",pictureService.getPictureOfDropById(id));
        model.addAttribute("allDrop",pictureService.findAllPictureOfDrop());
        model.addAttribute("font", fontService.getFont());
        return "admin-settings-drop";
    }

    @PostMapping("/drop/upload")
    public String saveDrop(@ModelAttribute PictureOfDrop drop,@RequestParam("saveDrop") MultipartFile fileDrop, Model model) {
        try {
            pictureService.updatePictureOfDrop(drop.getId(), fileDrop);
        } catch (IOException e) {
            model.addAttribute("font", fontService.getFont());
            model.addAttribute("updateDrop",pictureService.getPictureOfDropById(drop.getId()));
            model.addAttribute("allDrop",pictureService.findAllPictureOfDrop());
            model.addAttribute("error",e.getMessage());
            return "admin-settings-drop";
        }
        return "redirect:/admin/settings/drop";
    }

}
