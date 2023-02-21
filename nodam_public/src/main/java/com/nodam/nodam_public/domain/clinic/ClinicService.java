package com.nodam.nodam_public.domain.clinic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nodam.nodam_public.domain.clinic.search.SearchClinicDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;
    
    public List<Clinic> findByClinicRegion(String region){
        return clinicRepository.findClinicByRegion(region);
    }

    public Page<Clinic> findAllByRegion(SearchClinicDto searchClinicDto){
        Pageable pageable = PageRequest.of(searchClinicDto.getPage(), searchClinicDto.getRecordSize());
        return clinicRepository.findAllByRegion(pageable, searchClinicDto.getRegion());
    }

}
