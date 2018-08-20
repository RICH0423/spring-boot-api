package com.rich.api.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.rich.api.gateway.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class AccessFilter extends ZuulFilter {

	@Autowired
	private ObjectMapper jacksonObjectMapper;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info( "{} request to {}" , request.getMethod(), request.getRequestURL().toString());

		Object accessToken = request.getParameter("token");
		if (accessToken == null) {
			String msg = "access token is empty";

			ctx.setSendZuulResponse( false );
			ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
			try {
				ctx.setResponseBody(jacksonObjectMapper.writeValueAsString(new CommonResponse(HttpStatus.UNAUTHORIZED.value(), msg)));
			} catch (JsonProcessingException e) {
				log.error("Serialize CommonResponse failed", e);
			}
			return null ;
		}

		log.info( "access token ok" );
		return null ;
	}
}
