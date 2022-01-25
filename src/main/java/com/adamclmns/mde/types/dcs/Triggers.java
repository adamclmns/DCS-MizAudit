package com.adamclmns.mde.types.dcs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Triggers {

    Map<Integer,Zone> zones;
}
