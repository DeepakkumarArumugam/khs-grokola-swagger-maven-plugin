# grokola-swagger-plugin
GrokOla Swagger Integration Maven Plugin

## Overview
The Grokola Swagger Integration Maven Plugin can be used with Maven projects to upload Swagger JSON files describing the project's API to Grokola via the Maven build life-cycle.

## Getting Started
Add this plugin to the build/plugins section of your API project's pom.xml like so:
```xml
			<plugin>
				<groupId>com.khs</groupId>
				<artifactId>khs-grokola-swagger-maven-plugin</artifactId>
				<version>0.0.1-SNAPSHOT</version>
				<configuration>			
					<grokolaBaseUrl>https://beta.grokola.com</grokolaBaseUrl>
					<grokolaCategory>Miscellaneous</grokolaCategory>
					<grokolaAuthToken>xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx</grokolaAuthToken>
					<swaggerJsonFile>src/main/resources/static/swagger.json</swaggerJsonFile>
				</configuration>
				<executions>
					<execution>
						<phase>install</phase>
						<goals>
							<goal>grokola-swagger-upload</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
```
In the `configuration` section, specify the following settings:

| Setting          | Description                            | required/optional | Default value            |
| -----------------| -------------------------------------- | ----------------- | ------------------------ |
| grokolaBaseUrl   | the URL of the Grokola instance        | optional          | https://beta.grokola.com |
| grokolaCategory  | the category name in Grokola           | optional          | Miscellaneous            |
| grokolaAuthToken | the auth token from Grokola            | required          |                          |
| swaggerJsonFile  | the file path of the Swagger JSON file | required          |                          |

In the `executions` section of the plugin, specify a Maven build life-cycle phase and a goal of `grokola-swagger-upload`. In this example, we used the `install` phase but you might prefer to used something else, like `deploy`.
