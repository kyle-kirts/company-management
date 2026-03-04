## Table Templates

**Method:** `example(param1, param2)`

| Variable  | Characteristic  | Partition | Value |
| --------- | --------------- | --------- | ----- |
| Variable1 | Characteristic1 | Partition | Value |
|           |                 | Partition | Value |
|           | Characteristic2 | Partition | Value |
| Variable2 | Characteristic1 | Partition | Value |

---

**Method:** `example(param1, param2)`

| Test           | A   | B   | C   | JUnit Test Name                 |
| -------------- | --- | --- | --- | ------------------------------- |
| T1 (base test) | A2  | B2  | C3  | `test_medHt_medWt_otherDog()`   |
| T2             | A1  | B2  | C3  | `test_tallHt_medWt_otherDog()`  |
| T3             | A3  | B2  | C3  | `test_shortHt_medWt_otherDog()` |
| T4             | A2  | B1  | C3  | `test_medHt_heavyWt_otherDog()` |
| T5             | A2  | B3  | C3  | `test_medHt_lowWt_OtherDog()`   |
| T6             | A2  | B2  | C1  | `test_medHt_medWt_Poodle()`     |
| T7             | A2  | B2  | C2  | `test_medHt_medWt_Bulldog()`    |

## Class: Qualification

**Method:** `Qualification(String description)`

| Variable    | Characteristic | Partition            | Value |
| ----------- | -------------- | -------------------- | ----- |
| description | A\) Value Type | A1: null             | null  |
|             |                | A2: empty string     | ""    |
|             |                | A3: only whitespaces | " "   |

**Method:** `Qualification(String description)`

| Test           | A   | JUnit Test Name                             |
| -------------- | --- | ------------------------------------------- |
| T1 (base test) | A1  | `test_nullDescription_Constructor()`        |
| T2             | A2  | `test_emptyString_Constructor()`            |
| T3             | A3  | `test_whitespacesDescription_Constructor()` |

---

**Method:** `toString()`

| Variable    | Characteristic | Partition             | Value               |
| ----------- | -------------- | --------------------- | ------------------- |
| description | A\) Value Type | A1: Valid Description | "Valid Description" |

**Method:** `toString()`

| Test           | A   | JUnit Test Name                    |
| -------------- | --- | ---------------------------------- |
| T1 (base test) | A1  | `test_validDescription_toString()` |

---

**Method:** `equals(Object other)`

| Variable    | Characteristic | Partition   | Value                                       |
| ----------- | -------------- | ----------- | ------------------------------------------- |
| Object      | A\) Type       | A1: null    | null                                        |
|             |                | A2: valid   | Qualification                               |
|             |                | A3: invalid | Integer                                     |
| Description | B\) equal      | B1: True    | "valid description"                         |
|             |                | B2: False   | "valid description" & " valid description " |

**Method:** `equals(Object other)`

| Test           | A   | B   | JUnit Test Name              |
| -------------- | --- | --- | ---------------------------- |
| T1 (base test) | A2  | B1  | `test_equalOther_equals()`   |
| T2             | A2  | B2  | `test_unequalOther_equals()` |
| T3             | A1  |     | `test_nullOther_equals()`    |
| T4             | A3  |     | `test_invalidType_equals()`  |

---

**Method:** `hashCode()_isp`

| Variable    | Characteristic | Partition | Value               |
| ----------- | -------------- | --------- | ------------------- |
| description | A\) Value Type | A1: valid | "valid description" |

**Method:** `hashCode()_bcc`

| Test           | A   | JUnit Test Name                        |
| -------------- | --- | -------------------------------------- |
| T1 (base test) | A1  | `test_validDescriptionHash_hashCode()` |

---

**Method:** `getWorkers()_isp`

**Method:** `addWorker()_isp`

| Variable | Characteristic     | Partition     | Value     |
| -------- | ------------------ | ------------- | --------- |
| workers  | A\) Set of workers | A1: Valid Set | [worker1] |
|          |                    | A2: Empty Set | []        |

**Method:** `getWorkers()_bcc`

**Method:** `addWorker()_bcc`

| Test           | A   | JUnit Test Name                    |
| -------------- | --- | ---------------------------------- |
| T1 (base test) | A1  | `test_validWworkerSet_getWorker()` |
| T2             | A2  | `test_emptyWorkerSet_getWorker()`  |

---

**Method:** `removeWorker(w: Worker)_isp`

| Variable | Characteristic     | Partition       | Value     |
| -------- | ------------------ | --------------- | --------- |
| w        | A\) Valid Worker   | A1: Is Null     | null      |
|          |                    | A2: Is Not Null | worker1   |
| workers  | B\) Set of workers | B1: Valid Set   | [worker1] |
|          |                    | B2: Empty Set   | []        |

**Method:** `removeWorker(w: Worker)_bcc`

| Test           | A   | B   | JUnit Test Name                             |
| -------------- | --- | --- | ------------------------------------------- |
| T1 (base test) | A2  | B1  | `test_nonNullWorker_removeWorker()`         |
| T2             | A1  | B1  | `test_nullWorker_removeWorker()`            |
| T3             | A2  | B2  | `test_nonNullWorkerEmptySet_removeWorker()` |

---

**Method:** `toDTO()_isp`

| Variable    | Characteristic     | Partition     | Value               |
| ----------- | ------------------ | ------------- | ------------------- |
| description | A\) null           | A1: Valid     | "valid description" |
| workers     | B\) Set of workers | B1: Valid Set | [worker1]           |
|             |                    | B2: Empty Set | []                  |

