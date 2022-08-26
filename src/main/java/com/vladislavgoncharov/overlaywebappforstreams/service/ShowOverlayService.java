package com.vladislavgoncharov.overlaywebappforstreams.service;

import com.vladislavgoncharov.overlaywebappforstreams.entity.ShowOverlay;
import com.vladislavgoncharov.overlaywebappforstreams.repository.ShowOverlayRepository;
import com.vladislavgoncharov.overlaywebappforstreams.util.ReloadPageAfterUpdateDB;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowOverlayService {

    private final ShowOverlayRepository showOverlayRepository;

    public ShowOverlayService(ShowOverlayRepository showOverlayRepository) {
        this.showOverlayRepository = showOverlayRepository;
    }

    public void overlaySwitch(){
        ReloadPageAfterUpdateDB.valueIsUpdateDBTrue();

        ShowOverlay overlay = showOverlayRepository.getById(1L);
        overlay.setSwitcher(!overlay.isSwitcher());
        showOverlayRepository.save(overlay);
    }

    public boolean getValueSwitcher(){
        return showOverlayRepository.getById(1L).isSwitcher();
    }

}
