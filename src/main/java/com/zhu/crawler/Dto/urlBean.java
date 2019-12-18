package com.zhu.crawler.Dto;

import cn.wanghaomiao.seimi.annotation.Xpath;
import lombok.Data;

@Data
public class urlBean {


    @Xpath("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()")
    private String title;

    @Xpath("//a[@class='titlelnk']/@href")
    private String url;

    private String commentNum;

    private String playNum;

}
