package com.beer.msscbrewery.web.mapper;

import com.beer.msscbrewery.domain.Beer;
import com.beer.msscbrewery.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);

    Beer beerDtoToBeer(BeerDTO beerDTO);

}
