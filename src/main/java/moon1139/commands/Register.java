package moon1139.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.service.UserService;

@Component
public class Register extends Command {
	
	@Autowired
	private UserService userService;
	
	public Register() {
		this.setCommandNames(Arrays.asList("REGISTER", "register"));
		this.setCommandDescription("REGISTER <username>");
		this.setRequiredNumOfParams(1);
	}

	@Override
	public Response doAction(List<String> params) {
		Response response = userService.register(params.get(0));
		return response;
	}
	
}
