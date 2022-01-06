package com.adamclmns.mde.types.dcs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Mission {
    @JsonAlias("coalition")
    Coalitions coalitions;
}
