package com.envision.login.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.envision.login.model.UserPojo;

/**
 * @author abishek.chandran
 *
 */
public class UserPrincipalDetails implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserPojo user;

	public UserPrincipalDetails(UserPojo user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authoritues = new ArrayList<>();
		
		for (String rolesAndpermission : user.getRolesAndPermission()) {
			authoritues.add(new SimpleGrantedAuthority(rolesAndpermission));
		}
		return authoritues;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getIsactive().equals("Y");
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return user.getIsactive().equals("Y");
	}

}
