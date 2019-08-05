package moon1139.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import moon1139.entity.User;
import moon1139.model.Response;
import moon1139.model.ResponseStatus;
import moon1139.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Response findUser (String userName) {
		Response response = new Response();
		
		if (userRepo.findByUserName(userName).isEmpty()) {
			response.setMessage("failed - user not exist");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		response.setMessage("Success");
		response.setStatus(ResponseStatus.success);
		return response;
	}
	
	@Override
	public Response register (String userName) {
		Response response = new Response();
		
		if (findUser(userName).getStatus().equals(ResponseStatus.success)) {
			response.setMessage("Error - user already existing");
			response.setStatus(ResponseStatus.fail);
			return response;
		}
		
		userRepo.save(new User(userName));

		response.setMessage("Success");
		response.setStatus(ResponseStatus.success);
		return response;
	}

}
