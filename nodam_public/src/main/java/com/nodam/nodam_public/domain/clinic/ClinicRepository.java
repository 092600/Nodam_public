package com.nodam.nodam_public.domain.clinic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
 
    List<Clinic> findClinicByRegion(String Region);

    Page<Clinic> findAllByRegion(Pageable pageable, String region);
    
}
