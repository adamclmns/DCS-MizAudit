package com.adamclmns.mde.types.output;

import com.adamclmns.mde.types.dcs.Unit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitDataTabular {
    String countryName;
    String coalitionName;
    String groupName;
    String groupID;
    String skill;
    String type;
    String name;
}
