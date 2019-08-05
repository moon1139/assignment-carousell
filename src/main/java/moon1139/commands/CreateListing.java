package moon1139.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.service.ListingService;

@Component
public class CreateListing extends Command {
	
	@Autowired
	private ListingService listingService;
	
	public CreateListing() {
		this.setCommandNames(Arrays.asList("CREATE_LISTING", "createListing"));
		this.setCommandDescription("CREATE_LISTING <username> <title> <description> <price> <category>");
		this.setRequiredNumOfParams(5);
	}
	
	@Override
	public Response doAction(List<String> params) {
		Response response = listingService.createListing(params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
		return response;
	}

}
