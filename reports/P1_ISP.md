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
**Method:** `toString()`

| Variable  | Characteristic     | Partition  | Value  |
|-----------|--------------------|------------|--------|
| description | A\) Value Type   | A1: Valid Description | "Valid Description" |
|           |                     | A2: Null Type  | null |
|           |                     | A3: Empty String | "" |


**Method:** `toString()`

| Test | A   | JUnit Test Name                  |
|------|-----|----------------------------------|
| T1 (base test) | A1  | `test_validDescription_toString()`|
| T2   | A2  | `test_nullType_toString()`   |
| T3   | A3  | `test_emptyString_toString()`  |

---

## Class: Worker

## Class: Project

## Class: Company