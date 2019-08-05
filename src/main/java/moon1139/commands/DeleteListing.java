package moon1139.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.service.ListingService;

@Component
public class DeleteListing extends Command {
	
	@Autowired
	private ListingService listingService;
	
	public DeleteListing() {
		this.setCommandNames(Arrays.asList("DELETE_LISTING", "deleteListing"));
		this.setCommandDescription("DELETE_LISTING <username> <listing_id>");
		this.setRequiredNumOfParams(2);
	}
	
	@Override
	public Response doAction(List<String> params) {
		Response response = listingService.deleteListing(params.get(0), params.get(1));
		return response;
	}

}
