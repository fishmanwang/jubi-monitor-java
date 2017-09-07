/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service;

import com.google.common.base.Preconditions;
import com.jubi.dao.UserDao;
import com.jubi.dao.UserExtDao;
import com.jubi.dao.entity.User;
import com.jubi.dao.entity.UserExample;
import com.jubi.dao.param.UserQueryParam;
import com.jubi.dao.vo.UserAdminVo;
import com.jubi.event.UserCreateEvent;
import com.jubi.event.UserDeleteEvent;
import com.jubi.exception.ApplicationException;
import com.jubi.exception.CommonErrorCode;
import com.jubi.exception.UserErrorCode;
import com.jubi.service.vo.UserRegisterParam;
import com.jubi.spring.SpringContextHolder;
import com.mybatis.domain.PageBounds;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tjwang
 * @version $Id: UserService.java, v 0.1 2017/8/4 0004 16:01 tjwang Exp $
 */
@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserExtDao userExtDao;

    @Autowired
    private EncryptionService encryptionService;

    public void modifyPassword(Integer userId, String oldPwd, String newPwd, String newPwdConfirm) {

        checkConfirmPassword(newPwd, newPwdConfirm);
        User user = userDao.selectByPrimaryKey(userId);
        if (StringUtils.isNotBlank(oldPwd)) {
            String oldPwdMD5 = encryptionService.encryptPassword(oldPwd, user.getSalt());
            if (!user.getPassword().equals(oldPwdMD5)) {
                throw new ApplicationException(UserErrorCode.USER_PASSWORD_ERROR, "旧密码输入错误，请重新输入");
            }
        }

        String newPwdMD5 = encryptionService.encryptPassword(newPwd, user.getSalt());
        if (user.getPassword().equals(newPwdMD5)) {
            throw new ApplicationException(UserErrorCode.USER_PASSWORD_SAME_AS_OLD, "新密码不能与旧密码相同，请重新输入！");
        } else if (user.getUsername().equals(newPwd)) {
            throw new ApplicationException(UserErrorCode.USER_PASSWORD_SAME_AS_USERNAME, "新密码不能与账号相同，请重新输入！");
        } else {
            modifyPassword(userId, newPwdMD5);
            //userDao.modifyPassword(userId, newPwdMD5);
        }
    }

    public void adminResetPassword(Integer userId, String salt, String newPwd, String newPwdConfirm, String administratorId) {
        // 首先检查管理员(administratorId)的权限

        checkConfirmPassword(newPwd, newPwdConfirm);
        String newPwdMD5 = encryptionService.encryptPassword(newPwd, salt);
        //userDao.modifyPassword(userId, newPwdMD5);// 记录初始化密码时间
        modifyPassword(userId, newPwdMD5);
    }

    private void modifyPassword(Integer userId, String newPwd) {
        User u = new User();
        u.setId(userId);
        u.setPassword(newPwd);
        userDao.updateByPrimaryKeySelective(u);
    }

    public void checkConfirmPassword(String newPwd, String newPwdConfirm) {
        if (StringUtils.isBlank(newPwd) || StringUtils.isBlank(newPwdConfirm)) {
            throw new ApplicationException(UserErrorCode.USER_PASSWORD_NULL, "新密码和确认密码不能为空！");
        }

        if (!newPwd.equals(newPwdConfirm)) {
            throw new ApplicationException(UserErrorCode.USER_PASSWORD_INCONSISTENT, "新密码和确认新密码不一致，请重新输入！");
        }
    }

    /**
     * @param username
     * @param enableMultiPrincipals 是否支持手机号、邮箱
     * @return
     */
    public User findUserByUsername(String username, boolean enableMultiPrincipals) {
        List<User> list = null;
        UserExample example = new UserExample();
        try {
            if (enableMultiPrincipals) {
                example.createCriteria().andUsernameEqualTo(username);
            } else {
                example.createCriteria().andUsernameEqualTo(username);
            }
            list = userDao.selectByExample(example);
        } catch (Exception e) {
            logger.error("使用登录名查询用户失败, 用户名为{}.", username, e);
        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    private User findUserByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> list = userDao.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    public void deleteUser(Integer userId) {
        User user = getUser(userId);
        if (user == null) {
            logger.warn("指定ID {} 不存在", userId);
            throw new ApplicationException(CommonErrorCode.PARAM_ERROR, "指定用户不存在");
        }

        userDao.deleteByPrimaryKey(userId);

        UserDeleteEvent event = new UserDeleteEvent(userId);
        SpringContextHolder.getApplicationContext().publishEvent(event);
    }

    private User getUser(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public Integer registerUser(UserRegisterParam param) {
        String username = param.getUsername();

        User u = findUserByUsername(username);
        if (u != null) {
            throw new ApplicationException(UserErrorCode.USER_USERNAME_EXIST);
        }

        String password = param.getPassword();
        String salt = com.jubi.util.StringUtils.getRandomString(6);
        String encryptedPwd = encryptionService.encryptPassword(password, salt);

        Date now = new Date();

        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptedPwd);
        user.setSalt(salt);
        user.setCreateTime(now);

        Integer userId = createUser(user);

        SpringContextHolder.getApplicationContext().publishEvent(new UserCreateEvent(userId));

        return userId;
    }

    private Integer createUser(User user) {
        userDao.insertSelective(user);
        return user.getId();
    }

    public void updateLoginInfo(Integer userId, String ip) {
        User user = new User();
        user.setId(userId);
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(ip);
        userDao.updateByPrimaryKeySelective(user);
    }

    /**
     * 管理端查询用户
     *
     * @param param
     * @param pb
     * @return
     */
    public List<UserAdminVo> queryUserAdminUser(UserQueryParam param, PageBounds pb) {
        Preconditions.checkNotNull(param);
        Preconditions.checkNotNull(pb);

        return userExtDao.queryUser(param.getName(), pb);
    }

}
