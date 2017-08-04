package com.jubi.controller;

import com.jubi.RestResult;
import com.jubi.dao.entity.User;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.BizErrorCode;
import com.jubi.exception.UserErrorCode;
import com.jubi.param.UserBean;
import com.jubi.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 登录相关的功能Controller,包含登录，注销功能。
 *
 * @author jintao
 */
@Controller
public class LoginController extends AbstractController {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RestResult login(String username, String password, HttpServletRequest request) {
        if (StringUtils.isBlank(username)) {
            throw new ApplicationException(BizErrorCode.USER_NAME_EMPTY);
        }
        if (StringUtils.isBlank(password)) {
            throw new ApplicationException(BizErrorCode.USER_PASSWORD_NULL);
        }
        HttpSession session = request.getSession();
        if (!RequestUtil.checkVerificationCode(null, session, request)) {
            //return new RestResult<String>(CommonErrorCode.VALIDATION_ERROR.getCode(), "验证码不正确");
            throw new ApplicationException(BizErrorCode.VALIDATION_ERROR, "验证码不正确");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //token.setRememberMe(StringUtils.isNotBlank(rememberMe));
        try {
            /*
             * shiroDbRealm的认证方法com.bbd.brp.front.shiro.ShiroDbRealm.
             * doGetAuthenticationInfo (AuthenticationToken)
             */
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException uae) {
            throw new ApplicationException(UserErrorCode.USER_NO_EXIST, username);
        } catch (LockedAccountException lae) {
            logger.info("Account {} is locked!", username);
            //throw new AuthenticationException("登录失败：账户被锁定，请联系系统管理员解锁。");
            throw new ApplicationException(UserErrorCode.USER_ACCOUNT_LOCKED, username);
        } catch (AuthenticationException ae) {
            logger.info("User {} login failed: {}", username, ae.getMessage());
            //throw new AuthenticationException("登录失败：用户名/密码输入有误，请确认后重试。");
            throw new ApplicationException(UserErrorCode.USER_PASSWORD_INCONSISTENT);
        }
        UserBean bean = (UserBean) SecurityUtils.getSubject().getPrincipal();
        logger.info("用户{}在{}登录了系统。", bean.getUsername(), new Date());
        return RestResult.ok(bean);
    }

    @RequestMapping(value = "/checklogin")
    @ResponseBody
    public RestResult checkLogin(Model model) {
        Boolean loggedOn = SecurityUtils.getSubject().isAuthenticated();
        return RestResult.ok(loggedOn);
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public RestResult logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            User user = (User) subject.getPrincipal();
            if (user != null) {
                subject.logout();
                logger.info("用户{}在{}退出了系统。", user.getUsername(), new Date());
            }
        }
        return RestResult.ok(true);
    }

}
