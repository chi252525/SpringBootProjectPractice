package com.example.demo.controller;

import com.example.demo.model.request.merchant.MerchantReq;
import com.example.demo.model.response.MerchantRsp;
import com.example.demo.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping("/create")
    public MerchantRsp createMerchant(@RequestBody MerchantReq req) {
        return merchantService.insert(req);
    }
}