**Method:** `toDTO()_bcc`

| Test           | A   | B   | JUnit Test Name                                   |
| -------------- | --- | --- | ------------------------------------------------- |
| T1 (base test) | A1  | B1  | `test_validDescription_workerSetNotEmpty_toDTO()` |
| T2             | A1  | B2  | `test_validDescription_workerSetEmpty_toDTO()`    |

---

## Class: Worker

**Method:** `Worker(String name, Set<Qualification> qualifications, double salary)`

| Variable       | Characteristic    | Partition                   | Value             |
| -------------- | ----------------- | --------------------------- | ----------------- |
| name           | A\) String Value  | A1: empty                   | ""                |
|                |                   | A2: valid name              | "Bob B"           |
|                |                   | A3: only whitespace         | " "               |
|                |                   | A4: null                    | null              |
| salary         | B\) salary amount | B1: negative salary         | -1.00             |
|                |                   | B2: 0 salary                | 0.00              |
|                |                   | B3: positive salary         | 1.00              |
|                |                   | B4: NaN salary              | NaN               |
| qualifications | C\) Set Value     | C1: null                    | null              |
|                |                   | C2: empty                   | []                |
|                |                   | C3: atleast 1 qualification | ["Qualification"] |

**Method:** `Worker(String name, Set<Qualification> qualifications, double salary)`

| Test           | A   | B   | C   | JUnit Test Name                |
| -------------- | --- | --- | --- | ------------------------------ |
| T1 (base test) | A2  | B3  | C3  | `test_validWorker_Worker()`    |
| T2             | A1  | B3  | C2  | `test_emptyName_Worker()`      |
| T3             | A3  | B3  | C2  | `test_whitespaceName_Worker()` |
| T4             | A4  | B3  | C2  | `test_nullName_Worker()`       |
| T5             | A2  | B1  | C2  | `test_negativeSalary_Worker()` |
| T6             | A2  | B2  | C2  | `test_zeroSalary_Worker()`     |
| T7             | A2  | B3  | C1  | `test_nullQsSet_Worker()`      |
| T8             | A2  | B3  | C2  | `test_emptyQsSet_Worker()`     |
| T8             | A2  | B3  | C3  | `test_nonemptyQsSet_Worker()`  |
| T9             | A2  | B4  | C3  | `test_nanSalary_Worker()`      |

---

**Method:** `getQualifications()`

| Variable       | Characteristic | Partition | Value                                |
| -------------- | -------------- | --------- | ------------------------------------ |
| qualifications | A\) empty      | A1: True  | []                                   |
|                |                | A2: False | ["Qualification1", "Qualification2"] |

**Method:** `getQualifications()`

| Test           | A   | JUnit Test Name                                   |
| -------------- | --- | ------------------------------------------------- |
| T1 (base test) | A1  | `test_emptyQualifications_getQualifications()`    |
| T2             | A2  | `test_multipleQualifications_getQualifications()` |

---

**Method:** `hashCode()_isp`

| Variable | Characteristic | Partition | Value               |
| -------- | -------------- | --------- | ------------------- |
| name     | A\) Value Type | A1: valid | "valid description" |

**Method:** `hashCode()_bcc`

| Test           | A   | JUnit Test Name                 |
| -------------- | --- | ------------------------------- |
| T1 (base test) | A1  | `test_validNameHash_hashCode()` |

---

**Method:** `toString()_isp`

| Variable       | Characteristic | Partition                          | Value                                          |
| -------------- | -------------- | ---------------------------------- | ---------------------------------------------- |
| name           | String Length  | A1: length > 0                     | "Jamiroquai"                                   |
| projects       | List Length    | B1: length = 0                     | new HashSet()                                  |
|                |                | B2: length > 0                     | new HashSet("Synkronized", "Dynamite")         |
| qualifications | List Length    | C1: length = 0                     | new HashSet()                                  |
|                |                | C2: length > 0                     | new HashSet("Virtual Insanity", "Canned Heat") |
| salary         | size           | D1: salary = 0                     | 0                                              |
|                |                | D2: 0 < salary < INTEGER.MAX_Value | 123456                                         |
|                |                | D3: salary > INTEGER.MAX_Value     |

**Method:** `toString()_bcc`

| Test           | A   | B   | C   | D   | JUnit Test Name                                                |
| -------------- | --- | --- | --- | --- | -------------------------------------------------------------- |
| T1 (base test) | A1  | B1  | C2  | D2  | `test_noProjects_LongQualifications_normalSalary_toString()`   |
| T2             | A1  | B2  | C2  | D2  | `test_LongProjects_LongQualifications_normalSalary_toString()` |
| T3             | A1  | B1  | C1  | D2  | `test_noProjects_NoQualifications_normalSalary_toString()`     |
| T4             | A1  | B1  | C2  | D1  | `test_noProjects_LongQualifications_noSalary_toString()`       |
| T5             | A1  | B1  | C2  | D3  | `test_noProjects_LongQualifications_hugeSalary_toString()`     |

---

**Method:** `equals_isp`

| Variable | Characteristic | Partition      | Value                               |
| -------- | -------------- | -------------- | ----------------------------------- |
| Object O | Object type    | A1: null       | null                                |
|          |                | A2: not Worker | "notaworker"                        |
|          |                | A3: Worker     | new Worker("Bob b", qs, 10000)      |
|          | equality       | B1: O = this   | new Worker("Bob b", qs, 10000)      |
|          |                | B2: O != this  | new Worker("Betty Boop", qs, 15000) |

