package com.jubi.shiro;

import com.jubi.dao.entity.User;
import com.jubi.param.UserBean;
import com.jubi.service.AccountService;
import com.jubi.service.EncryptionService;
import com.jubi.service.UserService;
import com.jubi.service.vo.AccountVo;
import com.jubi.service.vo.UserGradeVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.apache.shiro.util.ByteSource;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 前台认证授权域（数据库）,根据登录名查找用户，然后看用户是否锁定等，最后验证密码。
 *
 * @author tjwang
 */
public class ShiroDbRealm extends AuthorizingRealm {

    public Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @Resource
    private AccountService accountService;

    @Resource
    private EncryptionService encryptionService;

    public ShiroDbRealm() {
        // 认证
        super.setAuthenticationCachingEnabled(false);
        // 授权
        super.setAuthorizationCacheName("shiro-authorizationCacheName");
    }

    /**
     * 认证
     *
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        // 调用业务方法
        String username = upToken.getUsername();
        String plaintextPassword = new String(upToken.getPassword());
        if (StringUtils.isBlank(username) || StringUtils.isBlank(plaintextPassword)) {
            throw new UnknownAccountException("username or password is null!");
        }

        User user = userService.findUserByUsername(username, true);
        if (user == null) {
            logger.info("Username {} is not exsited, can not log in!", username);
            throw new UnknownAccountException("username is not exsited!");
        } else { // 用户存在，判断是否被锁定，然后继续验证密码
            if (isFrozen(user.getFrozen())) {
                logger.info("User {} is locked, can not log in!", user.getUsername());
                throw new LockedAccountException(user.getUsername() + "is locked");
            }

            UserBean bean = new UserBean();
            UserGradeVo userGrade = accountService.getUserGrade(user.getId());
            AccountVo account = accountService.getAccount(user.getId());
            BeanUtils.copyProperties(user, bean);
            bean.setNickname(account.getNickname());
            BeanUtils.copyProperties(userGrade, bean);
            /* 后继使用HashedCredentialsMatcher进行密码验证 */
            SimpleAuthenticationInfo authinfo = new SimpleAuthenticationInfo(bean, user.getPassword().trim(), getName());
            authinfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
            return authinfo;
        }
    }

    /**
     * 是否被冻结
     *
     * @param frozen
     * @return
     */
    private boolean isFrozen(Integer frozen) {
        if (frozen == null || frozen == 0) {
            return false;
        }

        if (frozen == -1) {
            return true;
        }

        DateTime dateTime = new DateTime(frozen);
        return dateTime.isBeforeNow();
    }

    /**
     * 验证密码
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CredentialsMatcher cm = getCredentialsMatcher();
        if (cm != null) {
            if (cm.doCredentialsMatch(token, info)) {// 验证密码成功
                UserBean bean = (UserBean) info.getPrincipals().getPrimaryPrincipal();
                String ip = ((DelegatingSubject) SecurityUtils.getSubject()).getHost();
                userService.updateLoginInfo(bean.getId(), ip);
                return;
            }
            String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
            throw new IncorrectCredentialsException(msg);
        }

        throw new AuthenticationException(
                "A CredentialsMatcher must be configured in order to verify credentials during authentication.  If you do not wish for credentials to be examined, you can configure an "
                        + AllowAllCredentialsMatcher.class.getName() + " instance.");
    }

    /**
     * 设定Password校验的Hash算法与迭代次数, 必须与加密时候保持一致
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(encryptionService.getAlgorithmName());
        matcher.setHashIterations(encryptionService.getHashIterations());
        setCredentialsMatcher(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
