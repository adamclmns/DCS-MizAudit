package com.adamclmns.mde.types.dcs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForcedOptions {
    boolean easyFlight;
    boolean fuel;
    String geffect;
    boolean easyRadar;
}
