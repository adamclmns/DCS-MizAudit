package com.adamclmns.mde.types.dcs;

import com.adamclmns.mde.MissionInfoFinder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.adamclmns.mde.TestConstants.TEST_MISSION_FILE_PATH;

public class TypeMappingTest {

    String missionJSON;

    @Before
    public void setup() throws IOException {
        File missionFile = new File(TEST_MISSION_FILE_PATH);
        this.missionJSON = MissionInfoFinder.convertLuaTableDataToJsonData(missionFile);
    }

    @Test
    public void readMissionPoJoFromJsonString() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode missionNode = mapper.readTree(missionJSON);
        Mission mission = mapper.readValue(missionNode.traverse(),Mission.class);
        Assert.assertNotNull(mission);
        Assert.assertNotNull(mission.getCoalitions());

        Assert.assertNotNull(mission.getCoalitions().getBlue());
        Assert.assertNotNull(mission.getCoalitions().getBlue().getCountries());
        Assert.assertNotNull(mission.getCoalitions().getBlue().getCountries().keySet());


        Assert.assertNotNull(mission.getCoalitions().getRed());
        Assert.assertNotNull(mission.getCoalitions().getRed().getCountries());
        Assert.assertNotNull(mission.getCoalitions().getRed().getCountries().keySet());

        Assert.assertNotNull(mission.getCoalitions().getNeutrals());
        Assert.assertNotNull(mission.getCoalitions().getNeutrals().getCountries());
        Assert.assertNotNull(mission.getCoalitions().getNeutrals().getCountries().keySet());

    }

}