package com.nyist.service.impl;

import com.alipay.api.AlipayApiException;
import com.nyist.config.AlipayUtil;
import com.nyist.entity.AlipayBean;
import com.nyist.service.PayService;
import org.springframework.stereotype.Service;

/* 支付服务 */
@Service(value = "payService")
public class PayServiceImpl implements PayService {


    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }

}
