# Java 11 Migration Notes

- Java `11.0.1`

```
$ java --version
java 11.0.1 2018-10-16 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.1+13-LTS)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.1+13-LTS, mixed mode)
```
- Gradle `4.10.2`

```
$ gradle -version
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass (file:/Users/matthieun/Utilities/gradle-4.10.2/lib/groovy-all-2.4.15.jar) to method java.lang.Object.finalize()
WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release

------------------------------------------------------------
Gradle 4.10.2
------------------------------------------------------------

Build time:   2018-09-19 18:10:15 UTC
Revision:     b4d8d5d170bb4ba516e88d7fe5647e2323d791dd

Kotlin DSL:   1.0-rc-6
Kotlin:       1.2.61
Groovy:       2.4.15
Ant:          Apache Ant(TM) version 1.9.11 compiled on March 23 2018
JVM:          11.0.1 (Oracle Corporation 11.0.1+13-LTS)
OS:           Mac OS X 10.14.1 x86_64
```

## Eclipse has issues with building with source version 11

### Symptom

Eclipse cannot build

### Solution

Install buildship 2.2.2
http://download.eclipse.org/buildship/updates/e48/releases/2.x/2.2.2.v20181003-1024