**Method:** `equals()_bcc`

| Test     | A   | B   | JUnit Test Name                            |
| -------- | --- | --- | ------------------------------------------ |
| T1(Base) | A3  | B2  | `test_isWorker_notequalWorkers_equals()`   |
| T2       | A1  | B2  | `test_nullWorker_notequalWorkers_equals()` |
| T3       | A2  | B2  | `test_notWorker_notequalWorkers_equals()`  |
| T4       | A3  | B1  | `test_isWorker_equalWorkers_equals()`      |

---

**Method:** `getSalary()`

| Variable | Characteristic | Partition | Value   |
| -------- | -------------- | --------- | ------- |
| salary   | value          | 0         | 0.00    |
|          |                | 0 <       | 1000.00 |

**Method:** `getSalary()`

| Test           | A   | JUnit Test Name                   |
| -------------- | --- | --------------------------------- |
| T1 (base test) | A2  | `test_zeroSalary_getSalary()`     |
| T2             | A1  | `test_positiveSalary_getSalary()` |

---

**Method:** `getName()`

| Variable | Characteristic | Partition | Value   |
| -------- | -------------- | --------- | ------- |
| name     | A\) value      | A1: valid | "Bob B" |

**Method:** `getName()`

| Test           | A   | JUnit Test Name            |
| -------------- | --- | -------------------------- |
| T1 (base test) | A1  | `test_validName_getName()` |

---

**Method:** `setSalary(double salary)`

| Variable | Characteristic | Partition | Value   |
| -------- | -------------- | --------- | ------- |
| salary   | A\) value      | A1: NaN   | NaN     |
|          |                | A2: < 0   | -100.00 |
|          |                | A3: 0     | 0.00    |
|          |                | A4: 0 <   | 100.00  |

**Method:** `setSalary(double salary)`

| Test           | A   | JUnit Test Name                   |
| -------------- | --- | --------------------------------- |
| T1 (base test) | A4  | `test_positiveSalary_setSalary()` |
| T2             | A2  | `test_negativeSalary_setSalary()` |
| T3             | A3  | `test_zeroSalary_setSalary()`     |
| T4             | A1  | `test_nanSalary_setSalary()`      |

---

**Method:** `addQualification(Qualification qualification)`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| qualification | A\) value | A1: Qualification Object | new Qualification("Qualification_One") |

---

**Method:** `addProject(), getProjects(), removeProject()`

| Variable | Characteristic | Partition          | Value                                       |
| -------- | -------------- | ------------------ | ------------------------------------------- |
| project  | A\) value      | A1: Project Object | Project("Project", qs, ProjectSize.MEDIUM); |
| projects | B\) empty      | B1: True           | empty set                                   |
|          |                | B2: False          | projects size 2                             |

**Method:** `addProject(Project project), getProjects(), removeProject(Project project)`

| Test           | A   | B   | JUnit Test Name                     |
| -------------- | --- | --- | ----------------------------------- |
| T1 (base test) | A1  | B1  | `test_validProject_addProject()`    |
| T2             |     | B1  | `test_emptyProjects_getProjects()`  |
| T3             | A1  | B2  | `test_validProject_removeProject()` |

---

**Method:** `getWorkload()`

| Variable | Characteristic                   | Partition                                               | Value                       |
| -------- | -------------------------------- | ------------------------------------------------------- | --------------------------- |
| projects | A\) Empty projects               | A1: True                                                | empty set projects          |
|          |                                  | A2: False                                               | SMALL, MEDIUM, BIG projects |
| project  | B\) Portion of FINISHED projects | B1: At least one finished project, but not all finished | ProjectStatus.FINISHED      |
|          |                                  | B2: No finished projects                                | ProjectStatus.PlANNED       |
|          |                                  | B3: All projects are finished projects                  | ProjectStatus.FINISHED      |

**Method:** `getWorkload()`

| Test           | A   | B   | JUnit Test Name                           |
| -------------- | --- | --- | ----------------------------------------- |
| T1 (base test) | A2  | B2  | `test_validWorkload_getWorkload()`        |
| T2             | A2  | B3  | `test_onlyFinishedProjects_getWorkload()` |
| T3             | A1  | B2  | `test_emptyProjects_getWorkload()`        |
| T4             | A2  | B1  | `test_oneFinishedProject_getWorkload()`   |

---

**Method:** `isAvailable()`

| Variable | Characteristic            | Partition    | Value                                            |
| -------- | ------------------------- | ------------ | ------------------------------------------------ |
| projects | A\) combined workload     | A1: over 12  | 4 BIG projects, 1 SMALL project                  |
|          |                           | A2: at 12    | 4 BIG projects                                   |
|          |                           | A3: under 12 | 1 BIG, 1 SMALL, 1 MEDIUM project                 |
|          | B\) All projects FINISHED | B1: True     | all project status set to ProjectStatus.FINISHED |
|          |                           | B2: False    | all project status set to ProjectStatus.PLANNED  |

**Method:** `isAvailable()`

| Test           | A   | B   | JUnit Test Name                                |
| -------------- | --- | --- | ---------------------------------------------- |
| T1 (base test) | A3  | B2  | `test_underTwelve_isAvailable()`               |
| T2             | A2  | B2  | `test_atTwelve_isAvailable()`                  |
| T3             | A1  | B2  | `test_overTwelve_isAvailable()`                |
| T4             | A1  | B1  | `test_atTwelve_FinishedProjects_isAvailable()` |

---

**Method:** `willOverload(Project project)`

