package cn.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.util.Const;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UploadController {
    @RequestMapping("/upfile")
    @ResponseBody
    public JSONObject upfile(MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws IOException { 
        //传到服务器
        //得到真实路径
        String path=request.getSession().getServletContext().getRealPath("/images");
        //目标文件
        String fileName=path+"/"+file.getOriginalFilename();
        file.transferTo(new File(fileName));
        JSONObject result=new JSONObject();
		result.put("errno", 0);
		JSONArray data=new JSONArray();
		data.add(Const.ROOT+"images/"+file.getOriginalFilename());
		result.put("data", data);
		result.put("filename",file.getOriginalFilename());
        return result;
    }

    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("filename") String filename,
                                           Model model)throws Exception {
        //下载文件路径
        String path = request.getSession().getServletContext().getRealPath("/images/");    
        //找到服务器里面的中文文件,Tomcat8就不需要
        //String downloadFielName = new String(filename.getBytes("iso-8859-1"),"UTF-8");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以attachment（下载方式）打开图片,所有浏览器都会使用本地编码，即中文操作系统使用GBK
        //下载框中显示的文件名，解决中文名称乱码问题 
        headers.setContentDispositionFormData("attachment", new String(filename.getBytes("GBK"),"iso-8859-1"));
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

}
