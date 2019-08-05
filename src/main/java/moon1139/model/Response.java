package moon1139.model;

import org.springframework.stereotype.Component;

@Component
public class Response {

	private ResponseStatus status;
	private String message;

	public Response() {
		
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + "]";
	}
	
}
