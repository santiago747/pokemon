
package com.pokemon.controller;

import com.pokemon.entity.Pokemon;
import com.pokemon.exception.InfoPokemonException;
import com.pokemon.helper.CacheHelper;
import com.pokemon.service.PokemonService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PokemonController {
    
    @Autowired
    private PokemonService service;
    private final CacheHelper cacheHelper = new CacheHelper();
    
    @GetMapping(value = "/all")
    public ResponseEntity<List<Pokemon>> obtenerPokemones(){
        try {
        
            service.setCache(cacheHelper);
            return ResponseEntity.status(HttpStatus.OK).body(service.obtenerPokemones());

        } catch (InfoPokemonException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al consultar pokemomones", ex);
        }
    }
    
    @GetMapping(value = "/poke/{id}")
    public ResponseEntity<Pokemon> obtenerPokemon(@PathVariable Integer id){
        service.setCache(cacheHelper);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.obtenerPokemon(id.toString()));
        } catch (InfoPokemonException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al consultar pokemon", ex);
        }
    }
    
}
