## Table Templates

**Method:** `example(param1, param2)`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| Variable1 | Characteristic1     | Partition  | Value |
|           |                     | Partition  | Value |
|           | Characteristic2     | Partition  | Value |
| Variable2 | Characteristic1     | Partition  | Value |

---

**Method:** `example(param1, param2)`

| Test | A   | B   | C   | JUnit Test Name                  |
|------|-----|-----|-----|----------------------------------|
| T1 (base test) | A2  | B2  | C3  | `test_medHt_medWt_otherDog()`|
| T2   | A1  | B2  | C3  | `test_tallHt_medWt_otherDog()`   |
| T3   | A3  | B2  | C3  | `test_shortHt_medWt_otherDog()`  |
| T4   | A2  | B1  | C3  | `test_medHt_heavyWt_otherDog()`  |
| T5   | A2  | B3  | C3  | `test_medHt_lowWt_OtherDog()`    |
| T6   | A2  | B2  | C1  | `test_medHt_medWt_Poodle()`      |
| T7   | A2  | B2  | C2  | `test_medHt_medWt_Bulldog()`     |


## Class: Qualification
**Method:** `Qualification(String description)`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| description | A\) Value Type   | A1: null | null |
|             |                  | A2: empty string | "" |
|             |                  | A3: only whitespaces | "   " |


**Method:** `Qualification(String description)`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_nullDescription_Constructor()`|
| T2             | A2  | `test_emptyString_Constructor()`|
| T3             | A3  | `test_whitespacesDescription_Constructor()`|

---

**Method:** `toString()`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| description | A\) Value Type   | A1: Valid Description | "Valid Description" |

**Method:** `toString()`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validDescription_toString()`|

---

**Method:** `equals(Object other)`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| Object    | A\) Type           | A1: null   | null   |
|           |                    | A2: valid  | Qualification |
|           |                    | A3: invalid | Integer |
| Description | B\) equal        | B1: True   | "valid description" |
|           |                    | B2: False  | "valid description" & " valid description " |

**Method:** `equals(Object other)`

| Test | A   | B   | JUnit Test Name                  |
|------|-----|-----|----------------------------------|
| T1 (base test) | A2  | B1  | `test_equalOther_equals()`|
| T2   | A2  | B2  | `test_unequalOther_equals()`   |
| T3   | A1  |     | `test_nullOther_equals()`  |
| T4   | A3  |     | `test_invalidType_equals()`  |

---

**Method:** `hashCode()_isp`

 Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| description | A\) Value Type   | A1: valid   | "valid description" |


**Method:** `hashCode()_bcc`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validDescriptionHash_hashCode()`|

---


 Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| w | A\) Worker object  | A1: Worker Added   | worker1 |



| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validWorkerObject_addWorker()`|

---

**Method:** `getWorkers()_isp`

**Method:** `addWorker()_isp`

 Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| workers | A\) Set of workers | A1: Valid Set  | [worker1] |
|         |                     | A2: Empty Set | [] |

**Method:** `getWorkers()_bcc`

**Method:** `addWorker()_bcc`


| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validWworkerSet_getWorker()` |
| T2 | A2 | `test_emptyWorkerSet_getWorker()` |

---

## Class: Worker

**Method:** `Worker(String name, Set<Qualification> qualifications, double salary)`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| name      | A\) String Value   | A1: empty  | "" |
|           |                    | A2: valid name | "Bob B" |
|           |                    | A3: only whitespace  | "   " |
|           |                    | A4: null   | null |
| salary    | B\) salary amount  | B1: negative salary  | -1.00 |
|           |                    | B2: 0 salary | 0.00 |
|           |                    | B3: positive salary | 1.00 |
|           |                    | B4: NaN salary | NaN |
| qualifications  | C\) Set Value | C1: null   | null |
|           |                    | C2: empty  | [] |
|           |                    | C3: atleast 1 qualification | ["Qualification"] |


**Method:** `Worker(String name, Set<Qualification> qualifications, double salary)`

| Test | A   | B   | C   | JUnit Test Name                  |
|------|-----|-----|-----|----------------------------------|
| T1 (base test) | A2  | B3  | C3  | `test_validWorker_Worker()`|
| T2   | A1  | B3  | C2  | `test_emptyName_Worker()`        |
| T3   | A3  | B3  | C2  | `test_whitespaceName_Worker()`   |
| T4   | A4  | B3  | C2  | `test_nullName_Worker()`         |
| T5   | A2  | B1  | C2  | `test_negativeSalary_Worker()`   |
| T6   | A2  | B2  | C2  | `test_zeroSalary_Worker()`       |
| T7   | A2  | B3  | C1  | `test_nullQsSet_Worker()`        |
| T8   | A2  | B3  | C2  | `test_emptyQsSet_Worker()`       |
| T8   | A2  | B3  | C3  | `test_nonemptyQsSet_Worker()`    |
| T9   | A2  | B4  | C3  | `test_nanSalary_Worker()`        |

---

**Method:** `getQualifications()`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| qualifications | A\) empty     | A1: True   | []     |
|           |                    | A2: False  | ["Qualification1", "Qualification2"] |

**Method:** `getQualifications()`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_emptyQualifications_getQualifications()`|
| T2   | A2  | `test_multipleQualifications_getQualifications()`   |

