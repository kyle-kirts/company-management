# Use Cases

## 1) View company qualifications
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on Qualifications tab | 2. System displays a table of all qualifications |

## 2) View company employed workers
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on Workers tab | 2. System displays a table of all employed workers |

## 3) View company projects
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on Projects tab | 2. System directs to ../api/projects, displays table of all projects |

## 4) View qualification details
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on a qualification row | 2. System displays workers in the company with this qualification |
| 3. User clicks on a worker | 4. System takes the user to Workers tab, displaying the worker |

## 5) View worker details.
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on worker name in list | 2. System displays the details of the employed worker including the name, salary, current workload,  value, projects they are assigned to, and their qualifications. |
|                                       | 2a. System displays an empty list or None for projects and/or qualifications |
|                                       | 2b. System displays an error when unable to load details   |
                                              
## 6) View project details
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on a Project row | 2. System displays a dropdon with name, size, status, assigned workers, required qualifications, and missing qualifications|
| 3a. User clicks on an assigned worker | 4a. System redirects to Workers tab, highlighting specific worker |
| 3b. User clicks on a qualification | 4b. System redirects to qualifications tab, highlighting specific qualifications |

## 9) Create new project 
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. (In projects page) User clicks "Create New Project" Button | 2. System Displays form with name(text), size(dropdown), required qualifications(checkbox) and submit button|
| 3a. User Enters Values and presses submit | 4a. New Project is Created, displayed in Projects tab. form disappears |
| 3b. User omits values from any part of the form and presses submit | 4b. Error message appears with text"[missing element] cannot be empty" | 
## 12) Start project
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks start button for a valid state project | 2. System transitions the project status into active |
| 3. User clicks start button for an invalid state project | 4. System reports invalid conditions to start project |

## 13) Finish project
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1a. User clicks finish button on an active project | 2a. System changes project status from active to finished |
| 1b. User clicks finish button on a planned, suspended, or finished project | 2b. System outputs error message: "[project_state] projects cannot be finished" |