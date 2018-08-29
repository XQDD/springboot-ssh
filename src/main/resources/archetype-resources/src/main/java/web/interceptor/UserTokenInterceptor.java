package ${groupId}.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@Component
public class UserTokenInterceptor extends HandlerInterceptorAdapter {

    private final RedisTemplate redisTemplate;

    private final HttpSession session;

    @Autowired
    public UserTokenInterceptor(RedisTemplate redisTemplate, HttpSession session) {
        this.redisTemplate = redisTemplate;
        this.session = session;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            response.setStatus(401);
            ${groupId}.utils.CommUtils.responseJson(response, new ${groupId}.mvc.bean.interact.response.ErrorResult(40100, "Token有误"));
            return false;
        }
        ValueOperations opsForValue = redisTemplate.opsForValue();
        String tokenValue[] = token.split("_");
        if (tokenValue.length != 2) {
            response.setStatus(401);
            ${groupId}.utils.CommUtils.responseJson(response, new ${groupId}.mvc.bean.interact.response.ErrorResult(40100, "Token有误"));
            return false;
        }
        Object redisTokenValue[] = (Object[]) opsForValue.get("user_token_" + tokenValue[0]);
        if (redisTokenValue == null) {
            response.setStatus(401);
            ${groupId}.utils.CommUtils.responseJson(response, new ${groupId}.mvc.bean.interact.response.ErrorResult(40101, "身份验证已失效，请重新登录"));
            return false;
        }
        if (!token.equals(redisTokenValue[0])) {
            response.setStatus(401);
            ${groupId}.utils.CommUtils.responseJson(response, new ${groupId}.mvc.bean.interact.response.ErrorResult(40102, "您已经在另一台设备上登录"));
            return false;
        }
        session.setAttribute("user", redisTokenValue[1]);
        redisTemplate.expire("user_token_" + tokenValue[0], 30, TimeUnit.DAYS);
        return true;
    }
}
