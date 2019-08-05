package moon1139;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jayway.jsonpath.internal.Path;

import moon1139.commands.CommandManager;

public class IntegrationTest {

	@Autowired
	CommandManager invoker;
	
	@Autowired
	ConfigurableApplicationContext context;
	
	@Test
	public void test1() {
		
		context = SpringApplication.run(Application.class);
		
		invoker.autoRegisterCommands();
		
		StringBuilder sb = new StringBuilder();
		
		//System.out.println(System.getProperty("user.dir"));
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(".\\src\\test\\java\\moon1139\\resources\\Consolidated STDIN.txt"))) {

			// read line by line
			String line;
			while ((line = br.readLine()) != null) {
				
				//invoker.execute(line);
				sb.append(line).append("\n");
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		System.out.println(sb);
	}

}
