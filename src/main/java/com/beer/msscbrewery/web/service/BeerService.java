package com.beer.msscbrewery.web.service;

import com.beer.msscbrewery.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {

    BeerDTO getBeerById(UUID beerId);

    BeerDTO saveBeerDto(BeerDTO beerDTO);

    void updateBeer(UUID beerId, BeerDTO beerDTO);

    void deleteById(UUID beerId);
}
