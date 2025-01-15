package com.eventmanagement.eventapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SearchDTO {

    @NotNull(message = "Search cannot be blank")
    private String title;

    public @NotNull(message = "Search cannot be blank") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "Search cannot be blank") String title) {
        this.title = title;
    }
}
