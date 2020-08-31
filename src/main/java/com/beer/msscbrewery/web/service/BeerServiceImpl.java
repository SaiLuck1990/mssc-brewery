package com.beer.msscbrewery.web.service;

import com.beer.msscbrewery.web.model.BeerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements  BeerService {
    @Override
    public BeerDTO getBeerById(UUID beerId) {
        return BeerDTO.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

    @Override
    public BeerDTO saveBeerDto(BeerDTO beerDTO) {
        return BeerDTO.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDTO beerDTO) {
        log.debug("updating beer");
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("deleting beer");
    }
}
