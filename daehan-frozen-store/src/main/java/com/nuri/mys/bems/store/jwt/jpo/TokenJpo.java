package com.nuri.mys.bems.store.jwt.jpo;

import com.nuri.mys.bems.domain.jwt.entity.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nst_token", indexes = {
		@Index(name = "expiration", columnList = "expiration", unique = false),
		@Index(name = "audience", columnList = "audience", unique = false)
})
public class TokenJpo implements Serializable {

	private static final long serialVersionUID = -7879628787133599313L;
	private static final ModelMapper modelMapper = new ModelMapper();

	public TokenJpo(Token operCdo) {
		modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(operCdo, this);
	}

	public void fromDomain(Token operCdo) {
		modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(operCdo, this);
	}

	public Token toDomain() {
		Token userCDO = new Token();
		modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(this, userCDO);
		return userCDO;
	}

	public static TokenJpo sample() {
		return new TokenJpo(Token.sample());
	}

	public static void main(String[] args) {
		System.out.println(sample());
	}

	@Id
	@Column(length = 64, nullable = false, unique = true)
	private String tokenId;

	@Column(length = 64, nullable = false)
	private String issuer;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date issuedAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiration;

	@Column(length = 36, nullable = false)
	private String audience;

	@Column(length = 256, nullable = false)
	private String role;
	
	@Column(length = 256, nullable = false)
	private String url;
}
