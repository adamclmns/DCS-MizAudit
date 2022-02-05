package com.adamclmns.mde.types.dcs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicles {
    @JsonAlias("group")
    Map<Integer,Group> groups;
}
