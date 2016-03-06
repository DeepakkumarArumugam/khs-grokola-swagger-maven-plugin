package com.khs.grokola.swagger.maven.plugin;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.khs.grokola.swagger.maven.plugin.service.GrokolaApiService;

@Mojo(name = "grokola-swagger-upload")
public class GrokolaSwaggerUploadGoal extends AbstractMojo {

	@Parameter(defaultValue = "https://beta.grokola.com")
	private String grokolaBaseUrl;

	@Parameter(defaultValue = "Miscellaneous")
	private String grokolaCategory;

	@Parameter(required = true)
	private String grokolaAuthToken;

	@Parameter(required = true)
	private File swaggerJsonFile;

	public void execute() throws MojoExecutionException {
		try {
			getLog().info("--------------------------------------------------");
			getLog().info("--------------------------------------------------");
			getLog().info("-- HELLO FROM THE GROKOLA SWAGGER UPLOAD PLUGIN --");
			getLog().info("-- grokolaBaseUrl: " + grokolaBaseUrl);
			getLog().info("-- grokolaCategory: " + grokolaCategory);
			getLog().info("-- grokolaAuthToken: " + grokolaAuthToken);
			getLog().info("--------------------------------------------------");
			getLog().info("--------------------------------------------------");

			// Read the file from the specified resource location
			String jsonContent = readFile(swaggerJsonFile);

			// Send the content to Grokola via the API
			GrokolaApiService grokolaApi = new GrokolaApiService(grokolaBaseUrl, grokolaAuthToken);
			grokolaApi.setLog(getLog());
			grokolaApi.uploadSwaggerJson(grokolaCategory, jsonContent);
		} catch (Exception ex) {
			getLog().error(ex);
		}
	}

	private String readFile(File file) throws IOException {
		String fileContents = FileUtils.readFileToString(file);
		return fileContents;
	}

}
