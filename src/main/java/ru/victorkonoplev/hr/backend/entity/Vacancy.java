package ru.victorkonoplev.hr.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "vacancy", schema = "public")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lng")
    private Float lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
