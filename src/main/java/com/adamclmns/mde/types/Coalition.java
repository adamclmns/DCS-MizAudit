package com.adamclmns.mde.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coalition {
    String name;
    @JsonAlias("country")
    Map<Integer,Country> countries;

}
