package com.jubi.service.vo;

/**
 * 币种基本信息
 * Created by Administrator on 2017/7/30.
 */
public class CoinVo {

    private String code;

    private String desc;

    public static CoinVo build(String code, String desc) {
        CoinVo vo = new CoinVo();
        vo.setCode(code);
        vo.setDesc(desc);
        return vo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
