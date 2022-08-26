package com.vladislavgoncharov.overlaywebappforstreams.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class User {
    private static final String SEQ_NAME = "seq_users";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    private PictureOfRank rank;
    @OneToOne
    private PictureOfDrop drop;
    @OneToOne
    private CharacterPicture picture;


}
