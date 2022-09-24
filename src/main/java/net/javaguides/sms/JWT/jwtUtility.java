package net.javaguides.sms.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.javaguides.sms.entity.AppUser;
import net.javaguides.sms.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.table.DefaultTableCellRenderer;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtUtility implements Serializable {
    public static  final  long serialVersionUID = 234234543423L;
    public  final long  jwt_Token_Validity = 5*60*60;
    @Value("${jwt.secret}")
    private String secretKey;

    // Getting  claims (details) from the Jwt Token
    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public<T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
       final Claims claims = getAllClaimsFromToken(token);
       return claimsResolver.apply(claims);
    }
//code to retrieve any information/ all information from the token
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    // code to generate jwtToken
    public String generateToken(CustomUserDetails customUserDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("registrationNumber", customUserDetails.getRegistrationNumber());
        claims.put("email", customUserDetails.getEmail());
        claims.put("phoneNumber", customUserDetails.getPhoneNumber());
        return generatedToken(claims, customUserDetails.getUsername());
    }

    private String generatedToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject).setIssuedAt(
                        new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwt_Token_Validity* 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    // code to validate the generated JwtToken
    public boolean validateGeneratedToken(String token, AppUser appUser){
        final String username = getUserNameFromToken(token);
        return (username.equals(appUser.getUsername()) && !isTokenExpired(token));
    }
// code to check if the JwtToken is expired
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());

    }

    private Date getExpirationDateFromToken(String token) {
        return null;
    }


}
