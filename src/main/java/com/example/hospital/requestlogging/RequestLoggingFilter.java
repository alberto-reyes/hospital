package com.example.hospital.requestlogging;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Component
public class RequestLoggingFilter implements Filter {

	static Logger log = Logger.getLogger(RequestLoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpRequest);
		CachedHttpServletRequest cachedHttpServletRequest = new CachedHttpServletRequest(requestWrapper);
		String requestBody = getRequestBody(cachedHttpServletRequest);
		log.info(httpRequest.getMethod() + " " + httpRequest.getRequestURI() + " "
				+ requestBody);
		chain.doFilter(cachedHttpServletRequest, response);
	}

	private String getRequestBody(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}
		return stringBuilder.toString();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
