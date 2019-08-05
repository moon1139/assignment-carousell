package moon1139.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.model.ResponseStatus;

@Component
public class Exit extends Command{
	
	@Autowired
	Cli cli;
	
	public Exit() {
		this.setCommandNames(Arrays.asList("Exit", "exit"));
		this.setCommandDescription("Exit : to exit");
		this.setRequiredNumOfParams(0);
	}
	
	@Override
	public Response doAction(List<String> params) {
		//cli.closeLoop();
		Response response = new Response();
		response.setMessage("");
		response.setStatus(ResponseStatus.exit);
		return response;
	}
}
