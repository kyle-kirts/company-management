**Randoop Test Generation Log**
java -cp "tools/randoop-all-4.3.2.jar:target/classes" randoop.main.Main gentests --classlist=tools/classes.txt --time-limit=60
Randoop for Java version "4.3.2, local changes, branch master, commit df17bc8, 2023-01-08".

Will try to generate tests for 4 classes.
PUBLIC MEMBERS=60
Explorer = ForwardGenerator(steps: 0, null steps: 0, num_sequences_generated: 0;
    allSequences: 0, regresson seqs: 0, error seqs: 0=0=0, invalid seqs: 0, subsumed_sequences: 0, num_failed_output_test: 0;
    sideEffectFreeMethods: 1113, runtimePrimitivesSeen: 38)

Progress update: steps=1, test inputs generated=0, failing inputs=0      (2026-03-26T02:41:11.845094925Z     39.5M used)
Progress update: steps=1000, test inputs generated=651, failing inputs=0      (2026-03-26T02:41:21.409781009Z     123M used)
Progress update: steps=2000, test inputs generated=1277, failing inputs=0      (2026-03-26T02:41:29.371040387Z     274M used)
Progress update: steps=3000, test inputs generated=1865, failing inputs=0      (2026-03-26T02:41:36.846084773Z     347M used)
Progress update: steps=4000, test inputs generated=2432, failing inputs=0      (2026-03-26T02:41:44.267237068Z     212M used)
Progress update: steps=5000, test inputs generated=3030, failing inputs=0      (2026-03-26T02:41:52.149999488Z     269M used)
Progress update: steps=6000, test inputs generated=3594, failing inputs=0      (2026-03-26T02:41:59.423855850Z     143M used)
Progress update: steps=7000, test inputs generated=4185, failing inputs=0      (2026-03-26T02:42:07.267430545Z     419M used)
Progress update: steps=7616, test inputs generated=4530, failing inputs=0      (2026-03-26T02:42:11.845468179Z     423M used)
Normal method executions: 13699884
Exceptional method executions: 813

Average method execution time (normal termination):      3.60e-05
Average method execution time (exceptional termination): 0.0261
Approximate memory usage 423M
Explorer = ForwardGenerator(steps: 7616, null steps: 3086, num_sequences_generated: 4530;
    allSequences: 4530, regresson seqs: 4529, error seqs: 0=0=0, invalid seqs: 0, subsumed_sequences: 0, num_failed_output_test: 1;
    sideEffectFreeMethods: 1113, runtimePrimitivesSeen: 78)

No error-revealing tests to output.

About to look for failing assertions in 2480 regression sequences.

Regression test output:
Regression test count: 2480
Writing regression JUnit tests...
                                                                                                                                                            Created file <repo>/server/RegressionTest0.java
Created file <repo>/server/RegressionTest1.java
Created file <repo>/server/RegressionTest2.java
Created file <repo>/server/RegressionTest3.java
Created file <repo>/server/RegressionTest4.java
Created file <repo>/server/RegressionTest.java
Wrote regression JUnit tests.
About to look for flaky methods.

Invalid tests generated: 0

**Randoop Tests Run Log: Excerpt from running "mvn test" command**
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running RegressionTest
[INFO] Tests run: 2480, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.187 s - in RegressionTest
[INFO] Running edu.colostate.cs415.server.RestControllerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in edu.colostate.cs415.server.RestControllerTest
[INFO] Running edu.colostate.cs415.model.CompanyTest
Mar 25, 2026 8:53:06 PM edu.colostate.cs415.model.CompanyTest test_neither_overloaded_helpful_notInAssigned_inAvailable_assign
INFO: WORKER INFO: Bob b:12:Availability - false
[INFO] Tests run: 82, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in edu.colostate.cs415.model.CompanyTest
[INFO] Running edu.colostate.cs415.model.ProjectTest
[INFO] Tests run: 49, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in edu.colostate.cs415.model.ProjectTest
[INFO] Running edu.colostate.cs415.model.QualificationTest
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in edu.colostate.cs415.model.QualificationTest
[INFO] Running edu.colostate.cs415.model.WorkerTest
[INFO] Tests run: 51, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in edu.colostate.cs415.model.WorkerTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2680, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:report (report) @ company_management ---
[INFO] Loading execution data file <repo>/server/target/jacoco.exec
[INFO] Analyzed bundle 'company_management' with 13 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

