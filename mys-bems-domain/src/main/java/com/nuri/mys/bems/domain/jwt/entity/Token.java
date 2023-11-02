package com.nuri.mys.bems.domain.jwt.entity;

import com.nuri.mys.bems.domain.jwt.domain.NameValue;
import com.nuri.mys.bems.domain.jwt.util.json.JsonUtil;
import com.nuri.mys.bems.domain.jwt.domain.NameValueList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

	private static final long serialVersionUID = -8013486474781575677L;

	private String tokenId;

	private String issuer;

	private Date issuedAt;

	private Date expiration;

	private String audience;

	private String role;

	private String url;

	public void setValues(NameValueList nameValues) {
		for (NameValue nameValue : nameValues.list()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "tokenId":
					this.tokenId = value;
				break;
				case "issuer":
					this.issuer = value;
				break;
				case "issuedAt":
					this.issuedAt = new Date(value);
				break;
				case "expiration":
					this.expiration = new Date(value);
				break;
				case "audience":
					this.audience = value;
				break;
				case "role":
					this.role = value;
				break;
				case "url":
					this.url = value;
				break;
			}
		}
	}

	public static Token sample() {
		Token sample = new Token();
		sample.setTokenId(UUID.randomUUID().toString().replace("-", ""));
		sample.setIssuer("https://nuritelecom.com/dex/");
		sample.setIssuedAt(Calendar.getInstance().getTime());
		sample.setExpiration(Calendar.getInstance().getTime());
		sample.setAudience("nuri");
		sample.setRole("ROLE_USER");
		sample.setUrl("/api/v2/user, /api/v2/admin");
		return sample;
	}

	public static Token fromJson(String json) {
		return JsonUtil.fromJson(json, Token.class);
	}

//	public static void main(String[] args) {
//		System.out.println(sample());
//	}
}
