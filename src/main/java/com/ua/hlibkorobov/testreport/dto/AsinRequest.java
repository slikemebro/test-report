package com.ua.hlibkorobov.testreport.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * DTO for request with list of ASINs
 */
@Getter
@Setter
public class AsinRequest {
    private List<String> asins;
}
