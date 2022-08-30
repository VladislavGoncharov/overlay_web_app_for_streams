package com.vladislavgoncharov.overlaywebappforstreams.controller;

import com.vladislavgoncharov.overlaywebappforstreams.service.FontService;
import com.vladislavgoncharov.overlaywebappforstreams.util.ReloadPageAfterUpdateDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckUpdateDBRestController {

    @PostMapping("/is_update_db")
    public ResponseEntity<Boolean> isUpdateDB() {
        return new ResponseEntity<>(ReloadPageAfterUpdateDB.isUpdateDB(), HttpStatus.OK);
    }

    @PostMapping("/value_update_db_false")
    public void valueUpdateDBFalse() {
        Thread thread = new Thread(new ReloadPageAfterUpdateDB());
        thread.start();
    }

}
