package com.adamclmns.mde;

import com.adamclmns.mde.types.dcs.Coalition;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.adamclmns.mde.TestConstants.*;

public class MissionInfoFinderTest {
  private static final Logger logger = LoggerFactory.getLogger(MissionInfoFinderTest.class);


  @Test
  public void convertLuaTableDataToJsonData() throws ScriptException, IOException {
    File missionFile = new File(TEST_MISSION_FILE_PATH);
    String missionJSON = MissionInfoFinder.convertLuaTableDataToJsonData(missionFile);

    ObjectMapper om = new ObjectMapper();
    Coalition blueTeam = om.readValue(missionJSON, Coalition.class);

    logger.info("reading Object {}", blueTeam);

    FileWriter writer = new FileWriter(new File(MISSION_AS_JSON_PATH));
    writer.write(missionJSON);
  }

  @Test
  public void mapJsonDataToTypes() throws ScriptException, IOException {
    ObjectMapper om = new ObjectMapper();
    File missionFile = new File(TEST_MISSION_AS_JSON_PATH);

    JsonNode missionJson = om.readTree(missionFile);
    JsonNode coalitionNode = missionJson.get("coalition");
    JsonNode blueCoalitionInfo = coalitionNode.get("blue");

    logger.info("New Coalition Node: {}", blueCoalitionInfo.asText());
    Coalition blueTeam = om.readValue(blueCoalitionInfo.traverse(), Coalition.class);

    logger.info("reading Object {}", blueTeam);

    FileWriter writer = new FileWriter(new File(JSON_MAPPED_TO_TYPES_PATH));
    writer.write(om.writeValueAsString(blueTeam));
    writer.flush();
    writer.close();
  }
}