| Variable | Characteristic            | Partition    | Value                                            |
| -------- | ------------------------- | ------------ | ------------------------------------------------ |
| projects | A\) combined workload     | A1: over 12  | 4 BIG projects, 1 SMALL project                  |
|          |                           | A2: at 12    | 4 BIG projects                                   |
|          |                           | A3: under 12 | 1 BIG, 1 SMALL, 1 MEDIUM project                 |
|          | B\) All projects FINISHED | B1: True     | all project status set to ProjectStatus.FINISHED |
|          |                           | B2: False    | all project status set to ProjectStatus.PLANNED  |

**Method:** `willOverload(Project project)`

| Test           | A   | B   | JUnit Test Name                                |
| -------------- | --- | --- | ---------------------------------------------- |
| T1 (base test) | A3  | B2  | `test_underTwelve_isAvailable()`               |
| T2             | A2  | B2  | `test_atTwelve_isAvailable()`                  |
| T3             | A1  | B2  | `test_overTwelve_isAvailable()`                |
| T4             | A1  | B1  | `test_atTwelve_FinishedProjects_isAvailable()` |

**Method:** `toDTO()_isp`

| Variable    | Characteristic     | Partition     | Value               |
| ----------- | ------------------ | ------------- | ------------------- |
| description | A\) null           | A1: Valid     | "valid description" |
| workers     | B\) Set of workers | B1: Valid Set | [worker1]           |


**Method:** `toDTO()_bcc`

| Test           | A   | B   | JUnit Test Name                                   |
| -------------- | --- | --- | ------------------------------------------------- |
| T1 (base test) | A1  | B1  | `test_validDescription_workerSetNotEmpty_toDTO()` |
| T2             | A1  | B2  | `test_validDescription_workerSetEmpty_toDTO()`    |

---

**Method:** `toDTO()`

| Variable  | Characteristic  | Partition | Value |
| --------- | --------------- | --------- | ----- |
| projects  | A\) Size of the set | A1: 0 projects | empty set |
|           |                 | A2: size > 0 | 2 |
| qualifications | B\) Size of the set | B1: 0 qualifications | empty set |
|           |                 | B2: size > 0 | 2 |


**Method:** `toDTO()`

| Test           | A   | B   | JUnit Test Name                 |
| -------------- | --- | --- | ------------------------------- |
| T1 (base test) | A2  | B2  | `test_expectedState_toDTO()`    |
| T2             | A1  | B2  | `test_emptyProjects_toDTO()`    |
| T3             | A2  | B1  | `test_emptyQualifications_toDTO()` |
| T4             | A1  | B1  | `test_allEmpty_toDTO()`         |


---
## Class: Project

**Method:** `Project(String name, Set<Qualification> qualifications, ProjectSize size)_isp`

| Variable       | Characteristic | Partition          | Value                                      |
| -------------- | -------------- | ------------------ | ------------------------------------------ |
| name           | String Value   | A1: null           | null                                       |
|                |                | A2: not null       | "Project Runway"                           |
|                |                | A3: Empty String   | ""                                         |
| qualifications | set size       | B1: null           | null                                       |
|                |                | B2: empty set      | New HashSet()                              |
|                |                | B3: size > 1       | New HashSet("Sean Kelley", "Grace Kelsey") |
| size           | exists in enum | C1: null           | null                                       |
|                |                | C2: small project  | ProjectSize.SMALL                          |
|                |                | C3: medium project | ProjectSize.MEDIUM                         |
|                |                | C4: large project  | ProjectSize.BIG                            |

**Method:** `Project(String name, Set<Qualification> qualifications, ProjectSize size)_bcc`

| Test     | A   | B   | C   | JUnit Test Name                                       |
| -------- | --- | --- | --- | ----------------------------------------------------- |
| T1(Base) | A2  | B3  | C3  | `test_nonNullName_someQualifications_mediumProject()` |
| T2       | A1  | B3  | C3  | `test_NullName_someQualifications_mediumProject()`    |
| T3       | A3  | B3  | C3  | `test_emptyName_someQualifications_mediumProject()`   |
| T4       | A2  | B1  | C3  | `test_nonNullName_nullQualifications_mediumProject()` |
| T5       | A2  | B2  | C3  | `test_nonNullName_noQualifications_mediumProject()`   |
| T6       | A2  | B3  | C1  | `test_nonNullName_someQualifications_nullProject()`   |
| T7       | A2  | B3  | C2  | `test_nonNullName_someQualifications_smallProject()`  |
| T8       | A2  | B3  | C4  | `test_nonNullName_someQualifications_bigProject()`    |

---

**Method:** `getName()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| name | validity(checked on construction)| A1: valid name| "Project Runway"|

**Method:** `getName()_bcc`
| Test | A | JUnit Test Name |
|------|-----|----------------------------------|
| T1(base) | A1| `test_validName_getName() |


---

**Method:** `addQualification()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| qualification | nullness| A1: non-null qualification| `new Qualification("Grace Kelsey")`|
| qualification | nullness| A2: null qualification| `null`|
| qualification | membership in current required qualifications| B1: qualification not already present| `q2` not in `qs`|
| qualification | membership in current required qualifications| B2: qualification already present| `q1` already in `qs`|

**Method:** `addQualification()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1(base) | A1| B1| `test_newQualification_addQualification()` |
| T2 | A1| B2| `test_duplicateQualification_addQualification()` |
| T3 | A2| --| `test_nullQualification_addQualification()` |

---

