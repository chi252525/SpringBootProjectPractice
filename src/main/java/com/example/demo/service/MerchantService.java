package com.example.demo.service;

import com.example.demo.model.mapper.MerchantMapper;
import com.example.demo.model.mapper.entity.Merchant;
import com.example.demo.model.request.merchant.MerchantReq;
import com.example.demo.model.response.MerchantRsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
    @Autowired
    private MerchantMapper merchantMapper;

    public MerchantRsp insert(MerchantReq req) {
        req.validate();
        Merchant merchant = new Merchant();
        merchant.setShopName(req.getShopName());
        merchantMapper.addMerchant(merchant);
        MerchantRsp rsp = new MerchantRsp();
        rsp.setShopName(req.getShopName());
        return rsp;
    }

}