---

**Method:** `hashCode()_isp`

 Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| name | A\) Value Type   | A1: valid   | "valid description" |


**Method:** `hashCode()_bcc`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validNameHash_hashCode()`|

---

**Method:** `toString()_isp`

|Variable  | Characteristic     | Partition  | Value  |
|----------|--------------------|------------|--------|
|name      |String Length       |A1: length > 0  |"Jamiroquai"|
|projects  |List Length         |B1: length = 0  |new HashSet()|
|          |                    |B2: length > 0  |new HashSet("Synkronized", "Dynamite")|
|qualifications|List Length     |C1: length = 0  |new HashSet()|
|          |                    |C2: length > 0  |new HashSet("Virtual Insanity", "Canned Heat")|
|salary    |size                |D1: salary = 0  |0      |
|          |                    |D2: 0 < salary < INTEGER.MAX_Value|123456|
|          |                    |D3: salary > INTEGER.MAX_Value|

**Method:** `toString()_bcc`

| Test | A   | B   | C   | D   | JUnit Test Name                  |
|------|-----|-----|-----|-----|----------------------------------|
| T1 (base test)|A1|B1|C2| D2  | `test_noProjects_LongQualifications_normalSalary_toString()`|
| T2   | A1  | B2  | C2  | D2  | `test_LongProjects_LongQualifications_normalSalary_toString()`  |
| T3   | A1  | B1  | C1  | D2  | `test_noProjects_NoQualifications_normalSalary_toString()`  |
| T4   | A1  | B1  | C2  | D1  | `test_noProjects_LongQualifications_noSalary_toString()`   |
| T5   | A1  | B1  | C2  | D3  | `test_noProjects_LongQualifications_hugeSalary_toString()` |

---

**Method:** `getSalary()`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| salary    | value              | 0          | 0.00    |
|           |                    | 0 <        | 1000.00 |

**Method:** `getSalary()`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A2  | `test_zeroSalary_getSalary()`|
| T2   | A1  | `test_positiveSalary_getSalary()`   |

---

**Method:** `getName()`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| name      | A\) value          | A1: valid      | "Bob B" |

**Method:** `getName()`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1 | `test_validName_getName()`|

---

**Method:** `setSalary(double salary)`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| salary    | A\) value          | A1: NaN        | NaN    |
|           |                    | A2: < 0        | -100.00 |
|           |                    | A3: 0          | 0.00   |
|           |                    | A4: 0 <        | 100.00 |

**Method:** `setSalary(double salary)`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A4  | `test_positiveSalary_setSalary()`  |
| T2   | A2  | `test_negativeSalary_setSalary()`            |
| T3   | A3  | `test_zeroSalary_setSalary()`                |
| T4   | A1  | `test_nanSalary_setSalary()`                 |

---

## Class: Project
**Method:** `Project(String name, Set<Qualification> qualifications, ProjectSize size)_isp`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| name      | String Value       | A1: null   | null   |
|           |                    | A2: not null| "Project Runway"|
|           |                    | A3: Empty String| ""|
| qualifications| set size       | B1: null   | null   |
|           |                    | B2: empty set| New HashSet()|
|           |                    | B3: size > 1| New HashSet("Sean Kelley", "Grace Kelsey")|
| size      | exists in enum     | C1: null   | null   |
|           |                    | C2: small project| ProjectSize.SMALL|
|           |                    | C3: medium project| ProjectSize.MEDIUM|
|           |                    | C4: large project| ProjectSize.BIG|

**Method:** `Project(String name, Set<Qualification> qualifications, ProjectSize size)_bcc`

| Test | A   | B   | C   | JUnit Test Name                  |
|------|-----|-----|-----|----------------------------------|
| T1(Base)| A2| B3 | C3  | `test_nonNullName_someQualifications_mediumProject()`|
| T2   | A1  | B3  | C3  | `test_NullName_someQualifications_mediumProject()`|
| T3   | A3  | B3  | C3  | `test_emptyName_someQualifications_mediumProject()`|
| T4   | A2  | B1  | C3  | `test_nonNullName_nullQualifications_mediumProject()`|
| T5   | A2  | B2  | C3  | `test_nonNullName_noQualifications_mediumProject()`|
| T6   | A2  | B3  | C1  | `test_nonNullName_someQualifications_nullProject()`|
| T7   | A2  | B3  | C2  | `test_nonNullName_someQualifications_smallProject()`|
| T8   | A2  | B3  | C4  | `test_nonNullName_someQualifications_bigProject()`|

**Method:** `getName()_isp`
| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| name      | validity(checked on construction)| A1: valid name| "Project Runway"|

**Method:** `getName()_bcc`
| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1(base) | A1| `test_validName_getName()      |

## Class: Company