package com.sav.DTO;

public class HobbyDTO {

    private Long id;

    private String title;

    private String description;

    public HobbyDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public HobbyDTO(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
