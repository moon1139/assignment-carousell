package moon1139.commands;

import java.util.Scanner;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.model.ResponseStatus;

@Component
public class Cli implements InitializingBean {
	
	@Autowired
	private CommandManager commandManager;
	
	//private boolean closeLoop = false;
	
	public Cli() {
		
	}
	
	public void run() {
		this.showWelcomeMsg();
		this.startMainLoop();
		this.showExitMsg();
	}
	
	private void showWelcomeMsg() {
		System.out.print("ε(*’-‘)з†.*･ﾟ☆ヾ(ﾟ∇ﾟ)ﾉ☆ﾟ･*.†ε(‘-‘*)з\n");
		System.out.print("===================================\n");
		System.out.print("        Welcome to the CLI!        \n");
		System.out.print("===================================\n");
		System.out.print("ε(*’-‘)з†.*･ﾟ☆ヾ(ﾟ∇ﾟ)ﾉ☆ﾟ･*.†ε(‘-‘*)з\n");
	}
	
	private void startMainLoop() {
		Scanner scanner = new Scanner(System.in);
		String userInputs;
		Response response;
		
		while (true) {
			System.out.print("# ");
			userInputs = scanner.nextLine();
			
			response = this.input(userInputs);
			System.out.println(response.getMessage());
			
			if (response.getStatus().equals(ResponseStatus.exit)) {
				break;
			}
		}
		
		scanner.close();
	}
	
	private void showExitMsg() {
		System.out.print(" ☆ﾟ｡+｡ヾ(*'▽'*)/ ~ ﾏﾀﾈｯ!!ヾ☆Bye-Byeヾ☆  \n");
		System.out.print("===================================\n");
		System.out.print("           Exiting CLI...          \n");
		System.out.print("===================================\n");
		System.out.print(" ☆ﾟ｡+｡ヾ(*'▽'*)/ ~ ﾏﾀﾈｯ!!ヾ☆Bye-Byeヾ☆  \n");
	}
	
	public Response input(String userInputs) {
		return commandManager.execute(userInputs);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		commandManager.autoRegisterCommands();
	}
	
}
