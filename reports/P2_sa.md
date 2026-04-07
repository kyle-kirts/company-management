# Static Analysis Report

## PMD Results
```xml
<?xml version="1.0" encoding="UTF-8"?>
<pmd xmlns="http://pmd.sourceforge.net/report/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sourceforge.net/report/2.0.0 http://pmd.sourceforge.net/report_2_0_0.xsd"
    version="6.21.0" timestamp="2026-04-07T15:26:05.441">
<file name="/home/denizin/Personal/Education/CS415/Git/t16/server/src/main/java/edu/colostate/cs415/model/Company.java">
<violation beginline="166" endline="175" begincolumn="25" endcolumn="25" rule="CollapsibleIfStatements" ruleset="Design" package="edu.colostate.cs415.model" class="Company" method="assign" externalInfoUrl="https://pmd.github.io/pmd-6.21.0/pmd_rules_java_design.html#collapsibleifstatements" priority="3">
These nested if statements could be combined
</violation>
<violation beginline="167" endline="174" begincolumn="33" endcolumn="33" rule="CollapsibleIfStatements" ruleset="Design" package="edu.colostate.cs415.model" class="Company" method="assign" externalInfoUrl="https://pmd.github.io/pmd-6.21.0/pmd_rules_java_design.html#collapsibleifstatements" priority="3">
These nested if statements could be combined
</violation>
</file>
<file name="/home/denizin/Personal/Education/CS415/Git/t16/server/src/main/java/edu/colostate/cs415/model/ProjectSize.java">
<violation beginline="8" endline="10" begincolumn="13" endcolumn="5" rule="UnnecessaryModifier" ruleset="Code Style" package="edu.colostate.cs415.model" class="ProjectSize" method="ProjectSize" externalInfoUrl="https://pmd.github.io/pmd-6.21.0/pmd_rules_java_codestyle.html#unnecessarymodifier" priority="3">
Unnecessary modifier 'private' on constructor 'ProjectSize(int)': enum constructors are implicitly private
</violation>
</file>
<file name="/home/denizin/Personal/Education/CS415/Git/t16/server/src/main/java/edu/colostate/cs415/model/Qualification.java">
<violation beginline="30" endline="30" begincolumn="25" endcolumn="81" rule="UselessParentheses" ruleset="Code Style" package="edu.colostate.cs415.model" class="Qualification" method="equals" externalInfoUrl="https://pmd.github.io/pmd-6.21.0/pmd_rules_java_codestyle.html#uselessparentheses" priority="4">
Useless parentheses.
</violation>
</file>
<file name="/home/denizin/Personal/Education/CS415/Git/t16/server/src/main/java/edu/colostate/cs415/model/Worker.java">
<violation beginline="54" endline="54" begincolumn="25" endcolumn="145" rule="UselessParentheses" ruleset="Code Style" package="edu.colostate.cs415.model" class="Worker" method="toString" externalInfoUrl="https://pmd.github.io/pmd-6.21.0/pmd_rules_java_codestyle.html#uselessparentheses" priority="4">
Useless parentheses.
</violation>
</file>
<file name="/home/denizin/Personal/Education/CS415/Git/t16/server/src/main/java/edu/colostate/cs415/server/RestController.java">
<violation beginline="93" endline="93" begincolumn="17" endcolumn="26" rule="UnnecessaryFullyQualifiedName" ruleset="Code Style" package="edu.colostate.cs415.server" class="RestController" method="stop" externalInfoUrl="https://pmd.github.io/pmd-6.21.0/pmd_rules_java_codestyle.html#unnecessaryfullyqualifiedname" priority="4">
Unnecessary use of fully qualified name 'Spark.stop' due to existing static import 'spark.Spark.*'
</violation>
</file>
</pmd>
```

