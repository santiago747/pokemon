/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pokemon.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PokeApiUtil {
    
    public List<LinkedHashMap> obtenerPokemones(){
        String url = "https://pokeapi.co/api/v2/pokemon?limit=10";
        RestTemplate rest = new RestTemplate();
        Object pokemones = rest.getForObject(url, Object.class);
        LinkedHashMap pokMap = (LinkedHashMap) pokemones;
        List<LinkedHashMap> listaTodos = (ArrayList)pokMap.get("results");

        return listaTodos;
    }
    
    public LinkedHashMap obtenerDatosPokemon(String idName){
        String url = "https://pokeapi.co/api/v2/pokemon/" + idName;
        RestTemplate rest = new RestTemplate();
        Object pokemon = rest.getForObject(url, Object.class);
        LinkedHashMap pokMap = (LinkedHashMap) pokemon;
        
        return pokMap;
    }
    
    public LinkedHashMap obtenerEvoluciones(Integer id){
        String url = "https://pokeapi.co/api/v2/evolution-chain/" + id;
        RestTemplate rest = new RestTemplate();
        Object evoPokemon = rest.getForObject(url, Object.class);
        LinkedHashMap evoMap = (LinkedHashMap) evoPokemon;
        
        return evoMap;
    }
    
}
