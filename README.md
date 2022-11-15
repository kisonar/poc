# POC

```
Requires JAVA 18. Soon movement to 19
Gradle 7.5.1
```

## It contains

``` 
JDK learning
Guacamole example API usage
```

### Steps for building

```
gradle clean build test => compile, test and generate JAR
gradle clean build test --debug => compile, test, generate JAR and watch details
gradle clean build -x test => skip tests
gradle dependencies - shows dependencies
gradle dependencyUpdates

customization
gradle clean build copyDependencies shadowJar
```