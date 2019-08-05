package moon1139.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputs {
	private String command;
	private List<String> params = new ArrayList<>();
	private List<String> commandAndParams = new ArrayList<>();
	
	public String getCommand() {
		return command;
	}
	private void setCommand(String command) {
		this.command = command;
	}
	public List<String> getParams() {
		return params;
	}
	private void setParams(List<String> params) {
		this.params = params;
	}
	
	public void setCommandAndParams(String userInputs) {
		
		//[^\s"']+|"([^"]*)"|'([^']*)'
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(userInputs);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		    	commandAndParams.add(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		    	commandAndParams.add(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		    	commandAndParams.add(regexMatcher.group());
		    }
		} 
		
		//this.commandAndParams = Arrays.asList(userInputs.split(" "));
		this.setCommand(commandAndParams.get(0));
		this.setParams( 
				new ArrayList<>(commandAndParams.subList(1, commandAndParams.size()))
				);
	}
	
}
