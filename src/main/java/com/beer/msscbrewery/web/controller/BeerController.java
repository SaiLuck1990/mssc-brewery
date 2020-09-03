package com.beer.msscbrewery.web.controller;


import com.beer.msscbrewery.web.model.BeerDTO;
import com.beer.msscbrewery.web.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity handlePOST(@Valid @RequestBody BeerDTO beerDTO){
        BeerDTO savedDto = beerService.saveBeerDto(beerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleUpdate(@NotNull @PathVariable("beerId") UUID beerId , @Valid @RequestBody BeerDTO beerDTO){
        beerService.updateBeer(beerId , beerDTO);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
    }

/*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMismatchException(MethodArgumentNotValidException ex){
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
*/


}
