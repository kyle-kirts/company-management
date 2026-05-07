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
**Successful View Details (Happy Path)**
* **Setup:** Server is running and has a qualification in the DB.
1. User clicks on Qualification tab
2. System displays the complete list of qualifications in the database
3. User clicks on a qualification in the list
4. System expands the qualification object, showing the qualification description and workers that have that particular qualification

### Use Case 5: View Worker Details
**Successful View Details (Happy Path)**
* **Setup:** Server is running and has a worker in the DB.
1. User clicks on Workers tab
2. System displays the complete list of workers in the database
3. User clicks on a worker in the list
4. System expands the worker object, showing the worker's name, salary, current workload value, projects they are assigned to, and their qualifications

### Use Case 6: View Project Details
**Successful View Details (Happy Path)**
* **Setup:** Server is running and has multiple projects in the DB.
1. User clicks on Projects tab
2. System displays the complete list of projects in the database
3. User clicks on a project in the list
4. System espands the project object, showing the project's name, size, status, assigned employees, required qualifications, and missing qualifications. Missing qualifications are visualized by color coding: red = missing, green = satisfied.

### Use Case 7: Create Qualification
**Successful Creation (Happy Path)**
* **Setup:** Server is running, user is on Qualifications tab
1. User clicks on create qualification button 
2. System displays a form for the user to fill out asking for a description
3. User enters a valid description (String containing 1 or more characters)
4. User clicks on enter/submit
5. System displays new qualification in the qualifications list

**Invalid Description**
* **Setup:** Server is running, user is on Qualifications tab
1. User clicks on create qualification button 
2. System displays a form for the user to fill out asking for a description
3. User leaves description box blank
4. User clicks on enter/submit
5. System does not create qualification, displays message inside of the form stating a user has given an invalid description

**Exit Form**
* **Setup:** Server is running, user is on Qualifications tab
1. User clicks on create qualification button 
2. System displays a form for the user to fill out asking for a description
3. User clicks on the cancel button
4. System closes create qualification form and returns to displaying the list of qualifications

### Use Case 8: Create Worker
**Successful Creation (Happy Path)**
* **Setup:** Server is running, user is on Workers tab
1. User clicks on create worker button 
2. System displays a form for the user to fill out asking for a name, salary and check boxes to select qualifications
3. User enters a valid name, salary and checks atleast 1 qualification check box
4. User clicks on enter/submit
5. System displays new worker in the workers list

**Invalid Name**
* **Setup:** Server is running, user is on Workers tab
1. User clicks on create worker button 
2. System displays a form for the user to fill out asking for a name, salary and check boxes to select qualifications
3. User leaves the name empty, gives a valid salary and checks atleast 1 qualification check box
4. User clicks on enter/submit
5. System does not create worker, displays message stating the user has given an invalid name

**Invalid Salary**
* **Setup:** Server is running, user is on Workers tab
1. User clicks on create worker button 
2. System displays a form for the user to fill out asking for a name, salary and check boxes to select qualifications
3. User gives a valid name, a negative salary and checks atleast 1 qualification check box
4. User clicks on enter/submit
5. System does not create worker, displays message stating the user has given an invalid salary

**Invalid Qualifications**
* **Setup:** Server is running, user is on Workers tab
1. User clicks on create worker button 
2. System displays a form for the user to fill out asking for a name, salary and check boxes to select qualifications
3. User gives a valid name, a valid salary and doesn't check any qualification check boxes
4. User clicks on enter/submit
5. System does not create worker, displays message stating the user has to select atleast one qualification

**Exit Form**
* **Setup:** Server is running, user is on Worker tab
1. User clicks on create worker button 
2. System displays a form for the user to fill out asking for a name, salary and check boxes to select qualifications
3. User clicks on the cancel button
4. System closes create worker form and returns to displaying the list of workers

### Use Case 9: Create Project
**Successful Creation (Happy Path)**
* **Setup:** Server is running, user is on Projects tab
1. User clicks on create project button
2. System diplays a form to the user, asking for a name, size and qualifications
3. User gives a valid name, selects a size, selects atleast one qualification
4. User clicks on submit/enter
5. System creates the project, displays the project in the list on Projects tab

**Invalid Name**

**Invalid Size**


### Use Case 10: Assign Worker

* **Setup:** Server is running and contains at least one worker and one project.
1. User clicks on Projects tab
2. System displays the complete list of projects
3. User selects a project
4. User clicks Assign Worker button
5. User selects a worker from the worker list
6. System assigns the worker to the selected project

* **Expected Result:** Worker appears in the assigned employees section of the selected project.


### Use Case 11: Unassign Worker

* **Setup:** Project already has assigned workers.
1. User clicks on Projects tab
2. User selects a project
3. System displays assigned workers
4. User clicks Unassign Worker button
5. User selects an assigned worker
6. System removes the worker from the project

* **Expected Result:** Worker is removed from the assigned employees list for the project.


### Use Case 12: Start Project

* **Setup:** Project contains all required qualifications and assigned workers.
1. User clicks on Projects tab
2. User selects a project
3. User clicks Start Project button
4. System updates the project status

* **Expected Result:** Project status changes from Planned to Active.


### Use Case 13: Finish Project

* **Setup:** Project status is currently Active.
1. User clicks on Projects tab
2. User selects an active project
3. User clicks Finish Project button
4. System updates the project status

* **Expected Result:** Project status changes from Active to Finished.


## **Workflow Tests**

### Workflow Test 1: Create and Start Project

* **Setup:** System contains workers and qualifications.
1. User creates a new project
2. User assigns workers to the project
3. User views project details
4. User starts the project

* **Expected Final Result:** Project appears in project list with Active status and assigned workers displayed.
* **Expected System Response:** System updates the project information, saves assigned workers, and changes the project status to Active.

### Workflow Test 2: Assign, Finish, and Unassign Worker

* **Setup:** System contains active projects and workers.
1. User selects a project
2. User assigns a worker
3. User starts the project
4. User finishes the project
5. User unassigns the worker

* **Expected Final Result:** Project status changes to Finished and worker is removed from assigned employees list.
* **Expected System Response:** System updates the project status correctly and refreshes the assigned workers list after each action.