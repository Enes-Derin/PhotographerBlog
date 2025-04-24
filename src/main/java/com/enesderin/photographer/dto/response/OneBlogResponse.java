package com.enesderin.photographer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneBlogResponse {
    private int id;
    private String title;
    private String summary;
    private String content;
    private String coverImageUrl ;
    private LocalDateTime createdAt;
}
