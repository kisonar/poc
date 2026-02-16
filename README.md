# POC

```
Requires JAVA 25.x
Gradle 9.x

sdk default java 25.0.1-open
sdk default gradle 9.3.1
```

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