**EvoSuite Test Generation Log. Excerpts from test generation. See also *docs/testing/evosuite-report/statistics.csv***
* EvoSuite 1.0.6
* Analyzing classpath (generating inheritance tree)
  - target/classes
* Found 6 matching classes for prefix edu.colostate.cs415.model
* Current class: edu.colostate.cs415.model.Worker
* Going to generate test cases for class: edu.colostate.cs415.model.Worker
* Starting client
* Connecting to master process on port 2729
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree7744950206531031247.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Worker
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 57
  - Branch 36
  - Exception 0
  - MutationFactory 78
  - Output 37
  - Method 16
  - MethodNoException 16
  - CBranchFitnessFactory 36
* Using seed 1774655786156
* Starting evolution
[Progress:=======>                      25%] [Cov:===============================>   90%][MASTER] 17:56:42.136 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:====================>         68%] [Cov:================================>  94%][MASTER] 17:57:08.147 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Function<T, R> : Type points to itself
[Progress:==============================100%] [Cov:================================>  94%]
* Search finished after 61s and 1919 generations, 611605 statements, best individual has fitness: 19.142857120251406
...
* Writing JUnit test case 'Worker_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.Project
* Going to generate test cases for class: edu.colostate.cs415.model.Project
* Starting client
* Connecting to master process on port 20901
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree7744950206531031247.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Project
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 65
  - Branch 39
  - Exception 0
  - MutationFactory 38
  - Output 36
  - Method 17
  - MethodNoException 17
  - CBranchFitnessFactory 39
* Using seed 1774655851848
* Starting evolution
[Progress:======>                       20%] [Cov:================================>  94%][MASTER] 17:57:45.306 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:==============================100%] [Cov:================================>  94%]
* Search finished after 62s and 2193 generations, 595363 statements, best individual has fitness: 16.642857140791047
...
* Compiling and checking tests
* Writing JUnit test case 'Project_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.ProjectStatus
* Going to generate test cases for class: edu.colostate.cs415.model.ProjectStatus
* Starting client
* Connecting to master process on port 8246
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree7744950206531031247.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.ProjectStatus
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 0
  - Branch 0
  - Exception 0
  - MutationFactory 0
  - Output 0
  - Method 0
  - MethodNoException 0
  - CBranchFitnessFactory 0
* Using seed 1774655916774
* Starting evolution
[Progress:>                             0%] [Cov:===================================100%]
* Search finished after 0s and 0 generations, 563 statements, best individual has fitness: 1.0
...
* Compiling and checking tests
* Writing JUnit test case 'ProjectStatus_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.Qualification
* Going to generate test cases for class: edu.colostate.cs415.model.Qualification
* Starting client
* Connecting to master process on port 2482
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree7744950206531031247.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Qualification
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 28
  - Branch 17
  - Exception 0
  - MutationFactory 20
  - Output 13
  - Method 8
  - MethodNoException 8
  - CBranchFitnessFactory 17
* Using seed 1774655917771
* Starting evolution
[Progress:===>                          11%] [Cov:================================>  94%][MASTER] 17:58:45.776 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:======================>       75%] [Cov:================================>  94%][MASTER] 17:59:23.602 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Function<T, R> : Type points to itself
[Progress:==============================100%] [Cov:================================>  94%]
* Search finished after 61s and 5137 generations, 903946 statements, best individual has fitness: 6.333333333333333
...
* Compiling and checking tests
* Writing JUnit test case 'Qualification_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.Company
* Going to generate test cases for class: edu.colostate.cs415.model.Company
* Starting client
* Connecting to master process on port 6928
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree7744950206531031247.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.Company
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 103
  - Branch 128
  - Exception 0
  - MutationFactory 131
  - Output 58
  - Method 20
  - MethodNoException 20
