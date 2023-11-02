package com.nuri.mys.bems.store.jwt.jpo;

import com.nuri.mys.bems.domain.jwt.entity.User;
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
@Table(name = "nst_user_info", indexes = {
		@Index(name="created", columnList = "created", unique = false),
		@Index(name="status", columnList = "status", unique = false)
})
public class UserJpo implements Serializable {

	private static final long serialVersionUID = -5318878161053888886L;
	private static final ModelMapper modelMapper = new ModelMapper();

	public UserJpo(User operCdo) {
		modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(operCdo, this);
	}

	public void fromDomain(User operCdo) {
		modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(operCdo, this);
	}

	public User toDomain() {
		User userCDO = new User();
		modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(this, userCDO);
		return userCDO;
	}

	public static UserJpo sample() {
		return new UserJpo(User.sample());
	}

	public static void main(String[] args) {
		System.out.println(sample());
	}

	@Id
	@Column(length = 36, nullable = false, unique = true)
	private String userId;

	@Column(length = 256, nullable = false)
	private String password;

	@Column(length = 6, nullable = false)
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@Column(length = 256, nullable = false)
	private String role;

	@Column(length = 256, nullable = false)
	private String url;
}
