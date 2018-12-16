package in.java.support.api.spring.filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.HttpPutFormContentFilter;

public class HttpMethodExtensionContentFilter extends HttpPutFormContentFilter
{

	private final FormHttpMessageConverter formConverter = new AllEncompassingFormHttpMessageConverter();

	@Override
	protected void doFilterInternal(final HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException
	{

		if (isFormContentType(request))
		{
			if (("PUT".equalsIgnoreCase(request.getMethod()) || "PATCH".equalsIgnoreCase(request.getMethod())))
			{
				super.doFilterInternal(request, response, filterChain);
				return;
			} else if ("DELETE".equalsIgnoreCase(request.getMethod()))
			{
				HttpInputMessage inputMessage = new ServletServerHttpRequest(request)
				{
					@Override
					public InputStream getBody() throws IOException
					{
						return request.getInputStream();
					}
				};
				MultiValueMap<String, String> formParameters = formConverter.read(null, inputMessage);
				HttpServletRequest wrapper = new HttpFormContentRequestWrapper(request, formParameters);
				filterChain.doFilter(wrapper, response);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean isFormContentType(HttpServletRequest request)
	{
		String contentType = request.getContentType();
		if (contentType != null)
		{
			try
			{
				MediaType mediaType = MediaType.parseMediaType(contentType);
				return (MediaType.APPLICATION_FORM_URLENCODED.includes(mediaType));
			} catch (IllegalArgumentException ex)
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public boolean isMultipart(HttpServletRequest request) 
	{
		return (request != null && ServletFileUpload.isMultipartContent(request));
	}
}
