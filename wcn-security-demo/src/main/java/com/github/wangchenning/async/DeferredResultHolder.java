package com.github.wangchenning.async;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

@Component
public class DeferredResultHolder {
    private Map<String, DeferredResult<String>> map = new HashedMap<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
