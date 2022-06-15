package com.shop.magazine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PHOTO")
@NoArgsConstructor
@Getter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "photo_name")
    private String name;


    @Column(name = "ph_update_date")
    private LocalDateTime updateDate;

    @Column(name = "ph_upload")
    private LocalDateTime upload;
}
