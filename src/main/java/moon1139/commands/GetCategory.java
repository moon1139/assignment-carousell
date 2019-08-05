package moon1139.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.service.CategoryService;

@Component
public class GetCategory extends Command{

	@Autowired
	private CategoryService categoryService;

	public GetCategory() {
		this.setCommandNames(Arrays.asList("GET_CATEGORY", "getCategory"));
		this.setCommandDescription("GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}");
		this.setRequiredNumOfParams(4);
	}
	
	@Override
	public Response doAction(List<String> params) {
		Response response = categoryService.getCategory(params.get(0), params.get(1), params.get(2), params.get(3));
		return response;
	}
	
}
