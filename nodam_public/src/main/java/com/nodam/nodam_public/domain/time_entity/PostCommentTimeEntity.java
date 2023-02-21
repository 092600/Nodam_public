package com.nodam.nodam_public.domain.time_entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Embeddable;

@Embeddable
public class PostCommentTimeEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
}
