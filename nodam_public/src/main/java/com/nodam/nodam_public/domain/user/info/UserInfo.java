package com.nodam.nodam_public.domain.user.info;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class UserInfo {

    @Column(nullable = true)
    private String gender;


    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date birthday;
    

    @Temporal(TemporalType.TIMESTAMP)
    private Date noSmokingDate;

}
