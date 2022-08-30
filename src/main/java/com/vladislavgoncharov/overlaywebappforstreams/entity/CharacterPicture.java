package com.vladislavgoncharov.overlaywebappforstreams.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "character_picture")
public class CharacterPicture {
    private static final String SEQ_NAME = "seq_character_picture";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;

    private String addressOfSmallPicture;
    private String addressOfBigPicture;
    private boolean isDied;

}
