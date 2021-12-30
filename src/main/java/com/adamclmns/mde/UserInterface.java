package com.adamclmns.mde;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

public class UserInterface {
  private static final Logger logger = LoggerFactory.getLogger(UserInterface.class);
  public void main() {
    final JFrame frame = new JFrame("JFileChooser Demo");
    final JFileChooser fc = new JFileChooser();
    fc.setMultiSelectionEnabled(false);
    fc.setCurrentDirectory(
        new File(
            "G:\\My Drive\\CentralArkansasGaming_DCS-World\\Snowfox - Persian Gulf\\"));
    JButton openMizBtn = new JButton("Open Miz File");
    openMizBtn.addActionListener(
        new ActionListener() {

          public void actionPerformed(ActionEvent e) {
            int retVal = fc.showOpenDialog(frame);
            if (retVal == JFileChooser.APPROVE_OPTION) {
              File selectedFile = fc.getSelectedFile();
              StringBuilder sb = new StringBuilder();
              sb.append(selectedFile.getName() + "\n");
              try {
               logger.info("Getting the Data");
                byte[] mission = MizExtractor.getMissionFromMiz(selectedFile.getPath());
                File missionFile = new File("mission");
                FileWriter fw = new FileWriter(missionFile);
                fw.write(new String(mission));
                fw.flush();
                fw.close();


                MissionInfoFinder.convertLuaTableDataToJsonData(missionFile);
                JOptionPane.showMessageDialog(frame, sb.toString());
              } catch (Exception ex) {
                ex.printStackTrace();
              }
            }
          }
        });
    Container pane = frame.getContentPane();
    pane.setLayout(new GridLayout(3, 1, 10, 10));
    pane.add(openMizBtn);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setVisible(true);
  }
}
