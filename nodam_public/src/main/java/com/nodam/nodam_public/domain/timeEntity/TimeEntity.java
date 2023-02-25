package com.nodam.nodam_public.domain.timeEntity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class TimeEntity {
    
    @Column(name = "created_date", updatable = false)
    @CreatedDate()
    private LocalDateTime createdDate;

    @Column(name = "modified_date", updatable = true)
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}