**Class: Project**
| Method                          | Statement Coverage   | Branch Coverage |
| ------------------------------- | -------------------- | --------------- |
|isHelpful(Worker)                | 92%                  | 83%             |
|toDTO()                          | 0%                   | 0%              |
|Project(String, Set, ProjectSize)| 100%                 | 100%            |
|getMissingQualifications()       | 100%                 | 100%            |
|toString()                       | 100%                 | 100%            |
|equals(Object)                   | 100%                 | 100%            |
|addWorker(Worker)                | 100%                 | 100%            |
|removeWorker(Worker)             | 100%                 | 100%            |
|addQualification(Qualification)  | 100%                 | 100%            |
|setStatus(ProjectStatus)         | 100%                 | 100%            |
|hashcode()                       | 100%                 | n/a             |
|removeAllWorkers()               | 100%                 | n/a             |
|getName()                        | 100%                 | n/a             |
|getSize()                        | 100%                 | n/a             |
|getStatus()                      | 100%                 | n/a             |
|getWorkers()                     | 100%                 | n/a             |
|getRequiredQualifications        | 100%                 | n/a             |


**Class: Qualification**
| Method                          | Statement Coverage   | Branch Coverage |
| ------------------------------- | -------------------- | --------------- |
|Qualification(String)            | 100%                 | 100%            |
|toDTO()                          | 100%                 | n/a             |
|equals(Object)                   | 100%                 | 100%            |
|validateDescription(String)      | 100%                 | 100%            |
|removeWorker(Worker)             | 100%                 | 100%            |
|hashCode()                       | 100%                 | n/a             |
|addWorker(Worker)                | 100%                 | n/a             |
|toString()                       | 100%                 | n/a             |
|getWorkers()                     | 100%                 | n/a             |

**Class: Worker**
| Method                          | Statement Coverage   | Branch Coverage |
| ------------------------------- | -------------------- | --------------- |
|toDTO()                          | 0%                   | n/a             |
|Worker(String, Set, double)      | 100%                 | 100%            |
|toString()                       | 100%                 | n/a             |
|getWorkload()                    | 100%                 | 100%            |
|equals(Object)                   | 100%                 | 100%            |
|setSalary(double)                | 100%                 | 100%            |
|willOverload(Project)            | 100%                 | 100%            |
|hashCode()                       | 100%                 | n/a             |
|isAvailable()                    | 100%                 | 100%            |
|addQualification(Qualification)  | 100%                 | n/a             |
|addProject(Project)              | 100%                 | n/a             |
|removeProject(Project)           | 100%                 | n/a             |
|getName()                        | 100%                 | n/a             |
|getSalary()                      | 100%                 | n/a             |
|getQualifications()              | 100%                 | n/a             |
|getProjects()                    | 100%                 | n/a             |

**Company**
| Method                          | Statement Coverage   | Branch Coverage |
| ------------------------------- | -------------------- | --------------- |
