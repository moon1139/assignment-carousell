package moon1139.service;

import moon1139.model.Response;

public interface CategoryService {
	
	public Response createCategory(String userName, String category);
	
	public Response findCategory(String userName, String category);
	
	public Response getCategory(String userName, String category, String sortBy, String ordering);
	
	public Response addListingCount(String userName, String category);
	
	public Response minusListingCount(String userName, String category);
	
	public Response getTopCategory(String userName);

}
