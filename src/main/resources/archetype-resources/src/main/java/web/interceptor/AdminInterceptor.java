package ${groupId}.web.interceptor;

import ${groupId}.mvc.bean.interact.response.Result;
import ${groupId}.utils.CommUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {


    private final HttpSession session;

    @Autowired
    public AdminInterceptor(HttpSession session) {
        this.session = session;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (session.getAttribute("admin") == null) {
            CommUtils.responseJson(response, Result.error("请先登录"), 401);
            return false;
        } else {
            return true;
        }
    }
}
