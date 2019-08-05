package moon1139.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import moon1139.exception.CommandNotFoundException;
import moon1139.exception.IllegalCommandArgumentException;
import moon1139.exception.NullCommandResponseException;
import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.model.UserInputs;



@Component
public class CommandManager {
	//Map
	private HashMap<String, Command> commandMap = new HashMap<>();//HashMap<String, Command>
	private List<String> commandAndParams = new ArrayList<>();
	private Response helpInfo = new Response();
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	public void autoRegisterCommands() {
		Map<String, Command> commandMap = context.getBeansOfType(Command.class);
		for (String key : commandMap.keySet()) {
            Command command = commandMap.get(key);
            this.register(command.getCommandNames(), command);
        }
		
		this.setHelpInfo();
	}
	
    public void register(List<String> commandNames, Command command) {
        for (String commandName : commandNames) {
        	commandMap.put(commandName, command);
        }	
    }
    
    private Command getCommand(String userInputs) throws CommandNotFoundException {
    	List<String> commandAndParams = Arrays.asList(userInputs.split(" "));
    	
    	Command command = commandMap.get(commandAndParams.get(0));
    	
    	if (command == null ) {
    		throw new CommandNotFoundException("No command registered for " + commandAndParams.get(0));
    	}
    	
    	return command;
    }
    //should to cli
    public Response execute(String userInputs) {
    	
    	Response response = null;
    	
    	try {
    		Command command = this.getCommand(userInputs);
    		response = command.execute(userInputs);
    	}
    	catch (CommandNotFoundException ex) {
    		System.out.println("ex.getStackTrace() " + ex.getStackTrace());
    		response = new Response();
    		response.setStatus(ResponseStatus.error);
    		response.setMessage(ex.getMessage());
    	}
    	catch (IllegalCommandArgumentException ex) {
    		response = new Response();
    		response.setStatus(ResponseStatus.error);
    		response.setMessage(ex.getMessage());
    	}
    	catch (NullCommandResponseException ex) {
    		response = new Response();
    		response.setStatus(ResponseStatus.error);
    		response.setMessage(ex.getMessage());
    		//log something ?
    	}
    	catch (Exception ex) {
    		response = new Response();
    		response.setStatus(ResponseStatus.error);
    		response.setMessage(ex.getMessage());
    	}
    	
    		
    	return response;
    }
    
    private void setHelpInfo() {
    	StringBuilder helpInfoMsg = new StringBuilder();
			
		Map<String, Command> commandMap = context.getBeansOfType(Command.class);
		for (String key : commandMap.keySet()) {
			Command command = commandMap.get(key);
			helpInfoMsg.append(command.getCommandDescription() + "\n");
		}
		helpInfoMsg.setLength(helpInfoMsg.length()-1);
			
		this.helpInfo.setMessage(helpInfoMsg.toString());
		this.helpInfo.setStatus(ResponseStatus.success);
    }
    
    public Response getHelpInfo() {
		return helpInfo;
    }
}
