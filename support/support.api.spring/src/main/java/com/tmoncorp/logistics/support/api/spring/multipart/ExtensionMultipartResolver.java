package in.java.support.api.spring.multipart;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class ExtensionMultipartResolver extends CommonsMultipartResolver
{
	@Override
    public boolean isMultipart(HttpServletRequest request) 
	{
        return request != null && isMultipartContent(request);
    }

	private boolean isMultipartContent(HttpServletRequest request)
	{
		switch(request.getMethod().toLowerCase())
		{
			case "post":
			case "put":
			case "patch":
				return FileUploadBase.isMultipartContent(new ServletRequestContext(request));
			default:
				return false;
		}
		
//		if (!("POST".equalsIgnoreCase(request.getMethod()) 
//				|| "PUT".equalsIgnoreCase(request.getMethod()) 
//				|| "PATCH".equalsIgnoreCase(request.getMethod())))
//		{
//			return false;
//		}
//		return FileUploadBase.isMultipartContent(new ServletRequestContext(request));
	}
	
}
