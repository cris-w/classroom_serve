package top.criswjh.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Jwt 工具类
 *
 * @author wjh
 * @date 2021/12/5 12:54 上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "classroom.jwt")
public class JwtUtils {

    /**
     * 密钥
     */
    public String secret;
    /**
     * 过期时间
     */
    public Integer expire;
    /**
     * 请求头：Authorization
     */
    private String head;

    /**
     * 生成 JWT
     * @param username 用户名
     * @return jwt
     */
    public String generateToken(String username) {
        DateTime now = DateTime.now();
        DateTime expireTime = now.offsetNew(DateField.HOUR, 24 * expire);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(now)
                // 7 天过期
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析 jwt
     * @param jwt jwt
     * @return body
     */
    public Claims getClaimByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            // 如果 jwt 不合法则返回null
            return null;
        }
    }

    /**
     * 判断jwt 是否过期
     * @param claims claims
     * @return true 没过期  /  false 过期了
     */
    public boolean isTokenExpire(Claims claims) {
        return claims.getExpiration().before(DateTime.now());
    }
}
