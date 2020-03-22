package com.xxnjdg.heimacms.testfreemarker.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FreemarkerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @RequestMapping("/freemarker/banner")
    public String indexBanner(Map<String, Object> map){
        //使用restTemplate请求轮播图的模型数据
//        ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://localhost:10027/cms/config/5a791725dd573c3574ee333f", Map.class);
        ResponseResult result = restTemplate.getForObject("http://localhost:10027/cms/config/5a791725dd573c3574ee333f", ResponseResult.class);
        //设置模型数据
        map.putAll((Map<? extends String, ?>) result.getData());
        return "index_banner";
    }


    @RequestMapping("/store")
    @ResponseBody
    public Map store(){
        HashMap<String, String> map = new HashMap<>();

        ObjectId id = null;
        try (FileInputStream fileInputStream = new FileInputStream("F:\\index_banner.html")) {
            id = gridFsTemplate.store(fileInputStream, "mytest.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.put("success",id.toString());
        return map;
    }

    @RequestMapping("/read/{id}")
    @ResponseBody
    public Map read(@PathVariable("id") String id){
//        GridFSFindIterable gridFSFiles = gridFsTemplate.find(Query.query(GridFsCriteria.whereFilename().is("index_banner.html")));

        HashMap<String, String> map = new HashMap<>();
//        GridFsResource[] resources = gridFsTemplate.getResources("*.html");
//        for (GridFsResource resource : resources) {
//            String filename = resource.getFilename();
////            String contentType = resource.getContentType();
//            map.put(filename,"data");
//        }

        GridFSFile gridFsFile = gridFsTemplate.findOne(Query.query(GridFsCriteria.where("_id").is(id)));
        GridFsResource resource = gridFsTemplate.getResource(gridFsFile);

        try (InputStream inputStream = resource.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream("F:\\"+resource.getFilename())
        ) {
            final byte[] bytes = getBytes(inputStream);
            fileOutputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("success","read");
        return map;
    }

    private byte[] getBytes(InputStream inputStream) throws  Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int  i = 0;
        while (-1!=(i=inputStream.read(b))){
            bos.write(b,0,i);
        }
        return bos.toByteArray();
    }


}
