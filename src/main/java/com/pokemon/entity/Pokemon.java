
package com.pokemon.entity;

import java.util.LinkedHashMap;
import java.util.List;


public class Pokemon {
    
    private Integer id;
    private String nombre;
    private List<String> tipos;
    private Integer peso;
    private List<String> habilidades;
    private String foto;
    private String url;
    private LinkedHashMap evoluciones;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tipos
     */
    public List<String> getTipos() {
        return tipos;
    }

    /**
     * @param tipos the tipo to set
     */
    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }

    /**
     * @return the peso
     */
    public Integer getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    /**
     * @return the habilidades
     */
    public List<String> getHabilidades() {
        return habilidades;
    }

    /**
     * @param habilidades the habilidades to set
     */
    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    /**
     * @return the evoluciones
     */
    public LinkedHashMap getEvoluciones() {
        return evoluciones;
    }

    /**
     * @param evoluciones the evoluciones to set
     */
    public void setEvoluciones(LinkedHashMap evoluciones) {
        this.evoluciones = evoluciones;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
