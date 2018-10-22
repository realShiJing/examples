package com.nchu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yangshijing
 * @desc
 * @created 2018/5/24 0024
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    /**
     * 上传图片
     * @param session
     * @param myfile
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/upload")
    public String uploadFile(HttpSession session, MultipartFile myfile) throws IllegalStateException, IOException{
        //原始名称
        String oldFileName = myfile.getOriginalFilename(); //获取上传文件的原名
        //存储图片的物理路径
        String file_path = session.getServletContext().getRealPath("upload");
        //上传图片
        if(myfile!=null && oldFileName!=null && oldFileName.length()>0){
            //新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            //新图片
            File newFile = new File(file_path+"/"+newFileName);
            //将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            //将新图片名称返回到前端
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("success", "成功啦");
            return "You successfully uploaded file=" +  myfile.getOriginalFilename();
           // map.put("imgUrl",newFileName);
            //return  map;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("error","图片不合法");
            //return map;
            return "You failed to upload " +  myfile.getOriginalFilename() + " => ";
        }
    }
}
