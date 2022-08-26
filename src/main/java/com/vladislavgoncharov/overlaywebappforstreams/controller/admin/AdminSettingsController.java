package com.vladislavgoncharov.overlaywebappforstreams.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminSettingsController {


    @RequestMapping("/settings")
    public String viewSettings() {

        return null;
    }
}
