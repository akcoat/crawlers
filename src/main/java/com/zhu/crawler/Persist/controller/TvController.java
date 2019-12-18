package com.zhu.crawler.Persist.controller;


import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zjw
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/Persist/tv")
public class TvController {


    @Autowired
    private RedissonClient redissonClient;


    public String test(){

        return "hello-world";
    }

}

