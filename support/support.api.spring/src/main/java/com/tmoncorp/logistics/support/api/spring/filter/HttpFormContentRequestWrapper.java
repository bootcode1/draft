package in.java.support.api.spring.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class HttpFormContentRequestWrapper extends HttpServletRequestWrapper {

	private MultiValueMap<String, String> formParameters;

	public HttpFormContentRequestWrapper(HttpServletRequest request, MultiValueMap<String, String> parameters) {
		super(request);
		this.formParameters = (parameters != null) ? parameters : new LinkedMultiValueMap<String, String>();
	}

	@Override
	public String getParameter(String name) {
		String queryStringValue = super.getParameter(name);
		String formValue = this.formParameters.getFirst(name);
		return (queryStringValue != null) ?  queryStringValue : formValue;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> result = new LinkedHashMap<String, String[]>();
		Enumeration<String> names = this.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			result.put(name, this.getParameterValues(name));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<String> getParameterNames() {
		Set<String> names = new LinkedHashSet<String>();
		names.addAll(Collections.list(super.getParameterNames()));
		names.addAll(this.formParameters.keySet());
		return Collections.enumeration(names);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] queryStringValues = super.getParameterValues(name);
		List<String> formValues = this.formParameters.get(name);
		if (formValues == null) {
			return queryStringValues;
		}
		else if (queryStringValues == null) {
			return formValues.toArray(new String[formValues.size()]);
		}
		else {
			List<String> result = new ArrayList<String>();
			result.addAll(Arrays.asList(queryStringValues));
			result.addAll(formValues);
			return result.toArray(new String[result.size()]);
		}
	}
}