**Method:** `getRequiredQualifications()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| qualifications | validity(checked on construction)| A1: valid set| `qs`|
| qualifications.size() | number of qualifications| B1: no qualifications| `0`|
| qualifications.size() | number of qualifications| B2: some qualifications| `2`|

**Method:** `getRequiredQualifications()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1 | A1| B1| `test_noQualifications_getRequiredQualifications()` |
| T2 (base) | A1| B2| `test_someQualifications_getRequiredQualifications()` |

---

**Method:** `toString()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| name | validity(checked on construction)| A1: valid name| "Project Runway"|
| workers.size() | number of workers| B1: no workers| 0|
| workers.size() | number of workers| B2: one worker| 1|
| workers.size() | number of workers| B3: multiple workers| 2|
| status | project status| C1: PLANNED| ProjectStatus.PLANNED|
| status | project status| C2: ACTIVE| ProjectStatus.ACTIVE|

**Method:** `toString()_bcc`
| Test | A | B | C | JUnit Test Name |
|------|-----|-----|-----|----------------------------------|
| T1(base) | A1| B1| C1| `test_noWorkers_planned_toString()` |
| T2 | A1| B2| C1| `test_oneWorker_planned_toString()` |
| T3 | A1| B3| C1| `test_twoWorkers_planned_toString()` |
| T4 | A1| B1| C2| `test_noWorkers_active_toString()` |
| T5 | A1| B2| C2| `test_hasWorkers_active_toString()` |
| T6 | A1| B3| C2| `test_twoWorker_active_toString()` |

---

**Method:** `removeAllWorkers()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| workers | validity(checked on construction)| A1: valid set| `workers`|
| workers.size() | number of assigned workers| B1: no workers| `0`|
| workers.size() | number of assigned workers| B2: has workers| `2`|

**Method:** `removeAllWorkers()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1(base) | A1| B1| `test_noWorkers_removeAllWorkers()` |
| T2 | A1| B2| `test_hasWorkers_removeAllWorkers()` |

---

**Method:** `hashCode()_isp`

| Variable | Characteristic | Partition | Value               |
| -------- | -------------- | --------- | ------------------- |
| name     | A\) Value Type | A1: valid | "valid description" |

**Method:** `hashCode()_bcc`

| Test           | A   | JUnit Test Name                 |
| -------------- | --- | ------------------------------- |
| T1 (base test) | A1  | `test_validNameHash_hashCode()` |

---

**Method:** `getSize()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| size | valid enum value | A1: valid |ProjectSize.MEDIUM|

**Method:** `getSize()_bcc`

| Test           | A   | JUnit Test Name                |
| -------------- | --- | ------------------------------ |
| T1 (base test) | A1  | `test_validEnumSize_getSize()` |

---

**Method:** `equals()_isp`
| Variable | Characteristic | Partition | Value |
| -------- | -------------- | --------- | ------------------- |
| Object O | Object Type | A1: null | null |
| | | A2: not Project | "NotAProject" |
| | | A3: Project | new Project() |
|Name, O.name | equality | B1: name = O.name | "Projected" |
| | | B2: name != O.name | "Projected", "UnProjected" |

**Method:** `equals()_bcc`
| Test | A | B | JUnit Test Name |
| -------------- | --- | --- | ------------------------------ |
| T1(Base) | A3 | B1 | `test_projectO_equalNames_equals()` |
| T2 | A1 | B1 | `test_nullO_equalNames_equals()` |
| T3 | A2 | B1 | `test_nonprojectO_equalNames_equals()` |
| T4 | A3 | B2 | `test_projecto_nonEqualNames_equals()` |

---

**Method:** `setStatus()_isp`
| Variable | Characteristic | Partition | Value |
| -------- | -------------- | --------- | ------------------- |
|input status| nullness | A1: null | null |
| | | A2: not null| ProjectStatus.ACTIVE|

**Method:** `setStatus()_bcc`
| Test | A | JUnit Test Name |
| -------------- | --- | ------------------------------ |
| T1(Base) | A1 | `testNullStatus_setStatus()` |
| T2 | A2 | `testNotNullStatus_setStatus()`|

---

**Method:** `getStatus_isp`
| Variable | Characteristic | Partition | Value |
| -------- | -------------- | --------- | ------------------- |
| this.status| valid enum | A1: valid | ProjectStatus.PLANNED |

**Method:** `getStatus()_bcc`
| Test | A | JUnit Test Name |
| -------------- | --- | ------------------------------ |
| T1(Base) | A1 | `testValidEnumgetStatus()` |

---

**Method:** `getMissingQualifications()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| required qualifications | validity(checked on construction)| A1: valid set| `projectQs`|
| coverage of required qualifications by project workers | amount covered| B1: none covered| no required qualifications covered|
| coverage of required qualifications by project workers | amount covered| B2: some covered| some but not all required qualifications covered|
| coverage of required qualifications by project workers | amount covered| B3: all covered| all required qualifications covered|

**Method:** `getMissingQualifications()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1(base) | A1| B1| `test_noWorkers_getMissingQualifications()` |
| T2 | A1| B2| `test_someCovered_getMissingQualifications()` |
| T3 | A1| B3| `test_allCovered_getMissingQualifications()` |

---


---

**Method:** `getWorkers()_isp`
| Variable | Characteristic | Partition | Value |
| -------- | -------------- | --------- | ------------------- |
| set(worker) | Emptiness | A1: empty | new HashSet() |
| | | A2: not emptY| new HashSet(Worker w, Worker s) |

