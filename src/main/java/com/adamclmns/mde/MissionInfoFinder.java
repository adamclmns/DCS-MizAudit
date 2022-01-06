package com.adamclmns.mde;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class with functions to find certain data within a DCS World 'mission' file from within a .miz
 * archive.
 */
public class MissionInfoFinder {
  private static final Logger logger = LoggerFactory.getLogger(MissionInfoFinder.class);
  private static final String MASTER_REGEX =
      "\\{(((\\n\\t*)\\t)\\S.*(\\2.*)*)\\,\\s--\\s\\[\\d+\\]\\3\\}";
  private static final String VARIABLE_DEF_REGEX = "mission\\s=\\s\\w*";
  private static final String COMMENT_REGEX = "\\s--\\s.+(\n)";
  private static final String FINAL_COMMENT = " -- end of mission";
  private static final String TRAILING_COMMA_REGEX = "\\,(\\n.*\\})";
  private static final String EQUAL_TO_COLON_REMOVE_BRACKETS_REGEX = "\\[(.*?)\\]\\s\\=\\s";
  private static final String QUOTED_VALUES_REGEX = "\\:\\\"(.+)\\\"";
  private static final String NUMBERED_KEYS_REGEX = "\\s([\\d]+)([:])";
  private static final String TABS_RETURNS_REGEX = "[\\t\\r\\n]";


  public static String replace(String input, Pattern regex, Function<Matcher, String> callback) {
    StringBuffer resultString = new StringBuffer();
    Matcher regexMatcher = regex.matcher(input);
    while (regexMatcher.find()) {
      regexMatcher.appendReplacement(resultString, callback.apply(regexMatcher));
    }
    regexMatcher.appendTail(resultString);

    return resultString.toString();
  }

  /**
   * The place where Unit lists begin...
   *
   * ["coalition"] = { ["blue"] = { ["bullseye"] = { ["y"] = 0, ["x"] = 0, }, -- end of
   * ["bullseye"] ["nav_points"] = { }, -- end of ["nav_points"] ["name"] = "blue", ["country"] = {
   * [1] = { ["id"] = 21, ["name"] = "Australia", ["plane"] = { ["group"] = { [1] =
   */
  // https://stackoverflow.com/questions/16255770/convert-lua-data-to-json
  public static String convertLuaTableDataToJsonData(File tempFile) throws IOException {

    String allLines = Files.readAllLines(tempFile.toPath()).stream().collect(Collectors.joining("\n"));
    logger.info("Mission File Contents Length : {}",allLines.length());

    allLines = executeMatcherReplaceAll(allLines,VARIABLE_DEF_REGEX,"");
    allLines = executeMatcherReplaceAll(allLines,COMMENT_REGEX,"$1");
    allLines= allLines.replace(FINAL_COMMENT,"");
    allLines = executeMatcherReplaceAll(allLines,EQUAL_TO_COLON_REMOVE_BRACKETS_REGEX,"$1"+":");
    allLines = executeMatcherReplaceAll(allLines,NUMBERED_KEYS_REGEX,"\""+"$1"+"\":");
    allLines = executeMatcherReplaceAll(allLines,TRAILING_COMMA_REGEX,"$1");
    allLines = allLines.replaceAll("\\\\\"","&quot;");


    return allLines;
  }

  private static String executeMatcherReplaceAll(String content, String regexPattern, String replaceExpression){
    Pattern pattern = Pattern.compile(regexPattern);
    Matcher matcher = pattern.matcher(content);
    return matcher.replaceAll(replaceExpression);
  }

//  public static mapTheInfo

}
