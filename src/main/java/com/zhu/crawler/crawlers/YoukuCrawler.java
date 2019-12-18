package com.zhu.crawler.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.def.DefaultRedisQueue;
import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.http.SeimiHttpType;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.zhu.crawler.Persist.entity.Tv;
import com.zhu.crawler.Persist.service.TvService;
import com.zhu.crawler.Utls.StringUtls;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Crawler(name = "Bilibili", httpType= SeimiHttpType.OK_HTTP3,httpTimeOut = 60000,queue = DefaultRedisQueue.class)
@Component
public class YoukuCrawler extends BaseSeimiCrawler {

    @Autowired
    private TvService tvService;

    @Autowired
    private RedissonClient redissonClient;


    @Override
    public String[] startUrls() {
      //  return  new String[]{"https://www.bilibili.com/v/music/cover/?spm_id_from=333.5.b_6d757369635f636f766572.23#/all/click/0/1/2019-12-02"};
        return  null ;
    }



    @Override
    public String getUserAgent(){
        RList<Object> myu = redissonClient.getList("myUA");
        List<Object> objects = myu.readAll();
        return objects.get(RandomUtils.nextInt(0,objects.size())).toString();
    }


    @Override
    public void start(Response response) {

        JXDocument doc = response.document();
        List<Tv> tvList = new LinkedList<>();
        try {

            List<Object> sel1 = doc.sel("count(//*[@id=\"videolist_box\"]/div[2]/ul/li)");
            int  number =  ((Double)Double.parseDouble(sel1.get(0).toString())).intValue();
            for(int i=1;i<=number;i++){
                // 标题
                List<Object> titles = doc.sel("//*[@id=\"videolist_box\"]/div[2]/ul/li["+i+"]/div/div[2]/a/text()");

                // play
                List<Object> plays = doc.sel("//*[@id=\"videolist_box\"]/div[2]/ul/li[" + i + "]/div/div[2]/div[2]/span[1]/span/text()");
                String play = plays.get(0).toString();
                double playnum = 0;
                if(play.indexOf("万")>0){
                    int index = play.indexOf("万");
                    String substring = play.substring(0,index);
                    playnum = Double.parseDouble(substring)* 10000;
                }
                else
                    playnum=Integer.parseInt(play);
                // danmu
                List<Object> danmus = doc.sel("//*[@id=\"videolist_box\"]/div[2]/ul/li[" + i + "]/div/div[2]/div[2]/span[2]/span/text()");
                // zuozhe
                List<Object> authors =doc.sel("//*[@id=\"videolist_box\"]/div[2]/ul/li["+i+"]/div/div[2]/div[3]/a/text()");
                Tv tv = new Tv().setTitle((String) titles.get(0))
                        .setPlay(playnum)
                        .setDanmu(StringUtls.sub(danmus.get(0).toString()))
                        .setAuthor((String) authors.get(0));
                tvList.add(tv);
            }
            tvService.saveBatch(tvList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testJs(Response response) {
        JXDocument doc = response.document();
        try {
            List<Object> sel = doc.sel("//*[@id=\"videolist_box\"]/div[2]/ul/li/div[@class=\"l-item\"]/div/div/a/p[@class=\"t\"]/text() | " +
                    "//*[@id=\"videolist_box\"]/div[2]/ul/li/div[@class=\"l-item\"]/div/div/a/p[@class=\"num\"]/span[@class='play']/text() |" +
                    "//*[@id=\"videolist_box\"]/div[2]/ul/li/div[@class=\"l-item\"]/div/div/a/p[@class=\"num\"]/span[@class='danmu']/text()");

            for(Object res : sel){
                System.out.println(res.toString());
            }
//            String url = response.getUrl();
//            System.out.println(url);
           // push(Request.build(response.getUrl(),"next").useSeimiAgent().setSeimiAgentScript("$(\".pagination-btn num-btn\").click()"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Request> startRequests() {
        List<Request> requests = new LinkedList<>();
        for(int i=1;i<2;i++){
            Request start = Request.build("https://www.bilibili.com/v/music/cover/?spm_id_from=333.5.b_6d757369635f636f766572.23#/all/click/0/"+i+"/2019-12-01,2019-12-31",
                    "start")
                    .useSeimiAgent()
                    .setSeimiAgentRenderTime(5000);
            start.setHttpMethod(HttpMethod.GET);
            // start.setParams(params);
            requests.add(start);
        }

        return requests;
    }


    public void  getComment(Response response) {

        JXDocument doc = response.document();
        try {
            Object span =  doc.sel("//*[@id=\"videolist_box\"]/div[2]/ul/li[1]/div/div[2]/div[2]/span[1]/span");
            System.out.println(span.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
