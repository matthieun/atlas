# Java 10 Migration Notes

- Java `10.0.2`

```
$ java -version
java version "10.0.2" 2018-07-17
Java(TM) SE Runtime Environment 18.3 (build 10.0.2+13)
Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10.0.2+13, mixed mode)
```
- Gradle `4.9`

```
$ gradle -version
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass (file:/.../gradle-4.9/lib/groovy-all-2.4.12.jar) to method java.lang.Object.finalize()
WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release

------------------------------------------------------------
Gradle 4.9
------------------------------------------------------------

Build time:   2018-07-16 08:14:03 UTC
Revision:     efcf8c1cf533b03c70f394f270f46a174c738efc

Kotlin DSL:   0.18.4
Kotlin:       1.2.41
Groovy:       2.4.12
Ant:          Apache Ant(TM) version 1.9.11 compiled on March 23 2018
JVM:          10.0.2 ("Oracle Corporation" 10.0.2+13)
OS:           Mac ...
```

## JTS StyleFactory is not an ImageIO SPI Class

### Symptom

Unit tests fail:

```bash
org.openstreetmap.atlas.geography.boundary.CountryBoundaryMapTest > testDuplicateBoundary FAILED
    java.lang.Exception: Unexpected exception, expected<org.openstreetmap.atlas.exception.CoreException> but was<java.lang.IllegalArgumentException>

        Caused by:
        java.lang.IllegalArgumentException: org.geotools.styling.StyleFactory is not an ImageIO SPI class
            at java.desktop/javax.imageio.spi.ServiceRegistry.checkClassAllowed(ServiceRegistry.java:722)
            at java.desktop/javax.imageio.spi.ServiceRegistry.<init>(ServiceRegistry.java:117)
            at org.geotools.factory.FactoryRegistry.<init>(FactoryRegistry.java:155)
            at org.geotools.factory.FactoryCreator.<init>(FactoryCreator.java:91)
            at org.geotools.factory.CommonFactoryFinder.getServiceRegistry(CommonFactoryFinder.java:76)
            at org.geotools.factory.CommonFactoryFinder.getFileDataStoreFactories(CommonFactoryFinder.java:202)
            at org.geotools.data.FileDataStoreFinder.getAvailableDataStores(FileDataStoreFinder.java:166)
            at org.geotools.data.FileDataStoreFinder.getDataStore(FileDataStoreFinder.java:86)
            at org.geotools.data.FileDataStoreFinder.getDataStore(FileDataStoreFinder.java:69)
            at org.openstreetmap.atlas.geography.boundary.CountryBoundaryMap.readFromShapeFile(CountryBoundaryMap.java:679)
            at org.openstreetmap.atlas.geography.boundary.CountryBoundaryMap.fromShapeFile(CountryBoundaryMap.java:251)
            at org.openstreetmap.atlas.geography.boundary.CountryBoundaryMapTest.testDuplicateBoundary(CountryBoundaryMapTest.java:160)
```
### Link

https://osgeo-org.atlassian.net/browse/GEOT-5289

### Resolution

Depend on geotools 19.2

Also depend on jts-core 1.14.0 (used by geotools above)

## SpotlessJava `removeUnusedImports()` fails

### Symptom

spotlessJava fails:

