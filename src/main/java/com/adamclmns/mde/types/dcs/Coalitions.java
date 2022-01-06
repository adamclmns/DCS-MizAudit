package com.adamclmns.mde.types.dcs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coalitions {
    @JsonAlias("blue")
    Coalition blue;
    @JsonAlias("red")
    Coalition red;
    @JsonAlias("neutrals")
    Coalition neutrals;
}
