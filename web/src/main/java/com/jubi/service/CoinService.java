package com.jubi.service;

import com.google.common.collect.Lists;
import com.jubi.service.vo.CoinVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
public class CoinService {

    public List<CoinVo> getAllCoins() {
        List<CoinVo> rs = Lists.newArrayList();

        CoinVo btc = CoinVo.build("btc", "比特币");
        rs.add(btc);
        CoinVo eth = CoinVo.build("eth", "以太坊");
        rs.add(eth);
        CoinVo etc = CoinVo.build("etc", "以太经典");
        rs.add(etc);
        CoinVo ltc = CoinVo.build("ltc", "莱特币");
        rs.add(ltc);
        CoinVo doge = CoinVo.build("doge", "狗狗币");
        rs.add(doge);
        CoinVo xas = CoinVo.build("xas", "阿希币");
        rs.add(xas);
        CoinVo dnc = CoinVo.build("dnc", "暗网币");
        rs.add(dnc);
        CoinVo ppc = CoinVo.build("ppc", "点点币");
        rs.add(ppc);
        CoinVo rio = CoinVo.build("rio", "里约币");
        rs.add(rio);
        CoinVo xpm = CoinVo.build("xpm", "质数币");
        rs.add(xpm);
        CoinVo xrp = CoinVo.build("xrp", "瑞波币");
        rs.add(xrp);

        return rs;
    }

}
