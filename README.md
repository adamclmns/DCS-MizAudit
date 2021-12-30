#DCS MizAudit 
Initally, this tool is intended to provide visibility into a DCS World `.miz` file without opening it in the Mission Editor
##Initial Goals

Read data from a .miz file and present it in a clear, and easy to understand format (CSV/JSON)

##Future Goals

Allow minor modification of .miz files including but not limited to
* Changing Unit names
* Changing Unit Types and Loadouts
* Modifying Coalitions and their Groups
* Setting weather
* Modifying required rules, or required modules.


##Background Info (as of 12/30/21)

Inside the `.miz` archive is a file named `mission` which contains a lua dictionary containing nearly all the information needed to run a mission. 

This 'tool' converts the lua dictionary to a JSON structure that can easily be mapped to Java Objects and displayed.

testJsonOutput.json represents a `mission` file converted to JSON.
src/test/resources/mission is a raw `mission` file
TestOutput.json represents the current state of the Mapped JSON Types, as produced by the single Unit test present. 