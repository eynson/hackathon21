package com.example.hackathon21.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespuestaDTO {

    private String hostname;
    private String method;
    private String url;
    private String data;
    private String date;
    private int validitySeconds;
    private String token;



}
