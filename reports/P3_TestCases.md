# **Test Cases**

## **Use Case Tests**

### Use Case 1: View Qualifications
**Successful List View (Happy Path)**
*   **Test:** [Start -> Navigate to Qualifications -> Fetch Data -> Display Table -> End]
*   **Setup:** Server is running and has multiple qualifications in the DB.
*   **Expected Result:** Table displays a complete list of all qualifications.

**Empty Qualifications (Alternative Path)**
*   **Test:** [Start -> Navigate to Qualifications -> Fetch Data -> Display Empty State -> End]
*   **Setup:** Database contains no qualifications.
*   **Expected Result:** Page displays a "No qualifications found" message.

---

### Use Case1: Scenarios

**Successful View Table of Qualifications**
*   **Test Path:** [UC 1 (View Qualification)]
*   **Setup:** Database contains multiple qualifications.
*   **Action Steps:**
    1. Click on Qualifications tab
*   **Expected Final Result:** Table of qualifications is shown.

**Unsuccessful View Table of Qualifications**
*   **Test Path:** [UC 1 (View Qualification)]
*   **Setup:** Database contains no qualifications.
*   **Action Steps:**
    1. Click on Qualifications tab
*   **Expected Final Result:** Page displays a "No qualifications found" message.

### Use Case 2: View Workers
**Successful List View (Happy Path)**
* **Setup:** Server is running and has multiple workers in the DB.
1. User clicks on Workers tab
2. System displays the complete list of workers in the database


**Empty Workers (Alternative Path)**
* **Setup:** Database contains no workers.
1. User clicks on Workers tab
2. System displays an empty list of workers

### Use Case 3: View Projects
**Successful List View (Happy Path)**
* **Setup:** Server is running and has multiple projects in the DB.
1. User clicks on Projects tab
2. System displays the complete list of projects in the database

**Empty Projects (Alternative Path)**'
* **Setup:** Database contains no projects.
1. User clicks on Projects tab
2. System displays an empty list of projects

### Use Case 4: View Qualification Details
**Successful List View (Happy Path)**
* **Setup:** Server is running and has a qualification in the DB.
1. User clicks on Qualification tab
2. System displays the complete list of qualifications in the database
3. User clicks on a qualification in the list
4. System expands the qualification object, showing the qualification description and workers that have that particular qualification

### Use Case 5: View Worker Details
**Successful List View (Happy Path)**
* **Setup:** Server is running and has a worker in the DB.
1. User clicks on Workers tab
2. System displays the complete list of workers in the database
3. User clicks on a worker in the list
4. System expands the worker object, showing the worker's name, salary, current workload value, projects they are assigned to, and their qualifications

### Use Case 6: View Project Details
**Successful List View (Happy Path)**
* **Setup:** Server is running and has multiple projects in the DB.
1. User clicks on Projects tab
2. System displays the complete list of projects in the database
3. User clicks on a project in the list
4. System espands the project object, showing the project's name, size, status, assigned employees, required qualifications, and missing qualifications. Missing qualifications are visualized by color coding: red = missing, green = satisfied.






## **Workflow Tests**