package com.fielamigo.app.FielAmigo.dto;

public class CatalogDto {
    private Integer id;
    private String name;

    public CatalogDto() {
    }

    public CatalogDto(Integer id, String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CatalogDto [id=" + id + ", name=" + name + "]";
    }

}
