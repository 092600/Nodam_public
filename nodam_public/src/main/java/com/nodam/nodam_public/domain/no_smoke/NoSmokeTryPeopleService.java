package com.nodam.nodam_public.domain.no_smoke;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoSmokeTryPeopleService {
    
    private final NoSmokeTryPeopleRepository noSmokeTryPeopleRepository;

    public Optional<NoSmokeTryPeople> getTodayNoSmokeTryPeople() {
        LocalDate localDate = LocalDate.now();
        Date today = java.sql.Date.valueOf(localDate);
        
        return noSmokeTryPeopleRepository.findById(today);
    }

}
