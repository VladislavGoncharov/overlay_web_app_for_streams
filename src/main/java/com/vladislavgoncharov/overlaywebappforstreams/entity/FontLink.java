package com.vladislavgoncharov.overlaywebappforstreams.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "font_link")
public class FontLink {

    @Id
    private Long id;
    private String fontAddress;
    private String fontName;
}
