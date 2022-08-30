package com.vladislavgoncharov.overlaywebappforstreams.controller.admin;

import com.vladislavgoncharov.overlaywebappforstreams.configuration.GifStorageCloudinary;
import com.vladislavgoncharov.overlaywebappforstreams.entity.PictureOfRank;
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
public class AdminRankUploadController {

    private final FontService fontService;
    private final PictureService pictureService;

    public AdminRankUploadController(FontService fontService, PictureService pictureService) {
        this.fontService = fontService;
        this.pictureService = pictureService;
    }

    @RequestMapping("/rank")
    public String allRank(Model model) {
        model.addAttribute("font", fontService.getFont());
        model.addAttribute("allRank",pictureService.findAllPictureOfRank());
        return "admin-settings-rank";
    }
    @RequestMapping("/rank/update-{id}")
    public String uploadCharacter(@PathVariable Long id, Model model) {
        model.addAttribute("font", fontService.getFont());
        model.addAttribute("updateRank",pictureService.getPictureOfRankById(id));
        model.addAttribute("allRank",pictureService.findAllPictureOfRank());

        return "admin-settings-rank";
    }

    @PostMapping("/rank/upload")
    public String saveCharacter(@ModelAttribute PictureOfRank rank, @RequestParam("saveRank") MultipartFile fileRank, Model model) {
        try {
            pictureService.updatePictureOfRank(rank.getId(), fileRank);
        } catch (IOException e) {
            model.addAttribute("font", fontService.getFont());
            model.addAttribute("updateRank",pictureService.getPictureOfRankById(rank.getId()));
            model.addAttribute("allRank",pictureService.findAllPictureOfRank());
            model.addAttribute("error",e.getMessage());
            return "admin-settings-rank";
        }
        return "redirect:/admin/settings/rank";
    }

}
