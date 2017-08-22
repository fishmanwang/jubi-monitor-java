/**
 * BBD Service Inc
 * All Rights Reserved @2016
 */
package com.jubi.controller;

import com.google.common.collect.Lists;
import com.jubi.RestResult;
import com.jubi.param.UserBean;
import com.jubi.service.AccountAdminService;
import com.jubi.service.vo.AccountVo;
import com.jubi.service.vo.FavoriteCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tjwang
 * @version $Id: AccountController.java, v 0.1 2017/8/22 0022 13:44 tjwang Exp $
 */
@RestController
@RequestMapping("/account")
public class AccountController extends AbstractController {

    @Autowired
    private AccountAdminService accountAdminService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public RestResult saveAccount(@RequestBody AccountVo vo) {
        accountAdminService.saveAccount(getUser().getId(), vo);
        return RestResult.ok();
    }

    @RequestMapping(value = "/fcoin/set", method = RequestMethod.POST)
    public RestResult setFavoriteCoins(@RequestBody List<FavoriteCoin> fcs) {
        if (fcs == null) {
            fcs = Lists.newArrayList();
        }
        UserBean bean = getUser();
        Integer userId = bean.getId();
        accountAdminService.setFavoriteCoins(userId, fcs);
        return RestResult.ok();
    }

    @RequestMapping(value = "/fcoin/get", method = RequestMethod.GET)
    public RestResult getFavoriateCoins() {
        UserBean user = getUser();
        Integer userId = user.getId();
        List<FavoriteCoin> fcs = accountAdminService.getFavoriteCoin(userId);
        return RestResult.ok(fcs);
    }

}
