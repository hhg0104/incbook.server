package mebook.auth;

import java.lang.annotation.Inherited;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticInterceptor extends HandlerInterceptorAdapter {

	private static final String AUTH_KEY = "D0404DA24D18483895DB5676E0A274A4";

	private static final String AUTH_KEY_STR = "auth_key";
	
	/**
	 * {@link Inherited}
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		if(paramMap.containsKey(AUTH_KEY_STR) == false) {
			return false;
		}
		
		String paramAuthKey = paramMap.get(AUTH_KEY_STR)[0].trim();
		
		if(paramAuthKey.equals(AUTH_KEY) == false) {
			return false;
		}
		
		return true;
	}
}
