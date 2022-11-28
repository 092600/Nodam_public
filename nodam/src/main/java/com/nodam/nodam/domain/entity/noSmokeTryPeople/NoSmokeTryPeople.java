package com.nodam.nodam.domain.entity.noSmokeTryPeople;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class NoSmokeTryPeople {

    @Id
    private LocalDateTime createdDate = LocalDateTime.now();

    private Long tryPeopleAmount;
    private Long successPeopleAmount;
}
