package com.adamclmns.mde.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * {
 *  "alt":52,
 *  "hardpoint_racks":true,
 *  "alt_type":"BARO",
 *  "livery_id":"Australian 77th Squadron",
 *  "skill":"Client",
 *  "parking":31,
 *  "speed":138.88888888889,
 *  "AddPropAircraft": {
 *      "OuterBoard":0,
 *      "InnerBoard":0,
 *      "HelmetMountedDevice":1
 *      },
 *  "type":"FA-18C_hornet",
 *  "Radio": { "1": {
 *          "modulations": { "1":0, "2":0, "4":0, "8":0, "16":0, "17":0, "9":0, "18":0, "5":0, "10":0,
 * "20":0, "11":0, "3":0, "6":0, "12":0, "13":0, "7":0, "14":0, "19":0, "15":0 }, "channels": {
 * "1":266, "2":250.1, "4":268, "8":257, "16":261, "17":267, "9":255, "18":251, "5":238, "10":262,
 * "20":266, "11":259, "3":267, "6":272, "12":268, "13":269, "7":127.5, "14":260, "19":253, "15":263
 * } }, "2": { "modulations": { "1":0, "2":0, "4":0, "8":0, "16":0, "17":0, "9":0, "18":0, "5":0,
 * "10":0, "20":0, "11":0, "3":0, "6":0, "12":0, "13":0, "7":0, "14":0, "19":0, "15":0 },
 * "channels": { "1":250.1, "2":266, "4":268, "8":257, "16":261, "17":267, "9":255, "18":251,
 * "5":238, "10":262, "20":266, "11":259, "3":267, "6":272, "12":268, "13":269, "7":127.5, "14":260,
 * "19":253, "15":263 } } }, "unitId":92, "psi":-0.62155282898994, "dataCartridge": {
 * "GroupsPoints": { "Initial Point": { }, "Sequence 2 Red": { }, "PB": { }, "Sequence 1 Blue": { },
 * "Start Location": { }, "A/A Waypoint": { }, "PP": { }, "Sequence 3 Yellow": { } }, "Points": { }
 * }, "parking_id":"08", "x":-126317.72705078, "name":"Al Minhad AB - F/A-18C 4", "payload": {
 * "pylons": { "1": { "CLSID":"{5CE2FF2A-645A-4197-B48D-8720AC69394F}" }, "2": { "CLSID":"<CLEAN>"
 * }, "3": { "CLSID":"LAU-115_2*LAU-127_AIM-120C" }, "4": {
 * "CLSID":"{40EF17B7-F508-45de-8566-6FFECC0C1AB8}" }, "5": { "CLSID":"{FPU_8A_FUEL_TANK}" }, "6": {
 * "CLSID":"{40EF17B7-F508-45de-8566-6FFECC0C1AB8}" }, "7": { "CLSID":"LAU-115_2*LAU-127_AIM-120C"
 * }, "8": { "CLSID":"<CLEAN>" }, "9": { "CLSID":"{5CE2FF2A-645A-4197-B48D-8720AC69394F}" } },
 * "fuel":4900, "flare":30, "ammo_type":1, "chaff":60, "gun":100 }, "y":-90351.29675293,
 * "heading":0.62155282898994, "callsign": { "1":3, "2":8, "3":4, "name":"Uzi84" },
 * "onboard_num":"384" }
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Unit {
    String skill;
    String type;
    String name;
}
