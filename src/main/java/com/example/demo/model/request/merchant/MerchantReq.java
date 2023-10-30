package com.example.demo.model.request.merchant;

import com.example.demo.model.request.BaseReq;
import lombok.Data;

@Data
public class MerchantReq extends BaseReq {
    private String shopName;

    public void validate() {
        require("shopName", shopName);
    }
}
