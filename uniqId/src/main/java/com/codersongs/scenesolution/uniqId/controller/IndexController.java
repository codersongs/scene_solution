package com.codersongs.scenesolution.uniqId.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public JSONObject greeting() {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "success");
        return res;
    }
}
