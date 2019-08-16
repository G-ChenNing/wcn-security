package com.github.wangchenning.security.core.social.weixin.api;

import com.github.wangchenning.security.core.social.qq.api.QQUserInfo;

public interface Weixin {
    WeixinUserInfo getUserInfo(String openId);
}
