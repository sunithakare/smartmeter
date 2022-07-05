package com.envision.login;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8359239937092605695L;

	public static final long JWT_TOKEN_VALIDITY = 10*60*60;

	@Value("1234")
	private String secret;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public String generateToken(UserDetails userDetails, Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("ROLE", "USER");
		claims.put("USER", ((UserDetails)authentication.getPrincipal()).getUsername());
//		 String authorities = authentication.getAuthorities().stream()
//	                .map(GrantedAuthority::getAuthority)
//	                .collect(Collectors.joining(","));
//		 claims.put("AUTHORITIES_KEY", authorities);
		return doGenerateToken(claims, userDetails.getUsername());
	}

	public String generateRolesToken(UserDetails userDetails, Authentication authentication) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("ROLE", "USER");
		 String authorities = authentication.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.joining(","));
		 claims.put("AUTHORITIES_KEY", authorities);
		return doGenerateToken(claims, "USER");
	}
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	 public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {

//	        final JwtParser jwtParser = Jwts.parser().setSigningKey(secret);
//
//	        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
//
//	        final Claims claims = claimsJws.getBody();
//
//	        final Collection<? extends GrantedAuthority> authorities =
//	                Arrays.stream(claims.get("AUTHORITIES_KEY").toString().split(","))
//	                        .map(SimpleGrantedAuthority::new)
//	                        .collect(Collectors.toList());

	        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	    }
}