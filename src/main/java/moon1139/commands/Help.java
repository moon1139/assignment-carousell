package moon1139.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.model.ResponseStatus;

@Component
public class Help extends Command {
	
	@Autowired
	CommandManager commandManager;
	
	public Help() {
		this.setCommandNames(Arrays.asList("HELP", "help"));
		this.setCommandDescription("HELP : I am here to help ^^");
		this.setRequiredNumOfParams(0);
	}

	@Override
	public Response doAction(List<String> params) {
		Response response = commandManager.getHelpInfo();
		return response;
	}
	
}
