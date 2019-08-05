package moon1139.service;

import java.util.List;

import moon1139.entity.User;
import moon1139.model.Response;

public interface UserService {

//	List<User> findByUserName (String userName);
	
	public Response findUser (String userName);
	public Response register (String userName);
}
