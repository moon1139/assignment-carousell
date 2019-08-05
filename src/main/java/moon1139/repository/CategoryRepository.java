package moon1139.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import moon1139.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	public List<Category> findByCategory(String category);
	public List<Category> findByCategoryAndUserName(String category, String userName);
	public List<Category> findByUserName(String userName);
	public List<Category> findByUserName(String userName, Sort sort);
	public List<Category> findByUserNameAndListingCount(String userName, int listingCount);

}
