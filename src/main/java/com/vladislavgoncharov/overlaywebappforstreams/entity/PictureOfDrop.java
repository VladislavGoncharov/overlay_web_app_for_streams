package com.vladislavgoncharov.overlaywebappforstreams.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "picture_of_drop")
public class PictureOfDrop {
    private static final String SEQ_NAME = "seq_picture_of_drop";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;

    private int numbersOfDrop;
    private String addressPicture;

}
