package moon1139;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import moon1139.commands.Cli;
import moon1139.commands.CommandManager;
import moon1139.model.Response;

@SpringBootApplication
public class Application {
	
	@Autowired
	static ConfigurableApplicationContext context;
	
	@Autowired
	private Cli cli;
	
	public static void main(String[] args) throws Exception {
		context = SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	public PromptProvider myPromptProvider() {
//		return () -> new AttributedString("# ", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
//	}
	
	@Bean
	public CommandLineRunner cliRunner(ConfigurableApplicationContext context) {
		return (args) -> {
			cli.run();
		};
	}
}
