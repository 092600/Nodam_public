package com.nodam.nodam_public.domain.no_smoke;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class NoSmokeTryPeople {

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "date", updatable = false)
    private Date date;

    @Column(nullable = false)
    private String amountOfTryPeople;
    
    @Column(nullable = false)
    private String amountOfQuitPeople;
    
}
