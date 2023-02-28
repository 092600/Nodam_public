package com.nodam.nodam_public.domain.user.certificate;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.nodam.nodam_public.domain.user.User;

import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class UserCertificationInfo {

    @Id @GeneratedValue
    @Column(name = "user_certificate_id")
    private Long id;

    @OneToOne(mappedBy = "certificationInfo")
    private User certification_id;


    @Column
    private Integer certificationNumber;

    @Temporal(TemporalType.TIMESTAMP) 
    private Date certificateDate;

    public UserCertificationInfo(Integer certificationNumber, Date certificateDate) {
        this.certificationNumber = certificationNumber;
        this.certificateDate = certificateDate;
    }
}
