package com.adamclmns.mde;

import com.adamclmns.mde.types.TypeConverter;
import com.adamclmns.mde.types.dcs.Mission;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static com.adamclmns.mde.PropNames.DEFAULT_MISSION_PATH;
import static com.adamclmns.mde.PropNames.DEFAULT_OUTPUT_PATH;

public class UserInterface {
  private static final Logger logger = LoggerFactory.getLogger(UserInterface.class);

  // State Variables
  private String jsonMissionContent = "";
  private JFrame parentFrame;
  private ObjectMapper mapper;
  private Properties props;
  private TypeConverter typeConverter;


  private void init(Properties props) {
    this.parentFrame = new JFrame("JFileChooser Demo");
    this.mapper = new ObjectMapper();
    this.props = props;
    this.typeConverter = new TypeConverter(mapper);

  }

  public void main(Properties props) {
    init(props);

    JFileChooser missionFileChooser = new JFileChooser();
    missionFileChooser.setMultiSelectionEnabled(false);
    missionFileChooser.setCurrentDirectory(new File(props.getProperty(DEFAULT_MISSION_PATH)));
    missionFileChooser.setFileFilter(new FileNameExtensionFilter("Mission Files", "miz"));

    JFileChooser missionJsonSaveChooser = new JFileChooser();
    missionJsonSaveChooser.setMultiSelectionEnabled(false);
    missionJsonSaveChooser.setCurrentDirectory(new File(props.getProperty(DEFAULT_OUTPUT_PATH)));
    missionJsonSaveChooser.setFileFilter(new FileNameExtensionFilter("JSON Files", "json"));

    JFileChooser missionCSVSaveChooser = new JFileChooser();
    missionCSVSaveChooser.setMultiSelectionEnabled(false);
    missionCSVSaveChooser.setCurrentDirectory(new File(props.getProperty(DEFAULT_OUTPUT_PATH)));
    missionCSVSaveChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
    // Declare Buttons
    JButton openMizBtn = new JButton("Open Miz File");
    JButton saveMizJsonBtn = new JButton("Save Miz data as JSON");
    JButton saveMizCSVBtn = new JButton("Save Miz Unit info to CSV");
    openMizBtn.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            jsonMissionContent = openMizButtonActionPerformed(e, missionFileChooser);
          }
        });
    saveMizJsonBtn.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            saveMizJsonButtonActionPerformed(e, missionJsonSaveChooser);
          }
        });
    saveMizCSVBtn.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                saveMizUnitInfoToCSVButtonActionPerformed(e,missionCSVSaveChooser);
              }
            }
    );
    Container pane = this.parentFrame.getContentPane();
    pane.setLayout(new GridLayout(3, 3, 10, 10));
    pane.add(openMizBtn);
    pane.add(saveMizJsonBtn);
    pane.add(saveMizCSVBtn);

    this.parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.parentFrame.setSize(300, 200);
    this.parentFrame.setVisible(true);
  }

  private String openMizButtonActionPerformed(ActionEvent e, JFileChooser fc) {
    int retVal = fc.showOpenDialog(parentFrame);
    if (retVal == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fc.getSelectedFile();

      try {
        logger.info("Getting the Data");
        // Extract Mission File
        return fetchMissionFileFromMiz(selectedFile.getPath());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return null;
  }

  private void saveMizJsonButtonActionPerformed(ActionEvent e, JFileChooser fc) {
    int retVal = fc.showSaveDialog(parentFrame);
    if (retVal == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fc.getSelectedFile();
      try {
        logger.info("Saving to {}", selectedFile.getPath());
        // Map the "raw" JSON to known types.
        saveMizJsonDataToFile(selectedFile);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
  private void saveMizUnitInfoToCSVButtonActionPerformed(ActionEvent e, JFileChooser fc){
    int retVal = fc.showSaveDialog(parentFrame);
    if (retVal == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fc.getSelectedFile();
      try {
        logger.info("Saving to {}", selectedFile.getPath());
        // Map the "raw" JSON to known types.
        Mission mission = getMissionFromJSON();
        String csv = typeConverter.writeUnitDataTableToCSV(typeConverter.getUnitTableFromMission(mission));
        FileWriter writer = new FileWriter(new File(selectedFile.getPath()));
        writer.write(csv);
        writer.flush();
        writer.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  private void saveMizJsonDataToFile(File selectedFile) throws IOException {
    Mission mission = getMissionFromJSON();
    FileWriter writer = new FileWriter(new File(selectedFile.getPath()));
    writer.write(mapper.writeValueAsString(mission));
    writer.flush();
    writer.close();
  }

  private Mission getMissionFromJSON() throws IOException {
    JsonNode missionJson = mapper.readTree(jsonMissionContent);
    Mission mission = mapper.readValue(missionJson.traverse(), Mission.class);
    return mission;
  }

  private String fetchMissionFileFromMiz(String mizPath) throws IOException {

    byte[] mission = MizExtractor.getMissionFromMiz(mizPath);
    File missionFile = new File("mission");
    FileWriter fw = new FileWriter(missionFile);
    fw.write(new String(mission));
    fw.flush();
    fw.close();
    return MissionInfoFinder.convertLuaTableDataToJsonData(missionFile);
  }
}
