package com.adamclmns.mde;

import com.adamclmns.mde.types.Coalition;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MissionInfoFinderTest {
    private static final Logger logger = LoggerFactory.getLogger(MissionInfoFinderTest.class);
    private static final String path = "src/test/resources/mission";


    @Test
    public void convertLuaTableDataToJsonData() throws ScriptException, IOException {
        File missionFile = new File(path);
        JsonNode missionJSON = MissionInfoFinder.convertLuaTableDataToJsonData(missionFile);
        JsonNode coaltionNode = missionJSON.get("coalition");
        JsonNode blueCoalitionInfo = coaltionNode.get("blue");
        logger.info("New Coalition Node: {}",blueCoalitionInfo.asText());
        ObjectMapper om =new ObjectMapper();
        Coalition blueTeam = om.readValue(blueCoalitionInfo.traverse(), Coalition.class);

        logger.info("reading Object {}",blueTeam);

        FileWriter writer = new FileWriter(new File("TestOutput.json"));
        writer.write(om.writeValueAsString(blueTeam));
        writer.flush();
        writer.close();
    }
}