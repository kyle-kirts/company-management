# Test Cases

## Use Case 1: View Qualifications
**Successful List View (Happy Path)**
*   **Test:** [Start -> Navigate to Qualifications -> Fetch Data -> Display Table -> End]
*   **Setup:** Server is running and has multiple qualifications in the DB.
*   **Expected Result:** Table displays a complete list of all qualifications.

**Empty Qualifications (Alternative Path)**
*   **Test:** [Start -> Navigate to Qualifications -> Fetch Data -> Display Empty State -> End]
*   **Setup:** Database contains no qualifications.
*   **Expected Result:** Page displays a "No qualifications found" message.

---

## Use Case1: Scenarios

**Successful View Table of Qualifications**
*   **Test Path:** [UC 7 (View Qualification)]
*   **Setup:** Database contains multiple qualifications.
*   **Action Steps:**
    1. Click on Qualifications tab
*   **Expected Final Result:** Table of qualifications is shown.

**Unsuccessful View Table of Qualifications**
*   **Test Path:** [UC 7 (View Qualification)]
*   **Setup:** Database contains no qualifications.
*   **Action Steps:**
    1. Click on Qualifications tab
*   **Expected Final Result:** Page displays a "No qualifications found" message.