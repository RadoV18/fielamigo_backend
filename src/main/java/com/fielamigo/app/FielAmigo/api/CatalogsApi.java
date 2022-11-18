package com.fielamigo.app.FielAmigo.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fielamigo.app.FielAmigo.bl.CatalogBl;
import com.fielamigo.app.FielAmigo.dto.CatalogDto;
import com.fielamigo.app.FielAmigo.dto.ResponseDto;
import com.fielamigo.app.FielAmigo.utils.AuthUtil;
import com.fielamigo.app.FielAmigo.utils.FielAmigoException;
import com.fielamigo.app.FielAmigo.utils.JwtUtil;
import com.fielamigo.app.FielAmigo.utils.UnauthorizedException;

@RestController
@RequestMapping("/api/v1/catalogs")
public class CatalogsApi {

    private CatalogBl catalogBl;

    public CatalogsApi(CatalogBl catalogBl) {
        this.catalogBl = catalogBl;
    }
    
    /**
     * Endpoint to get all the countries
     */
    @GetMapping("/countries")
    public ResponseEntity<ResponseDto<List<CatalogDto>>> getCountries(@RequestHeader Map<String, String> headers) {
        ResponseDto<List<CatalogDto>> responseDto =
            new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_COUNTRIES");

            // get the countries
            List<CatalogDto> countries = catalogBl.getCountries();

            responseDto.setData(countries);
            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Endpoint to get all the cities.
     * @param countryId the id of the country
     */
    @GetMapping("/countries/{countryId}/cities")
    public ResponseEntity<ResponseDto<List<CatalogDto>>> getCities(
        @PathVariable int countryId, @RequestHeader Map<String, String> headers) {
        ResponseDto<List<CatalogDto>> responseDto =
            new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_CITIES");


            // get the cities
            List<CatalogDto> cities = catalogBl.getCities(countryId);

            responseDto.setData(cities);
            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Endpoint to get all the breeds.
     */
    @GetMapping("/breeds")
    public ResponseEntity<ResponseDto<List<CatalogDto>>> getBreeds(@RequestHeader Map<String, String> headers) {
        ResponseDto<List<CatalogDto>> responseDto =
            new ResponseDto<>(null, null, false);

        try {
            // check if the user has a token
            String jwt = JwtUtil.getTokenFromHeader(headers);
            // check if the token is valid
            AuthUtil.verifyHasRole(jwt, "GET_BREEDS");


            // get the breeds
            List<CatalogDto> breeds = catalogBl.getBreeds();

            responseDto.setData(breeds);
            responseDto.setSuccessful(true);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } catch (FielAmigoException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            responseDto.setMessage(e.getMessage());
            responseDto.setSuccessful(false);
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }
    }
}
