package org.springframework.web.servlet.view.json;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MappingJackson2JsonpView extends MappingJackson2JsonView {

	public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

	@Override
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!request.getParameterMap().containsKey("callback")) {
			super.render(model, request, response);
			return;
		}

		String callback = request.getParameter("callback");
		response.getOutputStream().write(new String(callback + "(").getBytes());
		super.render(model, request, response);
		response.getOutputStream().write(");".getBytes());
		response.setContentType(DEFAULT_CONTENT_TYPE);

	}
}
