package com.jubi.controller;

import com.jubi.context.SessionContext;
import com.jubi.param.UserBean;
import com.jubi.util.JBStringUtils;
import com.mybatis.domain.PageBounds;
import com.mybatis.domain.SortBy;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 包含Controller公用的方法，其他的controller通过继承来使用这些公用的方法.
 *
 * @author tjwang
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static PageBounds getPageBounds() {
        HttpServletRequest request = SessionContext.getRequest();

        int page = getParameterForInt(request, "page", 1);
        int limit = getParameterForInt(request, "limit", 10);

        String sortBy = request.getParameter("sortBy");
        if (StringUtils.isBlank(sortBy)) {
            sortBy = "create_time";
        }
        String sort = request.getParameter("sort");
        return new PageBounds(page, limit, SortBy.create(sortBy, sort));
    }

    /**
     * 如果参数不存在或者不合法，返回默认值
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static int getParameterForInt(HttpServletRequest request, String name, int defaultValue) {
        String stringValue = request.getParameter(name);
        if (StringUtils.isBlank(stringValue)) {
            return defaultValue;
        }
        return JBStringUtils.parseStringToInt(stringValue, defaultValue);
    }

    public UserBean getUser() {
        return (UserBean) SecurityUtils.getSubject().getPrincipal();
    }

}