**Method:** `getWorkers()_bcc`
| Test | A | JUnit Test Name |
| -------- | -------------- | --------- |
| T1(Base) | A2 | `test_hasWorkers_getWorkers()` |
| T2 | A1 | `test_noWorkers_getWorkers()` |

---

**Method:** `isHelpful()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| worker | validity| A1: valid worker| `w`|
| overlap between worker qualifications and project's missing qualifications | amount of overlap| B1: no overlap| worker has no missing qualifications|
| overlap between worker qualifications and project's missing qualifications | amount of overlap| B2: some overlap| worker has at least one missing qualification|
| project's missing qualifications | availability| C1: missing qualifications exist| project still missing at least one qualification|
| project's missing qualifications | availability| C2: no missing qualifications exist| project is missing no qualifications|

**Method:** `isHelpful()_bcc`
| Test | A | B | C | JUnit Test Name |
|------|-----|-----|-----|----------------------------------|
| T1(base) | A1| B2| C1| `test_helpfulWorker_isHelpful()` |
| T2 | A1| B1| C1| `test_notHelpfulWorker_isHelpful()` |
| T3 | A1| B1| C2| `test_noMissingQualifications_isHelpful()` |


---

**Method:** `addWorker()_isp`
| Variable | Characteristic | Partition | Value |
| -------- | -------------- | --------- | ------------------- |
| Worker worker | Existence in set | A1: null | null |
| | | A2: not Duplicate | new Worker("newguy", qs, 10000) |
| | | A3: Duplicate | new Worker("Bob B", qs, 10000) |
| set(worker) | Emptiness | B1: empty | new HashSet() |
| | | B2: not empty| new HashSet(Worker w, Worker s) |

**Method:** `addWorker()_bcc`
| Test | A | B | JUnit Test Name |
| -------- | -------------- | --------- | ------------------- |
| T1(Base) | A2 | B2 | `test_realWorker_hasWorkers_addWorker()` |
| T2 | A1 | B2 | `test_nullWorker_hasWorkers_addWorker()` |
| T3 | A3 | B2 | `test_dupeWorker_hasWorkers_addWorker()` |
| T4 | A2 | B1 | `test_realWorker_noWorkers_addWorker()` |

---

**Method:** `removeWorker()_isp`
| Variable | Characteristic | Partition | Value |
| -------- | -------------- | --------- | ------------------- |
| Worker w | relationship to worker list | A1: null | null |
| | | A2: worker not in worker list | new Worker("New", qs, 0) |
| | | A3: worker in worker list | new Worker("Bob B", qs, 10000) |
| set(worker) | Emptiness | B1: empty | new HashSet() |
| | | B2: not empty| new HashSet(Worker w, Worker s) |

**Method:** `removeWorker()_bcc`
| Test | A | B | JUnit Test Name |
| -------- | -------------- | --------- | ------------------- |
| T1(Base) | A2 | B2 | `test_notinList_hasWorkers_removeWorker()` |
| T2 | A1 | B2 | `test_nullWorker_hasWorkers_removeWorker()` |
| T3 | A3 | B2 | `test_inList_hasWorkers_removeWorker()` |
| T4 | A2 | B1 | `test_notinList_noWorkers_removeWorker()` |

---

## Class: Company

**Method:** `getName()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| name | validity(checked on construction) | A1: valid name | `"ABC"` |

---

**Method:** `getName()_bcc`
| Test | A | JUnit Test Name |
|------|-----|----------------------------------|
| T1(base) | A1 | `test_validName_getName()` |
| T2 | A1 | `test_anotherValidName_getName()` |

---

**Method:** `hashCode()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| name | validity(checked on construction) | A1: valid name | `"ABC"` |
| compared object names | equality of names | B1: same name | `"ABC"` and `"ABC"` |

**Method:** `hashCode()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1(base) | A1 | B1 | `test_sameName_hashCode()` |
| T2 | A1 | B1 | `test_validName_hashCode()` |

---

**Method:** `getQualifications()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| qualifications | validity(checked on construction) | A1: non-null set | `qualifications` |
| qualifications.size() | number of qualifications | B1: no qualifications | `0` |
| qualifications.size() | number of qualifications | B2: one qualification | `1` |
| qualifications.size() | number of qualifications | B3: multiple qualifications | `2` |

**Method:** `getQualifications()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1(base) | A1 | B1 | `test_noQualifications_getQualifications()` |
| T2 | A1 | B2 | `test_oneQualification_getQualifications()` |
| T3 | A1 | B3 | `test_multipleQualifications_getQualifications()` |

--- 

**Method:** `createQualification()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| description | validity | A1: valid description | `"Java"` |
|             |          | A2: null description | `null` |
|             |          | A3: empty description | `""` |

**Method:** `createQualification()_bcc`
| Test | A | JUnit Test Name |
|------|-----|----------------------------------|
| T1(base) | A1 | `test_validDescription_createQualification()` |
| T2 | A2 | `test_nullDescription_createQualification()` |
| T3 | A3 | `test_emptyDescription_createQualification()` |

---

**Method:** `equals()_isp`

| Variable | Characteristic | Partition      | Value                 |
| -------- | -------------- | -------------- | --------------------- |
| Object O | Object type    | A1: null       | null                  |
|          |                | A2: not Company| `"notaCompany"`       |
|          |                | A3: Company    | `new Company("Nvidia")` |
|          | equality       | B1: O = this   | `new Company("Nvidia")` |
|          |                | B2: O != this  | `new Company("AMD")`    |

**Method:** `equals()_bcc`

| Test     | A   | B   | JUnit Test Name                               |
| -------- | --- | --- | --------------------------------------------- |
| T1(Base) | A3  | B2  | `test_isCompany_notequalCompanies_equals()`   |
| T2       | A1  | B2  | `test_nullCompany_notequalCompanies_equals()` |
| T3       | A2  | B2  | `test_notCompany_notequalCompanies_equals()`  |
| T4       | A3  | B1  | `test_isCompany_equalCompanies_equals()`      |

---

**Method:** `getEmployedWorkers()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| employees | validity(checked on construction) | A1: valid set | `employees` |
| employees.size() | number of employed workers | B1: no workers | `0` |
| employees.size() | number of employed workers | B2: has workers | `1` |
| returned set | copy behavior | C1: defensive copy returned | modifying returned set does not change internal set |

