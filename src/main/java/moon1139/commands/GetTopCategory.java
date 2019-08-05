package moon1139.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.service.CategoryService;

@Component
public class GetTopCategory extends Command {
	
	@Autowired
	private CategoryService categoryService;
	
	public GetTopCategory() {
		this.setCommandNames(Arrays.asList("GET_TOP_CATEGORY", "getTopCategory"));
		this.setCommandDescription("GET_TOP_CATEGORY <username>");
		this.setRequiredNumOfParams(1);
	}

	@Override
	public Response doAction(List<String> params) {
		Response response = categoryService.getTopCategory(params.get(0));
		return response;
	}
}