[Progress:>                             0%] [Cov:>                                  0%]  - CBranchFitnessFactory 128
* Using seed 1774655980712
* Starting evolution
[Progress:======================>       75%] [Cov:===============================>   90%][MASTER] 18:00:26.830 [logback-2] ERROR TestCluster - Failed to check cache for java.util.function.Predicate<T> : Type points to itself
[Progress:==============================100%] [Cov:===============================>   91%]
* Search finished after 61s and 728 generations, 342317 statements, best individual has fitness: 63.92575756894636
...
* Compiling and checking tests
* Writing JUnit test case 'Company_ESTest' to evosuite-tests
* Done!

* Computation finished
* Current class: edu.colostate.cs415.model.ProjectSize
* Going to generate test cases for class: edu.colostate.cs415.model.ProjectSize
* Starting client
* Connecting to master process on port 17338
* Analyzing classpath: 
* Inheritance tree loaded from /tmp/ES_inheritancetree7744950206531031247.xml.gz
* Finished analyzing classpath
* Generating tests for class edu.colostate.cs415.model.ProjectSize
* Test criteria:
  - Line Coverage
  - Branch Coverage
  - Exception
  - Mutation testing (weak)
  - Method-Output Coverage
  - Top-Level Method Coverage
  - No-Exception Top-Level Method Coverage
  - Context Branch Coverage
* Setting up search algorithm for whole suite generation
* Total number of test goals: 
  - Line 4
  - Branch 1
  - Exception 0
  - MutationFactory 3
  - Output 10
  - Method 1
  - MethodNoException 1
  - CBranchFitnessFactory 1
* Using seed 1774656047235
* Starting evolution
[Progress:==============================100%] [Cov:=============================>     83%]
* Search finished after 61s and 12284 generations, 807066 statements, best individual has fitness: 8.75
* ...
* Compiling and checking tests
* Writing JUnit test case 'ProjectSize_ESTest' to evosuite-tests
* Done!

* Computation finished

**EvoSuite Tests Run Log: Excerpts from running "mvn test" command**
[INFO] Running edu.colostate.cs415.model.Company_ESTest
18:09:58.350 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Company_ESTest
...
18:09:58.626 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectSize
18:09:58.626 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize
[INFO] Tests run: 35, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.031 s - in edu.colostate.cs415.model.Worker_ESTest
[INFO] Running edu.colostate.cs415.model.Qualification_ESTest
18:09:58.646 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification_ESTest
18:09:58.646 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Qualification_ESTest'.
18:09:58.647 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Qualification_ESTest_scaffolding
...
18:09:58.661 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.ProjectStatus_ESTest'.
18:09:58.661 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.
...
18:09:58.663 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectStatus
18:09:58.663 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectStatus
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 s - in edu.colostate.cs415.model.ProjectStatus_ESTest
[INFO] Running edu.colostate.cs415.model.WorkerTest
[INFO] Tests run: 51, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in edu.colostate.cs415.model.WorkerTest
[INFO] Running edu.colostate.cs415.model.Project_ESTest
18:09:58.665 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project_ESTest
18:09:58.665 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Instrumenting class 'edu.colostate.cs415.model.Project_ESTest'.
18:09:58.666 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Seeing class for first time: edu.colostate.cs415.model.Project_ESTest_scaffolding
...
18:09:58.699 [main] INFO  o.e.r.i.CreateClassResetClassAdapter - Found static initializer in class edu/colostate/cs415/model/ProjectSize
18:09:58.699 [main] INFO  o.e.r.instrumentation.EvoClassLoader - Keeping class: edu.colostate.cs415.model.ProjectSize
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 s - in edu.colostate.cs415.model.ProjectSize_ESTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 2818, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.8:report (report) @ company_management ---
[INFO] Loading execution data file /s/bach/c/under/Net_ID/CS415/t16/server/target/jacoco.exec
[INFO] Analyzed bundle 'company_management' with 13 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------