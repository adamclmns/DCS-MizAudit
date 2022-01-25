package com.adamclmns.mde.types.dcs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zone {
    Integer radius;
    Integer zoneId;
    // color
    // properties
    Boolean hidden;
    Double y;
    Double x;
    String name;
    Integer type;

//              ["radius"] = 75000,
//              ["zoneId"] = 206,
//              ["color"] =
//               {
//                    [1] = 1,
//                    [2] = 1,
//                    [3] = 1,
//                    [4] = 0.15,
//               }, -- end of ["color"]
//            ["properties"] =
//              {
//              }, -- end of ["properties"]
//            ["hidden"] = true,
//            ["y"] = -161999.99055548,
//            ["x"] = -188476.39972662,
//            ["name"] = "Abu Dhabi Intl Perimeter Zone",
//            ["type"] = 0,


}
