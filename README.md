# POC

```
Requires JAVA 18. Soon movement to 19
Gradle 8.5

sdk default java 18.0.1-open
sdk default gradle 7.5.1
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
gradle clean build copyDependencies 

gradle clean build copyDependencies shadowJar

full build
gradle clean build dependencyUpdates
```

``` 
TODO

The following dependencies have later milestone versions:
 - com.github.johnrengelman.shadow:com.github.johnrengelman.shadow.gradle.plugin [7.1.2 -> 8.1.1]
 
 - io.jsonwebtoken:jjwt-impl [0.11.5 -> 0.12.5]
     https://github.com/jwtk/jjwt
 - io.jsonwebtoken:jjwt-jackson [0.11.5 -> 0.12.5]
     https://github.com/jwtk/jjwt
     
 - io.netty:netty-all [4.1.56.Final -> 5.0.0.Alpha2]
     http://netty.io/
 - org.javamodularity.moduleplugin:org.javamodularity.moduleplugin.gradle.plugin [1.8.12 -> 1.8.15]
 
 - org.junit.jupiter:junit-jupiter-engine [5.8.2 -> 5.10.2]
     https://junit.org/junit5/
     
 - org.springframework.boot:org.springframework.boot.gradle.plugin [2.7.0 -> 3.2.4]
     https://spring.io/projects/spring-boot
     
 - org.springframework.boot:spring-boot-starter-actuator [3.2.4 -> 3.3.0-M3]
     https://spring.io/projects/spring-boot
 - org.springframework.boot:spring-boot-starter-data-jpa [3.2.4 -> 3.3.0-M3]
     https://spring.io/projects/spring-boot
 - org.springframework.boot:spring-boot-starter-security [3.2.4 -> 3.3.0-M3]
     https://spring.io/projects/spring-boot
 - org.springframework.boot:spring-boot-starter-test [3.2.4 -> 3.3.0-M3]
     https://spring.io/projects/spring-boot
 - org.springframework.boot:spring-boot-starter-web [3.2.4 -> 3.3.0-M3]
     https://spring.io/projects/spring-boot
 - org.springframework.boot:spring-boot-starter-websocket [3.2.4 -> 3.3.0-M3]
     https://spring.io/projects/spring-boot

```