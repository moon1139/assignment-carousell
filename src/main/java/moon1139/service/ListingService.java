package moon1139.service;

import java.util.List;

import moon1139.entity.Listing;
import moon1139.model.Response;

public interface ListingService {
	
	public Response createListing (String userName, String title, String description, String price, String category);
	
	public Response deleteListing (String userName, String listingId);
	
	public Response getListing (String userName, String listingId);
	
	public Response getListingsByUserNameAndCategory (String userName, String category, String sortBy, String ordering);
}
