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


***Method:** `hashCode()_bcc`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validDescriptionHash_hashCode()`|

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


***Method:** `hashCode()_bcc`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validNameHash_hashCode()`|

---

## Class: Project

## Class: Company