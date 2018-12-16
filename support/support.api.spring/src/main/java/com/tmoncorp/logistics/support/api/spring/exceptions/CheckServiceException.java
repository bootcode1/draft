package in.java.support.api.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class CheckServiceException extends AbstractApiException
{
	/**
     * 
     */
	private static final long serialVersionUID = 8986333546037085367L;

	private static int code = Integer.MAX_VALUE;

	public CheckServiceException(String message)
	{
		super(message, code);
	}
}