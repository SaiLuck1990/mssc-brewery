package com.beer.msscbrewery.web.service;

import com.beer.msscbrewery.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {

    BeerDTO getBeerById(UUID beerId);
}