**Method:** `getEmployedWorkers()_bcc`
| Test | A | B | C | JUnit Test Name |
|------|-----|-----|-----|----------------------------------|
| T1(base) | A1| B1| --| `test_getEmployedWorkers_emptyAtStart()` |
| T2 | A1| B2| C1| `test_getEmployedWorkers_defensiveCopy()` |

**Method:** `createProject()_isp`

| Variable       | Characteristic | Partition          | Value                                      |
| -------------- | -------------- | ------------------ | ------------------------------------------ |
| String name    | null/emptiness | A1: null           | null                                       |
|                |                | A2: empty          | ""                                         |
|                |                | A3: not empty      | "Project Runway"                           |
| qualifications | size           | B1: not empty      | "design", "walking"                        |
|                |                | B2: empty          | ""                                         |
| size           | exists in enum | C1: null           | null                                       |
|                |                | C2: valid enum     | ProjectSize.Medium                         |

**Method:** `createProject()_bcc`

| Test           | A   | B   | C   | JUnit Test Name                                                         |
| -------------- | --- | --- | --- | ----------------------------------------------------------------------- |
| T1(Base)       | A3  | B1  | C2  | `test_nonEmptyName_hasQualifications_validEnum_createProject()`         |
| T2             | A1  | B1  | C2  | `test_nullName_hasQualifications_validEnum_createProject()`             |
| T3             | A2  | B1  | C2  | `test_emptyName_hasQualifications_validEnum_createProject()`            |
| T4             | A3  | B2  | C2  | `test_nonEmptyName_noQualifications_validEnum_createProject()`          |
| T5             | A3  | B1  | C1  | `test_nonEmptyName_hasQualifications_nullEnum_createProject()`          |

**Method:** `getProjects()_isp`

| Variable       | Characteristic | Partition          | Value                                      |
| -------------- | -------------- | ------------------ | ------------------------------------------ |
| this.projects  | emptiness      | A1: not empty      | "Project Runway", "Project Hail Mary"      |
|                |                | A2: empty          | ""                                         |


**Method:** `getProjects()_bcc`
| Test           | A   | JUnit Test Name                   |
| -------------- | --- | --------------------------------- |
| T1(Base)       | A1  | `test_notEmpty_getProjects()`     |
| T2             | A2  | `test_empty_getProjects()`        |

**Method:** `toString()_isp`
| Variable         | Characteristic | Partition                          | Value                                      |
| ---------------- | -------------- | ---------------------------------- | ------------------------------------------ |
| name             | String Length  | A1: length > 0                     | "Nvidia"                                   |
| available workers| List Length    | B1: length = 0                     | new HashSet()                              |
|                  |                | B2: length > 0                     | new HashSet("Bob B", "Bettie Boop")        |
| Projects         | List Length    | C1: length = 0                     | new HashSet()                              |
|                  |                | C2: length > 0                     | new HashSet("Project Runway")              |

**Method:** `toString()_bcc`

| Test           | A   | B   | C   | JUnit Test Name                                                |
| -------------- | --- | --- | --- | -------------------------------------------------------------- |
| T1(Base)       | A1  | B2  | C2  | `test_normalString_hasWorkers_hasProjects_toString()`          |
| T2             | A1  | B1  | C2  | `test_normalString_noWorkers_hasProjects_toString()`           |
| T3             | A1  | B2  | C1  | `test_normalString_hasWorkers_noProjects_toString()`           |


**Method:** `start()_isp`

| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| project | reference validity| A1: valid project in company| `p` created by `c.createProject(...)`|
| project | reference validity| A2: null project| `null`|
| project | reference validity| A3: project not in company| `p` created outside company|
| project status before start | current status| B1: PLANNED| `ProjectStatus.PLANNED`|
| project status before start | current status| B2: SUSPENDED| `ProjectStatus.SUSPENDED`|
| missing qualifications | availability| C1: no missing qualifications| all required qualifications covered|
| missing qualifications | availability| C2: missing qualifications exist| project still missing at least one qualification|

**Method:** `start()_bcc`
| Test | A | B | C | JUnit Test Name |
|------|-----|-----|-----|----------------------------------|
| T1(base) | A1| B1| C1| `test_plannedNoMissing_start()` |
| T2 | A1| B1| C2| `test_plannedMissingQualifications_start()` |
| T3 | A1| B2| C1| `test_suspendedNoMissing_start()` |
| T4 | A2| --| --| `test_nullProject_start()` |
| T5 | A3| --| --| `test_projectNotInCompany_start()` |

---

