package moon1139.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import moon1139.entity.Category;
import moon1139.entity.Listing;
import moon1139.entity.User;
import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.repository.CategoryRepository;
import moon1139.repository.ListingRepository;
import moon1139.repository.UserRepository;

@Service
public class ListingServiceImpl implements ListingService {
	
	private static Map<String,String> sortKeywordMaps = new HashMap<String,String>();
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private ListingRepository listingRepo;
	
	@Autowired
	private CategoryService cateServ;
	
	public ListingServiceImpl() {
		sortKeywordMaps.put("sort_price", "price");
		sortKeywordMaps.put("sort_time", "creationTime");
	}
	
	@Override
	public Response createListing (String userName, String title, String description, String price, String category) {
		Response response = new Response();
		
		if (userServ.findUser(userName).getStatus().equals(ResponseStatus.fail)) {
			response.setMessage("Error - unknown user");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		Listing newListing = new Listing(userName, title, description, price, category);
		listingRepo.save(newListing);
			
		cateServ.addListingCount(userName, category);
		
		response.setMessage(newListing.getListingId());
		response.setStatus(ResponseStatus.success);
		return response;
	}
	
	@Override
	public Response deleteListing (String userName, String listingId) {		
		Response response = new Response();
		
		if (listingRepo.findByListingId(listingId).isEmpty()) {
			response.setMessage("Error - listing does not exist");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		Listing listing = listingRepo.findByListingId(listingId).get(0);
		
		if (!listing.getUserName().equals(userName)) {
			//return "listing owner mismatch";
			response.setMessage("Error - listing owner mismatch");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		//if (categoryRepo.findByCategoryAndUserName(listing.getCategory(), userName).isEmpty()) {
		if (cateServ.findCategory(userName, listing.getCategory()).getStatus().equals(ResponseStatus.fail)) {
			response = cateServ.findCategory(userName, listing.getCategory());
			return response;
		}
		
		listingRepo.delete(listing);
		
		cateServ.minusListingCount(userName, listing.getCategory());
	
		response.setMessage("Success");
		response.setStatus(ResponseStatus.success);
		return response;
	}
	
	@Override
	public Response getListing (String userName, String listingId) {
		Response response = new Response();
		
		if (userServ.findUser(userName).getStatus().equals(ResponseStatus.fail)) {
			response.setMessage("Error - unknown user");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		if (listingRepo.findByListingId(listingId).isEmpty()) {
			//return "Error - not found";
			response.setMessage("Error - not found");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		Listing listing = listingRepo.findByListingId(listingId).get(0);
		
		if (!listing.getUserName().equals(userName)) {
			//return "Error - not found";
			response.setMessage("Error - not found");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		String responseMsg = listing.getTitle() + "|" + listing.getDescription() + "|" + listing.getPrice() + "|" +
				listing.getCreationTime() + "|" + listing.getCategory() + "|" + listing.getUserName();
		
		//return responseMsg;
		response.setMessage(responseMsg);
		response.setStatus(ResponseStatus.success);
		return response;
	}

	@Override
	public Response getListingsByUserNameAndCategory (String userName, String category, String sortBy, String ordering) {
		Response response = new Response();
		
		if (userServ.findUser(userName).getStatus().equals(ResponseStatus.fail)) {
			response.setMessage("Error - unknown user");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		if (cateServ.findCategory(userName, category).getStatus().equals(ResponseStatus.fail)) {
			response.setMessage("Error - category not found");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		String sortKeyword = sortKeywordMaps.get(sortBy);
		
		if (sortKeyword == null) {
			response.setMessage("No such sort keyword: " + sortBy);
			response.setStatus(ResponseStatus.error);
			return response;
		}
		
		List<Listing> listings = listingRepo.findByUserNameAndCategory(userName, category, Sort.by(Sort.Direction.fromString(ordering), sortKeyword));
		
		StringBuilder responseMsg = new StringBuilder();
		
		listings.forEach(listing-> { 
			responseMsg.append(listing.getTitle() + "|"+ listing.getDescription() + "|" + 
					listing.getPrice() + "|" + listing.getCreationTime() + "\n");
		});
		responseMsg.setLength(responseMsg.length()-1);
		
		response.setMessage(responseMsg.toString());
		response.setStatus(ResponseStatus.success);
		return response;
	}
}
