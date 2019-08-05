package moon1139.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import moon1139.entity.Category;
import moon1139.entity.Listing;
import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.repository.CategoryRepository;
import moon1139.repository.ListingRepository;
import moon1139.repository.UserRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private ListingService listingServ;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Response createCategory (String userName , String category) {
		Response response = new Response();
		
		if (this.findCategory(userName, category).getStatus().equals(ResponseStatus.success)) {
			response.setMessage("user category already exist");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		Category newCategory = new Category(category, userName);
		categoryRepo.save(newCategory);
		//return "Success";
		response.setMessage("Success");
		response.setStatus(ResponseStatus.success);
		return response;
	}
	
	@Override
	public Response findCategory(String userName, String category) {
		Response response = new Response();
		
		if (userServ.findUser(userName).getStatus().equals(ResponseStatus.fail)) {
			//return "Error - unknown user";
			response.setMessage("Error - unknown user");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		if (categoryRepo.findByCategoryAndUserName(category, userName).isEmpty()) {
			//return "Error - category not found";
			response.setMessage("Error - category not found");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		response.setMessage("category found");
		response.setStatus(ResponseStatus.success);
		return response;
	}
	
	@Override
	public Response getCategory(String userName, String category, String sortBy, String ordering) {
		Response response = listingServ.getListingsByUserNameAndCategory(userName, category, sortBy, ordering);
		return response;
	}
	
	@Override
	public Response addListingCount(String userName, String  category) {
		Response response = new Response();
		
		if (this.findCategory(userName, category).getStatus().equals(ResponseStatus.fail)) {
			this.createCategory(userName, category);
		}
		
		Category Category = categoryRepo.findByCategoryAndUserName(category, userName).get(0);
		Category.addListingCount();
		categoryRepo.save(Category);
		
		response.setMessage("Success - current listingCount is:" + Category.getListingCount());
		response.setStatus(ResponseStatus.success);
		return response;
	}
	
	@Override
	public Response minusListingCount(String userName, String  category) {
		Response response = new Response();
		
		if (this.findCategory(userName, category).getStatus().equals(ResponseStatus.fail)) {
			response = this.findCategory(userName, category);
			return response;
		}
		
		Category Category = categoryRepo.findByCategoryAndUserName(category, userName).get(0);
		Category.minusListingCount();
		
		if (Category.getListingCount() == 0) {
			categoryRepo.delete(Category);
			response.setMessage("Success - category deleted");
			response.setStatus(ResponseStatus.success);			
		} else if (Category.getListingCount() < 0) {
			categoryRepo.delete(Category);
			response.setMessage("Error - listing count is below 0");
			response.setStatus(ResponseStatus.error);
		} else {
			categoryRepo.save(Category);
			response.setMessage("Success - current listingCount is: " + Category.getListingCount());
			response.setStatus(ResponseStatus.success);
		}
		
		return response;
	}

	@Override
	public Response getTopCategory(String userName) {
		Response response = new Response();
		
		if (userServ.findUser(userName).getStatus().equals(ResponseStatus.fail)) {
			//return "Error - unknown user";
			response.setMessage("Error - unknown user");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		if (categoryRepo.findByUserName(userName).isEmpty()) {
			//return "user category doesn't exist";
			response.setMessage("user category doesn't exist");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		List categories = categoryRepo.findByUserName(userName, Sort.by(Sort.Direction.fromString("DESC"), "listingCount"));
		Category category = (Category) categories.get(0);
		int listingCount = category.getListingCount();
		
		List topCategories = categoryRepo.findByUserNameAndListingCount(userName, listingCount);
		StringBuilder responseMsg = new StringBuilder();
		topCategories.forEach(topCategory-> { 
			responseMsg.append(((Category) topCategory).getCategory() + "\n");
		});
		responseMsg.setLength(responseMsg.length()-1);
		
		response.setMessage(responseMsg.toString());
		response.setStatus(ResponseStatus.success);
		return response;
	}
	
}
