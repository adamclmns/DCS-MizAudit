package com.adamclmns.mde.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coalitions {
    Coalition blue;
    Coalition red;
    Coalition neutrals;
}
