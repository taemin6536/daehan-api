package com.nuri.mys.bems.domain.jwt.entity;

import com.nuri.mys.bems.domain.jwt.domain.NameValue;
import com.nuri.mys.bems.domain.jwt.util.json.JsonUtil;
import com.nuri.mys.bems.domain.jwt.domain.NameValueList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	private static final long serialVersionUID = 2863408751430021949L;

	private String userId;

	private Date created;

	private String password;

	private String role;

	private String status;

	private Date updated;

	private String url;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(StringUtils.commaDelimitedListToStringArray(this.role)).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public void setValues(NameValueList nameValues) {
		for (NameValue nameValue : nameValues.list()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "userId":
					this.userId = value;
				break;
				case "created":
					this.created = new Date(value);
				break;
				case "password":
					this.password = value;
				break;
				case "role":
					this.role = value;
				break;
				case "status":
					this.status = value;
				break;
				case "updated":
					this.updated = new Date(value);
				break;
				case "url":
					this.url = value;
				break;
			}
		}
	}

	public static User sample() {
		User sample = new User();
		sample.setUserId("nuri");;
		sample.setCreated(Calendar.getInstance().getTime());;
		sample.setPassword("pass");
		sample.setUrl("/admin/,/user/");
		sample.setStatus("ACTIVE");
		sample.setUpdated(Calendar.getInstance().getTime());
		return sample;
	}

	public static User fromJson(String json) {
		return JsonUtil.fromJson(json, User.class);
	}

//	public static void main(String[] args) {
//		System.out.println(sample());
//	}
}
