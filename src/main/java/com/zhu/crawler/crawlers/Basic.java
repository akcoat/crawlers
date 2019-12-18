package com.zhu.crawler.crawlers;


import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Crawler(name = "basic",useCookie = true)
@Component
public class Basic extends BaseSeimiCrawler {
    @Override
    public String[] startUrls() {
        //两个是测试去重的
    //    return new String[]{"http://www.cnblogs.com/","http://www.cnblogs.com/"};
        return  null;
    }

    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {

            Request build = Request.build("http://192.168.168.211:9081/admin/toIndex","testLogin")
                    .useSeimiAgent()
                    .setSeimiAgentUseCookie(true)
                    .setSeimiAgentRenderTime(5000);
            push(build);
            // push(Request.build("http://192.168.168.211:9081/admin/toIndex","testLogin"));
            // List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
           // List<Object> urls = doc.sel("//a/@href");

           // urlBean bean = response.render(urlBean.class);
          //  logger.info("{}", urls.size());
//            for (Object s:urls){
//                push(Request.build(s.toString(),Basic::getTitle));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testLogin(Response response) {

        JXDocument doc = response.document();
        try {
            // List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
             List<Object> urls = doc.sel("//ul[@class='menu-item-popup']/li/a/@href");

            // urlBean bean = response.render(urlBean.class);
              logger.info("{}", urls.size());

            for (Object s:urls){
                push(Request.build(s.toString(),Basic::getTitle));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public List<Request> startRequests() {
        List<Request> requests = new LinkedList<>();
        Request start = Request.build("http://192.168.168.211:9081/admin/login","start")
                .useSeimiAgent()
                .setSeimiAgentUseCookie(true)
                .setSeimiAgentRenderTime(5000);
        Map<String,String> params = new HashMap<>();
        params.put("userName","admin");
        params.put("password","5E9EEC54E141EF5FA8E328A238692E23");
//        params.put("save_login","1");
//        params.put("verifyCode","");
        start.setHttpMethod(HttpMethod.POST);
        start.setParams(params);
        requests.add(start);
        return requests;
    }


    public void getTitle(Response response){
        JXDocument doc = response.document();
        try {
            logger.info("url:{} {}", response.getUrl(), doc.sel(""));
            //do something
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}