## SpotBugs Results
```json
{
  "$schema": "https://raw.githubusercontent.com/oasis-tcs/sarif-spec/master/Schemata/sarif-schema-2.1.0.json",
  "version": "2.1.0",
  "runs": [
    {
      "tool": {
        "driver": {
          "name": "SpotBugs",
          "rules": [
            {
              "id": "DMI_INVOKING_TOSTRING_ON_ARRAY",
              "shortDescription": {
                "text": "Invocation of toString on an array."
              },
              "fullDescription": {
                "text": "The code invokes toString on an array, which will generate a fairly useless \nresult such as [C@16f0472. Consider using Arrays.toString to convert the array \ninto a readable String that gives the contents of the array. See Programming \nPuzzlers, chapter 3, puzzle 12."
              },
              "helpUri": "https://spotbugs.readthedocs.io/en/latest/bugDescriptions.html#DMI_INVOKING_TOSTRING_ON_ARRAY"
            }
          ]
        }
      },
      "results": [
        {
          "ruleId": "DMI_INVOKING_TOSTRING_ON_ARRAY",
          "level": "error",
          "message": {
            "text": "Invocation of toString on an array"
          },
          "locations": [
            {
              "physicalLocation": {
                "artifactLocation": {
                  "uri": "edu/colostate/cs415/dto/ProjectDTO.java"
                },
                "region": {
                  "startLine": 129
                }
              }
            }
          ],
          "partialFingerprints": {
            "instanceHash": "a87bdb141a02a1dfd13805691e7307c3"
          }
        },
        {
          "ruleId": "DMI_INVOKING_TOSTRING_ON_ARRAY",
          "level": "error",
          "message": {
            "text": "Invocation of toString on an array"
          },
          "locations": [
            {
              "physicalLocation": {
                "artifactLocation": {
                  "uri": "edu/colostate/cs415/dto/ProjectDTO.java"
                },
                "region": {
                  "startLine": 130
                }
              }
            }
          ],
          "partialFingerprints": {
            "instanceHash": "96a9c220dfd7507d173ca67d5eb4ed3"
          }
        },
        {
          "ruleId": "DMI_INVOKING_TOSTRING_ON_ARRAY",
          "level": "error",
          "message": {
            "text": "Invocation of toString on an array"
          },
          "locations": [
            {
              "physicalLocation": {
                "artifactLocation": {
                  "uri": "edu/colostate/cs415/dto/ProjectDTO.java"
                },
                "region": {
                  "startLine": 131
                }
              }
            }
          ],
          "partialFingerprints": {
            "instanceHash": "cb837e3f4629879b2108d7fcaeaad2c3"
          }
        },
        {
          "ruleId": "DMI_INVOKING_TOSTRING_ON_ARRAY",
          "level": "error",
          "message": {
            "text": "Invocation of toString on an array"
          },
          "locations": [
            {
              "physicalLocation": {
                "artifactLocation": {
                  "uri": "edu/colostate/cs415/dto/QualificationDTO.java"
                },
                "region": {
                  "startLine": 63
                }
              }
            }
          ],
          "partialFingerprints": {
            "instanceHash": "a6a2683efbc13f1309c4c14d63c1e9a1"
          }
        },
        {
          "ruleId": "DMI_INVOKING_TOSTRING_ON_ARRAY",
          "level": "error",
          "message": {
            "text": "Invocation of toString on an array"
          },
          "locations": [
            {
              "physicalLocation": {
                "artifactLocation": {
                  "uri": "edu/colostate/cs415/dto/WorkerDTO.java"
                },
                "region": {
                  "startLine": 110
                }
              }
            }
          ],
          "partialFingerprints": {
            "instanceHash": "1213202867fbe5fee7c1b433276594c"
          }
        },
        {
          "ruleId": "DMI_INVOKING_TOSTRING_ON_ARRAY",
          "level": "error",
          "message": {
            "text": "Invocation of toString on an array"
          },
          "locations": [
            {
              "physicalLocation": {
                "artifactLocation": {
                  "uri": "edu/colostate/cs415/dto/WorkerDTO.java"
                },
                "region": {
                  "startLine": 111
                }
              }
            }
          ],
          "partialFingerprints": {
            "instanceHash": "b554010481c9935afd4740b8bb33614a"
          }
        }
      ],
      "properties": {
        "spotbugsRunName": "Git"
      }
    }
  ]
}
```
