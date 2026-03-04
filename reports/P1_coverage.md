# Coverage Report

## Class: Project
| Element | Missed Instructions | Instruction Cov. | Missed Branches | Branch Cov. | Missed Cxty | Cxty | Missed Lines | Lines | Missed Methods | Methods |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Project(String, Set, ProjectSize) | 53 | 100% | 8 | 100% | 0 | 5 | 0 | 15 | 0 | 1 |
| toDTO() | 48 | 100% | n/a | n/a | 0 | 1 | 0 | 13 | 0 | 1 |
| isHelpful(Worker) | 27 | 100% | 6 | 100% | 0 | 4 | 0 | 7 | 0 | 1 |
| getMissingQualifications() | 25 | 100% | 2 | 100% | 0 | 2 | 0 | 4 | 0 | 1 |
| toString() | 19 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| equals(Object) | 18 | 100% | 4 | 100% | 0 | 3 | 0 | 4 | 0 | 1 |
| addWorker(Worker) | 13 | 100% | 2 | 100% | 0 | 2 | 0 | 3 | 0 | 1 |
| removeWorker(Worker) | 13 | 100% | 2 | 100% | 0 | 2 | 0 | 3 | 0 | 1 |
| addQualification(Qualification) | 13 | 100% | 2 | 100% | 0 | 2 | 0 | 4 | 0 | 1 |
| setStatus(ProjectStatus) | 11 | 100% | 2 | 100% | 0 | 2 | 0 | 3 | 0 | 1 |
| hashCode() | 9 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| removeAllWorkers() | 4 | 100% | n/a | n/a | 0 | 1 | 0 | 2 | 0 | 1 |
| getName() | 3 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| getSize() | 3 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| getStatus() | 3 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| getWorkers() | 3 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| getRequiredQualifications() | 3 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| lambda$1(int) | 3 | 100% | n/a | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| **Total** | **0 of 271** | **100%** | **0 of 28** | **100%** | **0** | **32** | **0** | **66** | **0** | **18** |

## Class: Qualification
| Element | Missed Instructions | Instruction Cov. | Missed Branches | Branch Cov. | Missed Cxty | Cxty | Missed Lines | Lines | Missed Methods | Methods |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Qualification(String) | 20 | 100% | 2 | 100% | 0 | 2 | 0 | 6 | 0 | 1 |
| toDTO() | 18 | 100% | 0 | n/a | 0 | 1 | 0 | 5 | 0 | 1 |
| equals(Object) | 14 | 100% | 2 | 100% | 0 | 2 | 0 | 4 | 0 | 1 |
| validateDescription(String) | 12 | 100% | 4 | 100% | 0 | 3 | 0 | 5 | 0 | 1 |
| removeWorker(Worker) | 10 | 100% | 2 | 100% | 0 | 2 | 0 | 3 | 0 | 1 |
| hashCode() | 9 | 100% | 0 | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| addWorker(Worker) | 6 | 100% | 0 | n/a | 0 | 1 | 0 | 2 | 0 | 1 |
| toString() | 3 | 100% | 0 | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| getWorkers() | 3 | 100% | 0 | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| lambda$1(int) | 3 | 100% | 0 | n/a | 0 | 1 | 0 | 1 | 0 | 1 |
| **Total** | **0 of 98** | **100%** | **0 of 10** | **100%** | **0** | **15** | **0** | **29** | **0** | **10** |

## Class: Worker 
| Element | Missed Instructions | Cov. | Missed Branches | Cov. | Missed Cxty | Missed Lines | Missed Methods |
|---|---:|---:|---:|---:|---:|---:|---:|
| Worker(String, Set, double) | 52 | 100% | 10 | 100% | 6 | 14 | 1 |
| toDTO() | 32 | 100% | 0 | n/a | 1 | 8 | 1 |
| toString() | 30 | 100% | 0 | n/a | 1 | 2 | 1 |
| getWorkload() | 27 | 100% | 4 | 100% | 3 | 6 | 1 |
| equals(Object) | 18 | 100% | 4 | 100% | 3 | 4 | 1 |
| setSalary(double) | 16 | 100% | 4 | 100% | 3 | 4 | 1 |
| willOverload(Project) | 16 | 100% | 2 | 100% | 2 | 5 | 1 |
| hashCode() | 9 | 100% | 0 | n/a | 1 | 1 | 1 |
| isAvailable() | 8 | 100% | 2 | 100% | 2 | 3 | 1 |
| addQualification(Qualification) | 6 | 100% | 0 | n/a | 1 | 2 | 1 |
| addProject(Project) | 6 | 100% | 0 | n/a | 1 | 2 | 1 |
| removeProject(Project) | 6 | 100% | 0 | n/a | 1 | 2 | 1 |
| getName() | 3 | 100% | 0 | n/a | 1 | 1 | 1 |
| getSalary() | 3 | 100% | 0 | n/a | 1 | 1 | 1 |
| getQualifications() | 3 | 100% | 0 | n/a | 1 | 1 | 1 |
| getProjects() | 3 | 100% | 0 | n/a | 1 | 1 | 1 |
| lambda$1(int) | 3 | 100% | 0 | n/a | 1 | 1 | 1 |
| **Total** | **0 of 241** | **100%** | **0 of 26** | **100%** | **30** | **58** | **17** |

## Class: Company
| Method                          | Statement Coverage   | Branch Coverage |
| ------------------------------- | -------------------- | --------------- |
| Element | Missed Instructions | Cov. | Missed Branches | Cov. | Missed Cxty | Missed Lines | Missed Methods |
|---|---:|---:|---:|---:|---:|---:|---:|
| unassign(Worker, Project) | 17 | 77% | 9 | 55% | 8 | 5 | 0 |
| getUnavailableWorkers() | 2 | 0% | 0 | n/a | 1 | 1 | 1 |
| getAssignedWorkers() | 2 | 0% | 0 | n/a | 1 | 1 | 1 |
| getUnassignedWorkers() | 2 | 0% | 0 | n/a | 1 | 1 | 1 |
| finish(Project) | 1 | 0% | 0 | n/a | 1 | 1 | 1 |
| assign(Worker, Project) | 1 | 0% | 0 | n/a | 1 | 1 | 1 |
| unassignAll(Worker) | 1 | 0% | 0 | n/a | 1 | 1 | 1 |
| createWorker(String, Set, double) | 0 | 100% | 2 | 87% | 2 | 0 | 0 |
| Company(String) | 0 | 100% | 0 | 100% | 0 | 0 | 0 |
| start(Project) | 0 | 100% | 1 | 90% | 1 | 0 | 0 |
| toString() | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| createQualification(String) | 0 | 100% | 0 | 100% | 0 | 0 | 0 |
| equals(Object) | 0 | 100% | 0 | 100% | 0 | 0 | 0 |
| createProject(String, Set, ProjectSize) | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| getEmployedWorkers() | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| getAvailableWorkers() | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| hashCode() | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| getName() | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| getProjects() | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| getQualifications() | 0 | 100% | 0 | n/a | 0 | 0 | 0 |
| **Total** | **26 of 316** | **91%** | **12 of 58** | **79%** | **17** | **11** | **6** |

# Reflection

For our fully implemented Project, Worker and Qualification classes, we achieved 100% coverage over our methods, statements and branches. Our test suites did a good job for handling inputs that would simulate normal and abnormal behaviors. Such as invalid inputs into constructors or void methods which we verified would return false or throw an exception.