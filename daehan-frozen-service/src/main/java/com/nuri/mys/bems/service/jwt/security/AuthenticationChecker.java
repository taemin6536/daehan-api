package com.nuri.mys.bems.service.jwt.security;

import com.nuri.mys.bems.domain.jwt.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Set;

@Component
public class AuthenticationChecker {

	private AntPathMatcher mAntPathMatcher = new AntPathMatcher();

	public boolean check(HttpServletRequest request, Authentication auth) {
		Object prin = auth.getPrincipal();
		if (prin instanceof User) {
			User user = (User) auth.getPrincipal();
			Set<String> url = StringUtils.commaDelimitedListToSet(user.getUrl());
			for (Iterator<String> iterator = url.iterator(); iterator.hasNext();) {
				String string = iterator.next();
				if (mAntPathMatcher.matchStart(string, request.getRequestURI())) {
					return true;
				}
			}
		}
		return false;
	}
}
