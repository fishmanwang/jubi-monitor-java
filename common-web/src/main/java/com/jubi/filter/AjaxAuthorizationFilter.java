package com.jubi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jubi.RestResult;
import com.jubi.exception.ErrorCode;
import com.jubi.exception.UserErrorCode;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 针对ajax请求的认证过滤(由于ajax在服务端无法做页面跳转，所以针对ajax的受限请求直接返回登录的json提示结果)
 *
 * @author tjwang
 * @version $Id: AjaxAuthorizationFilter.java, v 0.1 2016年12月10日 下午2:35:42 jintao Exp $
 */
public class AjaxAuthorizationFilter extends AccessControlFilter {

    private boolean isAjaxRequst(HttpServletRequest httpServletRequest) {
        String requestType = httpServletRequest.getHeader("X-Requested-With");
        boolean flag = "XMLHttpRequest".equalsIgnoreCase(requestType);
        return flag;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        }

        Subject subject = getSubject(request, response);
        // If principal is not null, then the user is known and should be allowed access.
        return subject.getPrincipal() != null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Subject subject = getSubject(request, response);
        subject.logout();

        if (isAjaxRequst(httpRequest)) {//针对ajax请求直接返回json
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            ErrorCode errorCode = UserErrorCode.USER_NOT_LOGIN;
            RestResult result = RestResult.fail(errorCode.getStatus(), errorCode.getMessage());
            out.write(new ObjectMapper().writeValueAsString(result));
            out.flush();
        } else {
            saveRequestAndRedirectToLogin(request, response);//普通请求跳转
        }
        return false;
    }

}
