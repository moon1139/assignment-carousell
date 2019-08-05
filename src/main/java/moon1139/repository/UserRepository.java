package moon1139.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import moon1139.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findByUserName(String userName);
	
}