package com.vladislavgoncharov.overlaywebappforstreams.service;

import com.vladislavgoncharov.overlaywebappforstreams.entity.FontLink;
import com.vladislavgoncharov.overlaywebappforstreams.repository.FontRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@Transactional
public class FontService {

    private final FontRepository fontRepository;


    public FontService(FontRepository fontRepository) {
        this.fontRepository = fontRepository;
    }

    public FontLink getFont(){
        return fontRepository.getById(1L);
    }

    public void saveFont(FontLink font) {
        font.setId(1L);
        fontRepository.save(font);
    }

}
