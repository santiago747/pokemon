
package com.pokemon.helper;

import com.pokemon.entity.Pokemon;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.Cache;

public class CacheHelper {
    
    
    private CacheManager cacheManager;
    private Cache<Integer, Pokemon> pokeCache;

    public CacheHelper() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        pokeCache = cacheManager.createCache("pokeCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Pokemon.class, ResourcePoolsBuilder.heap(10)));
    }

    public Cache<Integer, Pokemon> getPokeCache() {
        return pokeCache;
    }

    public Cache<Integer, Pokemon> getPokeCacheFromCacheManager() {
        return cacheManager.getCache("pokeCache", Integer.class, Pokemon.class);
    }
}