**Method:** `unassign()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| worker | reference validity| A1: valid worker| `w`|
| worker | reference validity| A2: null worker| `null`|
| project | reference validity| B1: valid project| `p`|
| project | reference validity| B2: null project| `null`|
| assignment relationship | membership| C1: worker is assigned to project| `w` is in `p.getWorkers()` and `p` is in `w.getProjects()`|

**Method:** `unassign()_bcc`
| Test | A | B | C | JUnit Test Name |
|------|-----|-----|-----|----------------------------------|
| T1(base) | A1| B1| C1| `test_removesWorkerFromProjectAndWorker_unassign()` |
| T2 | A2| B1| --| `test_nullWorker_unassign()` |
| T3 | A1| B2| --| `test_nullProject_unassign()` |

---

**Method:** `unassignAll()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| worker | reference validity| A1: valid employee worker| `w` created by `c.createWorker(...)`|
| worker | reference validity| A2: null worker| `null`|
| worker | reference validity| A3: non-employee worker| `w` created outside company|
| worker projects in company | number of company projects assigned| B1: no company projects| `0`|
| worker projects in company | number of company projects assigned| B2: one company project| `1`|
| worker projects in company | number of company projects assigned| B3: multiple company projects| `2`|
| worker projects outside company | presence of non-company projects| C1: no non-company projects| worker only has company projects|
| worker projects outside company | presence of non-company projects| C2: has non-company projects| worker has projects not owned by company|
| status of affected company projects | project status effect| D1: active company project becomes suspended if unassigned| `ProjectStatus.ACTIVE -> ProjectStatus.SUSPENDED`|

**Method:** `unassignAll()_bcc`
| Test | A | B | C | D | JUnit Test Name |
|------|-----|-----|-----|-----|----------------------------------|
| T1(base) | A1| B1| C1| --| `test_noProjects_unassignAll()` |
| T2 | A2| --| --| --| `test_nullWorker_unassignAll()` |
| T3 | A3| --| --| --| `test_nonEmployee_unassignAll()` |
| T4 | A1| B2| C1| D1| `test_companyProjectRemovedAndActiveProjectSuspended_unassignAll()` |
| T5 | A1| B1| C2| --| `test_nonCompanyProjectsSkippedAndWorkerBecomesUnavailable_unassignAll()` |
| T6 | A1| B3| C1| --| `test_multipleCompanyProjects_unassignAll()` |

---

**Method:** `createWorker()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| name | validity| A1: valid name| `"Bob"`|
| name | validity| A2: null name| `null`|
| qualifications | validity| B1: non-empty qualifications set| `qs` containing `q`|
| qualifications | validity| B2: empty qualifications set| empty `qs`|
| salary | validity| C1: valid salary| `1000.0`|
| salary | validity| C2: negative salary| `-1.0`|
| salary | validity| C3: NaN salary| `Double.NaN`|

**Method:** `createWorker()_bcc`
| Test | A | B | C | JUnit Test Name |
|------|-----|-----|-----|----------------------------------|
| T1(base) | A1| B1| C1| `test_validWorker_createWorker()` |
| T2 | A2| B1| C1| `test_nullName_createWorker()` |
| T3 | A1| B2| C1| `test_emptyQualifications_createWorker()` |
| T4 | A1| B1| C2| `test_negativeSalary_createWorker()` |
| T5 | A1| B1| C3| `test_nanSalary_createWorker()` |

---

**Method:** `getAvailableWorkers()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| available | validity(checked on construction) | A1: valid set | `available` |
| available.size() | number of available workers | B1: no workers | `0` |
| available.size() | number of available workers | B2: one worker | `1` |
| available.size() | number of available workers | B3: multiple workers | `2` |

**Method:** `getAvailableWorkers()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1(base) | A1 | B1 | `test_noWorkers_getAvailableWorkers()` |
| T2 | A1 | B2 | `test_oneWorker_getAvailableWorkers()` |
| T3 | A1 | B3 | `test_twoWorkers_getAvailableWorkers()` |

**Method:** `getAssignedWorkers()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| assigned | validity(checked on construction) | A1: valid set | `assigned` |
| assigned.size() | number of assigned workers | B1: none | `0` |
| assigned.size() | number of assigned workers | B2: one | `1` |
| assigned.size() | number of assigned workers | B3: multiple | `2` |

**Method:** `getAssignedWorkers()_bcc`
| Test | A | B | JUnit Test Name |
|------|-----|-----|----------------------------------|
| T1(base) | A1 | B1 | `test_getAssignedWorkers_emptyAtStart()` |
| T2 | A1 | B2 | `test_getAssignedWorkers_oneAssignedWorker()` |
| T3 | A1 | B3 | `test_getAssignedWorkers_multipleAssignedWorkers()` |

**Method:** `getUnassignedWorkers()_isp`
| Variable | Characteristic | Partition | Value |
|----------|----------------|-----------|-------|
| employees | validity(constructed) | A1: valid set | `employees` |
| assigned | validity(constructed) | A2: valid set | `assigned` |
| unassigned count | number of employed but not assigned | B1: none | `0` |
| unassigned count | number of employed but not assigned | B2: one | `1` |
| unassigned count | number of employed but not assigned | B3: multiple | `2` |

**Method:** `getUnassignedWorkers()_bcc`
| Test | A | B | JUnit Test Name |
|------|---|---|------------------|
| T1(base) | A1,A2 | B1 | `test_getUnassignedWorkers_emptyAtStart()` |
| T2 | A1,A2 | B2 | `test_getUnassignedWorkers_oneEmployedNoneAssigned()` |
| T3 | A1,A2 | B2 | `test_getUnassignedWorkers_twoEmployed_oneAssigned()` |
| T4 | A1,A2 | B2 | `test_getUnassignedWorkers_defensiveCopy()` |
