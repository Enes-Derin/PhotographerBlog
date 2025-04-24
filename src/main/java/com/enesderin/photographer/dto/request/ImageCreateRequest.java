package com.enesderin.photographer.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCreateRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String url;
}
