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

| Variable  | Characteristic  | Partition | Value |
| --------- | --------------- | --------- | ----- |
| project   | A\) value           | A1: Project Object | Project("Project", qs, ProjectSize.MEDIUM); |
| projects  | B\) empty           | B1: True      | empty set |
|           |                     | B2: False     | projects size 2 |

**Method:** `addProject(Project project), getProjects(), removeProject(Project project)`

| Test           | A   | B   | JUnit Test Name                 |
| -------------- | --- | --- | ------------------------------- |
| T1 (base test) | A1  | B1  | `test_validProject_addProject()`   |
| T2             |     | B1  | `test_emptyProjects_getProjects()`  |
| T3             | A1  | B2  | `test_validProject_removeProject()` |

---

**Method:** `getWorkload()`

| Variable  | Characteristic  | Partition | Value |
| --------- | --------------- | --------- | ----- |
| projects  | A\) Empty projects| A1: True | empty set projects |
|           |                 | A2: False | SMALL, MEDIUM, BIG projects |
| project   | B\) Portion of FINISHED projects | B1: At least one finished project, but not all finished | ProjectStatus.FINISHED |
|           |                 | B2: No finished projects | ProjectStatus.PlANNED |
|           |                 | B3: All projects are finished projects | ProjectStatus.FINISHED |

**Method:** `getWorkload()`

| Test           | A   | B   | JUnit Test Name                 |
| -------------- | --- | --- | ------------------------------- |
| T1 (base test) | A2  | B2  | `test_validWorkload_getWorkload()`   |
| T2             | A2  | B3  | `test_onlyFinishedProjects_getWorkload()`  |
| T3             | A1  | B2  | `test_emptyProjects_getWorkload()` |
| T4             | A2  | B1  | `test_oneFinishedProject_getWorkload()` |
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

**Method:** `hashCode()_isp`

| Variable | Characteristic | Partition | Value               |
| -------- | -------------- | --------- | ------------------- |
| name     | A\) Value Type | A1: valid | "valid description" |

**Method:** `hashCode()_bcc`

| Test           | A   | JUnit Test Name                 |
| -------------- | --- | ------------------------------- |
| T1 (base test) | A1  | `test_validNameHash_hashCode()` |

**Method:** `getSize()_isp`
| Variable | Characteristic | Partition | Value |
|-----------|--------------------|------------|--------|
| size | valid enum value | A1: valid |ProjectSize.MEDIUM|

**Method:** `getSize()_bcc`

| Test           | A   | JUnit Test Name                |
| -------------- | --- | ------------------------------ |
| T1 (base test) | A1  | `test_validEnumSize_getSize()` |

**Method:** `equals()_isp`
| Variable | Characteristic | Partition | Value               |
| -------- | -------------- | --------- | ------------------- |
| Object O | Object Type    | A1: null  | null                |
|          |                | A2: not Project | "NotAProject" |
|          |                | A3: Project | new Project()     |
|Name, O.name | equality    | B1: name = O.name | "Projected" |
|          |                | B2: name != O.name | "Projected", "UnProjected" |

**Method:** `equals()_bcc`
| Test           | A   | B   | JUnit Test Name                |
| -------------- | --- | --- | ------------------------------ |
| T1(Base)       | A3  | B1  | `test_projectO_equalNames_equals()` |
| T2             | A1  | B1  | `test_nullO_equalNames_equals()` |
| T3             | A2  | B1  | `test_nonprojectO_equalNames_equals()` |
| T4             | A3  | B2  | `test_projecto_nonEqualNames_equals()` |

**Method:** `setStatus()_isp`
| Variable | Characteristic | Partition | Value               |
|input status| nullness     | A1: null  | null                |
|          |                | A2: not null| ProjectStatus.ACTIVE|

**Method:** `setStatus()_bcc`
| Test           | A   | JUnit Test Name                |
| -------------- | --- | ------------------------------ |
| T1(Base)       | A1  | `testNullStatus_setStatus()`   |
| T2             | A2  | `testNotNullStatus_setStatus()`|

**Method:** `getStatus_isp`
| Variable | Characteristic | Partition | Value               |
| this.status| valid enum   | A1: valid | ProjectStatus.PLANNED |

**Method:** `getStatus()_bcc`
| Test           | A   | JUnit Test Name                |
| -------------- | --- | ------------------------------ |
| T1(Base)       | A1  | `testValidEnumgetStatus()`     |

**Method:** `getWorkers()_isp`
| Variable | Characteristic | Partition | Value               |
| set(worker) | Emptiness   | A1: empty | new HashSet()       |
|          |                | A2: not emptY| new HashSet(Worker w, Worker s) |

**Method:** `getWorkers()_bcc`
| Test           | A   | JUnit Test Name                |
| T1(Base)       | A2  | `test_hasWorkers_getWorkers()` |
| T2             | A1  | `test_noWorkers_getWorkers()`  |

**Method:** `addWorker()_isp`
| Variable | Characteristic | Partition | Value               |
| Worker worker | Existence in set | A1: null  | null         |
|          |                | A2: not Duplicate | new Worker("newguy", qs, 10000) |
|          |                | A3: Duplicate | new Worker("Bob B", qs, 10000) |
| set(worker) | Emptiness   | B1: empty | new HashSet()       |
|          |                | B2: not empty| new HashSet(Worker w, Worker s) |

**Method:** `addWorker()_bcc`
| Test           | A   | B   | JUnit Test Name                |
| T1(Base)       | A2  | B2  | `test_realWorker_hasWorkers_addWorker()` |
| T2             | A1  | B2  | `test_nullWorker_hasWorkers_addWorker()` |
| T3             | A3  | B2  | `test_dupeWorker_hasWorkers_addWorker()` | 
| T4             | A2  | B1  | `test_realWorker_noWorkers_addWorker()`  |

**Method:** `removeWorker()_isp`
| Variable | Characteristic | Partition | Value               |
| Worker w | relationship to worker list | A1: null | null        |
|          |                | A2: worker not in worker list | new Worker("New", qs, 0) |
|          |                | A3: worker in worker list |  new Worker("Bob B", qs, 10000) |
| set(worker) | Emptiness   | B1: empty | new HashSet()       |
|          |                | B2: not empty| new HashSet(Worker w, Worker s) |

**Method:** `removeWorker()_bcc`
| Test           | A   | B   | JUnit Test Name                |
| T1(Base)       | A2  | B2  | `test_notinList_hasWorkers_removeWorker()` |
| T2             | A1  | B2  | `test_nullWorker_hasWorkers_removeWorker()` |
| T3             | A3  | B2  | `test_inList_hasWorkers_removeWorker()` |
| T4             | A2  | B1  | `test_notinList_noWorkers_removeWorker()` |



---

## Class: Company
