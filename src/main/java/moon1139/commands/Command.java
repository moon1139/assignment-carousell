package moon1139.commands;

import java.util.List;

import moon1139.exception.IllegalCommandArgumentException;
import moon1139.exception.NullCommandResponseException;
import moon1139.model.Response;
import moon1139.model.UserInputs;

public abstract class Command {
	
	private List<String> commandNames;
	
	private String commandDescription;
	
	private int requiredNumOfParams;
	
	public List<String> getCommandNames() {
		return commandNames;
	}

	public void setCommandNames(List<String> commandNames) {
		this.commandNames = commandNames;
	}
	
	public String getCommandDescription() {
		return commandDescription;
	}

	public void setCommandDescription(String commandDescription) {
		this.commandDescription = commandDescription;
	}	
	
	public int getRequiredNumOfParams() {
		return requiredNumOfParams;
	}

	public void setRequiredNumOfParams(int requiredNumOfParams) {
		this.requiredNumOfParams = requiredNumOfParams;
	}//setter no public
	
	public Response execute(String userInputs) throws NullCommandResponseException {
		
		UserInputs parsedUserInputs = beforeAction(userInputs);
		Response response = doAction(parsedUserInputs.getParams());
		afterAction(response);
		
		return response;
	}
	
	private UserInputs beforeAction(String userInputs) throws IllegalCommandArgumentException {
		UserInputs parsedUserInputs = new UserInputs();
    	parsedUserInputs.setCommandAndParams(userInputs);
    	
    	validateParams(parsedUserInputs.getParams());
    	
    	return parsedUserInputs;
	}
	
	protected abstract Response doAction(List<String> params); //throws ServiceException

	private void afterAction(Response response) throws NullCommandResponseException {
		if (response == null) {
			throw new NullCommandResponseException("Command response is null");
		}
	}
	
	private void validateParams(List<String> params) throws IllegalCommandArgumentException{
		if (params.size() > this.getRequiredNumOfParams()) {
			throw new IllegalCommandArgumentException("Validator: Too many parameters. Usage: "+ this.getCommandDescription());
		}
		if (params.size() < this.getRequiredNumOfParams()) {
			throw new IllegalCommandArgumentException("Validator: Too few parameters. Usage: "+ this.getCommandDescription());
		}
	}
}
