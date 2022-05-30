package com.pokemon.service;

import com.pokemon.util.PokeApiUtil;
import com.pokemon.entity.Pokemon;
import com.pokemon.exception.InfoPokemonException;
import com.pokemon.helper.CacheHelper;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    
    @Autowired
    private PokeApiUtil util;
    private CacheHelper cache;
    
    public List<Pokemon> obtenerPokemones() throws InfoPokemonException{
        List<Pokemon> listaTodos = new ArrayList<>();
        List<LinkedHashMap> pokes = util.obtenerPokemones();
        int i=0;
        for(LinkedHashMap map : pokes){
            Pokemon poke = new Pokemon();
            poke.setUrl((String)map.get("url"));
            poke.setNombre((String)map.get("name"));
            String[] partesUrl = poke.getUrl().split("/");
            poke.setId(Integer.parseInt(partesUrl[partesUrl.length-1]));
            if (cache.getPokeCache().containsKey(poke.getId())) {
                listaTodos.add(cache.getPokeCache().get(poke.getId()));
                System.out.println("Adiciona 1 pokemon cache " + i++);
            }else{
                LinkedHashMap pokeMap = util.obtenerDatosPokemon(poke.getId().toString());

                List<String> habilidades = new ArrayList<>();
                List<String> tipos = new ArrayList<>();

                obtenerDatosPokemon(poke, pokeMap, habilidades, tipos);
                listaTodos.add(poke);
                cache.getPokeCache().put(poke.getId(), poke);
                System.out.println("Adiciona 1 pokemon " + i++);
            }
        }
        
        return listaTodos;
    }

    private void obtenerDatosPokemon(Pokemon poke, LinkedHashMap pokeMap, List<String> habilidades, List<String> tipos) {
        if(poke.getNombre() == null){
            poke.setNombre((String)pokeMap.get("name"));
        }
        poke.setPeso((Integer)pokeMap.get("weight"));
        obtenerHabilidadesPokemon(pokeMap, habilidades);
        
        obtenerTiposPokemon(pokeMap, tipos);
        
        obtenerFotoPokemon(pokeMap, poke);
        poke.setTipos(tipos);
        poke.setHabilidades(habilidades);
        LinkedHashMap evoluciones = util.obtenerEvoluciones(poke.getId());
        poke.setEvoluciones(evoluciones);
    }

    private void obtenerFotoPokemon(LinkedHashMap pokeMap, Pokemon poke) {
        LinkedHashMap sprites = (LinkedHashMap)pokeMap.get("sprites");
        poke.setFoto(sprites.get("front_default")!= null? sprites.get("front_default").toString():"");
    }

    private void obtenerTiposPokemon(LinkedHashMap pokeMap, List<String> tipos) {
        ArrayList<LinkedHashMap> types = (ArrayList<LinkedHashMap>)pokeMap.get("types");
        for(LinkedHashMap typesMap : types){
            String nombreTypes = (String)((LinkedHashMap)typesMap.get("type")).get("name");
            tipos.add(nombreTypes);
        }
    }

    private void obtenerHabilidadesPokemon(LinkedHashMap pokeMap, List<String> habilidades) {
        ArrayList<LinkedHashMap> abilities = (ArrayList<LinkedHashMap>)pokeMap.get("abilities");
        for(LinkedHashMap abiMap : abilities){
            String nombre = (String)((LinkedHashMap)abiMap.get("ability")).get("name");
            habilidades.add(nombre);
        }
    }
    
    public Pokemon obtenerPokemon(String idName) throws InfoPokemonException{
        Pokemon poke = new Pokemon();
        List<String> habilidades = new ArrayList<>();
        List<String> tipos = new ArrayList<>();
        try{
            poke.setId(Integer.parseInt(idName));
        }catch(NumberFormatException e){
            throw new InfoPokemonException(e.getMessage());
        }
        if (cache.getPokeCache().containsKey(poke.getId())) {
            return cache.getPokeCache().get(poke.getId());
        }
        try{
            LinkedHashMap pokeMap = util.obtenerDatosPokemon(idName);
            obtenerDatosPokemon(poke, pokeMap, habilidades, tipos);
            cache.getPokeCache().put(poke.getId(), poke);
        }catch(Exception ex){
            throw new InfoPokemonException(ex.getMessage());
        }
        return poke;
    }
    
    public void setCache(CacheHelper cache) {
        this.cache = cache;
    }
    
}
