# Use Cases

## 1) View company qualifications
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on Qualifications tab | 2. System displays a table of all qualifications |

## 2) View company employed workers
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on Workers tab | 2. System displays a table of all employed workers |

## 4) View qualification details
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks on a qualification row | 2. System displays workers in the company with this qualification |
| 3. User clicks on a worker | 4. System takes the user to Workers tab, displaying the worker |

## 12) Start project
| Actor action                  | System Response                                |
|-------------------------------|------------------------------------------------|
| 1. User clicks start button for a valid state project | 2. System transitions the project status into active |
| 3. User clicks start button for an invalid state project | 4. System reports invalid conditions to start project |