```bash
> Task :spotlessJava FAILED
Step 'removeUnusedImports' found problem in 'pyatlas/resources/CreateTestAtlas.java':
javax/tools/JavaFileManager$Location
java.lang.NoClassDefFoundError: javax/tools/JavaFileManager$Location
        at com.diffplug.spotless.java.GoogleJavaFormatStep$State.createRemoveUnusedImportsOnly(GoogleJavaFormatStep.java:113)
        at com.diffplug.spotless.FormatterStepImpl$Standard.format(FormatterStepImpl.java:76)
        at com.diffplug.spotless.FormatterStep$Strict.format(FormatterStep.java:76)
        at com.diffplug.spotless.Formatter.compute(Formatter.java:230)
        at com.diffplug.spotless.Formatter.isClean(Formatter.java:167)
        at com.diffplug.gradle.spotless.SpotlessTask.check(SpotlessTask.java:216)
        at com.diffplug.gradle.spotless.SpotlessTask.performAction(SpotlessTask.java:172)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at org.gradle.internal.reflect.JavaMethod.invoke(JavaMethod.java:73)
        at org.gradle.api.internal.project.taskfactory.IncrementalTaskAction.doExecute(IncrementalTaskAction.java:50)
        at org.gradle.api.internal.project.taskfactory.StandardTaskAction.execute(StandardTaskAction.java:39)
        at org.gradle.api.internal.project.taskfactory.StandardTaskAction.execute(StandardTaskAction.java:26)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter$1.run(ExecuteActionsTaskExecuter.java:131)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:317)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:309)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor.execute(DefaultBuildOperationExecutor.java:185)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:97)
        at org.gradle.internal.operations.DelegatingBuildOperationExecutor.run(DelegatingBuildOperationExecutor.java:31)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeAction(ExecuteActionsTaskExecuter.java:120)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeActions(ExecuteActionsTaskExecuter.java:99)
        at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:77)
        at org.gradle.api.internal.tasks.execution.OutputDirectoryCreatingTaskExecuter.execute(OutputDirectoryCreatingTaskExecuter.java:51)
        at org.gradle.api.internal.tasks.execution.SkipUpToDateTaskExecuter.execute(SkipUpToDateTaskExecuter.java:59)
        at org.gradle.api.internal.tasks.execution.ResolveTaskOutputCachingStateExecuter.execute(ResolveTaskOutputCachingStateExecuter.java:54)
        at org.gradle.api.internal.tasks.execution.ValidatingTaskExecuter.execute(ValidatingTaskExecuter.java:59)
        at org.gradle.api.internal.tasks.execution.SkipEmptySourceFilesTaskExecuter.execute(SkipEmptySourceFilesTaskExecuter.java:101)
        at org.gradle.api.internal.tasks.execution.FinalizeInputFilePropertiesTaskExecuter.execute(FinalizeInputFilePropertiesTaskExecuter.java:44)
        at org.gradle.api.internal.tasks.execution.CleanupStaleOutputsExecuter.execute(CleanupStaleOutputsExecuter.java:91)
        at org.gradle.api.internal.tasks.execution.ResolveTaskArtifactStateTaskExecuter.execute(ResolveTaskArtifactStateTaskExecuter.java:62)
        at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:59)
        at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:54)
        at org.gradle.api.internal.tasks.execution.ExecuteAtMostOnceTaskExecuter.execute(ExecuteAtMostOnceTaskExecuter.java:43)
        at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:34)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.run(EventFiringTaskExecuter.java:51)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:317)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:309)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor.execute(DefaultBuildOperationExecutor.java:185)
        at org.gradle.internal.operations.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:97)
        at org.gradle.internal.operations.DelegatingBuildOperationExecutor.run(DelegatingBuildOperationExecutor.java:31)
        at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:46)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$ExecuteTaskAction.execute(DefaultTaskExecutionGraph.java:262)
        at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$ExecuteTaskAction.execute(DefaultTaskExecutionGraph.java:246)
        at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker$1.execute(DefaultTaskPlanExecutor.java:136)
        at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker$1.execute(DefaultTaskPlanExecutor.java:130)
        at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker.execute(DefaultTaskPlanExecutor.java:201)
        at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker.executeWithTask(DefaultTaskPlanExecutor.java:192)
        at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker.run(DefaultTaskPlanExecutor.java:130)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:63)
        at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:46)
        at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:55)
Caused by: java.lang.ClassNotFoundException: javax.tools.JavaFileManager$Location
        ... 52 more
```

### Link

https://github.com/diffplug/spotless/issues/83

### Resolution

Temporarily disable `removeUnusedImports()` in `quality.gradle`:

```groovy
// Disabled with Java 9+: https://github.com/diffplug/spotless/issues/83
//removeUnusedImports()
```

## Javadoc & HTML 5

### Symptom

Javadoc wants to have html version set:

```
> Task :javadoc
javadoc: warning - You have not specified the version of HTML to use.
The default is currently HTML 4.01, but this will change to HTML5
in a future release. To suppress this warning, please specify the
version of HTML used in your documentation comments and to be
generated by this doclet, using the -html4 or -html5 options.
1 warning
```

### Resolution

Add `html5=true` in `javadoc` in `build.gradle`.

```groovy
javadoc {
    options.addBooleanOption('html5', true)
}
```
