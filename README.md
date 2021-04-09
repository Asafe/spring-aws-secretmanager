<h1 align="center">
  <img src="https://simpleicons.org/icons/spring.svg" width="55" />
  <img src="https://simpleicons.org/icons/amazonaws.svg" width="55"/>
  <br>
  Secrets Manager
</h1>
<p align="center">
  <em>springboot-aws-secretmanager</em> 🧩⚙️ is a component 
  <br>for loading <b>➕AWS SecretsManager</b> parameters 
  <br>in the <b>➕Spring Boot</b> application
</p>

[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/sdacode/springboot-aws-secretsmanager/blob/main/LICENSE)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.sdacode/springboot-aws-secretsmanager/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.sdacode/springboot-aws-secretsmanager)

## Installation 👨‍💻👩‍💻

### Include in your pom.xml
```
<dependency>
    <groupId>io.github.sdacode</groupId>
    <artifactId>springboot-aws-secretsmanager</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Using the lib
Use a property that is prefixed with `AwsSecretsManager::<yourkey>`

Example: **Class(@Component or @Service)**
```
@Value("${AwsSecretsManager::/example/secrets/parameter}")
String value;
```

Example: **application.properties**
```
example.secrets.parameter=${AwsSecretsManager::/example/secrets/parameter}
```

## Properties configuration 🔧📋

By default:
```
aws.secretsmanager.enable=true
aws.secretsmanager.region=us-east-1
```
When including the library in your pom.xml, it is enabled by default, to disable it, use the following property:
```
aws.secretsmanager.enable=false
```
To change the region:
```
aws.secretsmanager.region=<region-string>
```

## Contributing 🤝🤙
Open an issue to report bugs or to request additional features. Pull requests are always welcome.
