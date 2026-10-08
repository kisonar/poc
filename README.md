# POC

```
Requires JAVA 79.x
Gradle 9.8.0

sdk default java 27.0.0-oracle
sdk default gradle 9.6.0
```

https://docs.gradle.org/current/userguide/compatibility.html

## It contains

``` 
JDK learning
Guacamole example API usage - is out of data as Guacamole does not support jakarta packages
```

### Steps for building

```
gradle clean build test => compile, test and generate JAR
gradle clean build test --debug => compile, test, generate JAR and watch details
gradle clean build -x test => skip tests
gradle dependencies => shows dependencies

customization
gradle clean build copyDependencies 
gradle clean build copyDependencies shadowJar
```
