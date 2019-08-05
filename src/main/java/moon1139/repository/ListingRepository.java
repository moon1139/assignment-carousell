package moon1139.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import moon1139.entity.Listing;


public interface ListingRepository extends CrudRepository<Listing, Long> {

	public List<Listing> findByListingId(String listingId);
	public List<Listing> findByUserNameAndCategory(String userName, String category, Sort sort);
}
