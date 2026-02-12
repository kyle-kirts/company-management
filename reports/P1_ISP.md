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
|           |                    | B2: False  | "valid descriptin" & " valid description " |

**Method:** `equals(Object other)`

| Test | A   | B   | JUnit Test Name                  |
|------|-----|-----|----------------------------------|
| T1 (base test) | A2  | B1  | `test_equalOther_equals()`|
| T2   | A2  | B2  | `test_unequalOther_equals()`   |
| T3   | A1  |     | `test_nullOther_equals()`  |
| T4   | A3  |     | `test_invalidType_equals()`  |

---

## Class: Worker

## Class: Project

## Class: Company