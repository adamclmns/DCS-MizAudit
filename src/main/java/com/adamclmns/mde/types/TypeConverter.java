package com.adamclmns.mde.types;

import com.adamclmns.mde.types.dcs.*;
import com.adamclmns.mde.types.output.UnitDataTabular;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Converts from DCS Types to MDE types to allow for easier display in a tabular manner */
public class TypeConverter {

  private ObjectMapper mapper;

  public TypeConverter(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public List<UnitDataTabular> getUnitTableFromMission(Mission mission) {
    List<UnitDataTabular> unitDataTable = new ArrayList<>();
    Coalition blue = mission.getCoalitions().getBlue();
    Coalition red = mission.getCoalitions().getRed();
    Coalition neutral = mission.getCoalitions().getNeutrals();

    unitDataTable.addAll(getUnitTableFromCoalition(blue));
    unitDataTable.addAll(getUnitTableFromCoalition(red));
    unitDataTable.addAll(getUnitTableFromCoalition(neutral));
    return unitDataTable;
  }

  private List<UnitDataTabular> getUnitTableFromCoalition(Coalition coalition) {
    List<UnitDataTabular> unitDataTable = new ArrayList<>();
    for (Map.Entry<Integer, Country> blueCountry : coalition.getCountries().entrySet()) {
      String coalitionName = coalition.getName();
      String countryName = blueCountry.getValue().getName();
      Planes countryPlanes = blueCountry.getValue().getPlanes();
      if (countryPlanes != null) {
        for (Group countryPlaneGroup : countryPlanes.getGroups().values()) {

          String groupName = countryPlaneGroup.getName();
          for (Unit groupUnit : countryPlaneGroup.getUnits().values()) {
            String unitName = groupUnit.getName();
            String unitType = groupUnit.getType();
            String unitSkill = groupUnit.getSkill();
            unitDataTable.add(
                UnitDataTabular.builder()
                    .coalitionName(coalitionName)
                    .countryName(countryName)
                    .groupName(groupName)
                    .name(unitName)
                    .skill(unitSkill)
                    .type(unitType)
                    .build());
          }
        }
      }
    }
    return unitDataTable;
  }

  public String writeUnitDataTableToCSV(List<UnitDataTabular> unitTableData) {

    StringBuilder csvOutput = new StringBuilder();
    csvOutput.append("Coalition Name").append(",");
    csvOutput.append("Country Name").append(",");
    csvOutput.append("Group Name").append(",");
    csvOutput.append("Unit Name").append(",");
    csvOutput.append("Unit Skill").append(",");
    csvOutput.append("Unit Type").append("\n");
    for (UnitDataTabular udt : unitTableData) {
      csvOutput.append(udt.getCoalitionName()).append(",");
      csvOutput.append(udt.getCountryName()).append(",");
      csvOutput.append(udt.getGroupName()).append(",");
      csvOutput.append(udt.getName()).append(",");
      csvOutput.append(udt.getSkill()).append(",");
      csvOutput.append(udt.getType()).append("\n");
    }
    return csvOutput.toString();
  }
}
