# POC

```
Requires JAVA 18
Gradle 7.4.2
```

## Steps

```
gradle clean build test => compile, test and generate JAR
gradle clean build test --debug => compile, test, generate JAR and watch details
gradle clean build -x test => skip tests
gradle dependencies
gradle dependencyUpdates

customization
gradle clean build copyDependencies shadowJar
```