/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.service.vo;

/**
 * @author tjwang
 * @version $Id: UserGradeVo.java, v 0.1 2017/9/8 0008 10:44 tjwang Exp $
 */
public class UserGradeVo {

    /**
     * 等级
     */
    private Integer grade;

    /**
     * 一天邮件最大发送数量
     */
    private Integer emailCount;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(Integer emailCount) {
        this.emailCount = emailCount;
    }
}
