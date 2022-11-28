package com.nodam.nodam.domain.entity.clinic;

import javax.persistence.*;

@Entity
public class Clinic {

    // PK
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Clinic Info
    private String address;
    private String callNumber;
    private String organization;
    private String region;
}
