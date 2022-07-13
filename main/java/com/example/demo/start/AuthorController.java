package com.example.demo.start;


import com.example.demo.entity.Author;
import com.example.demo.entity.Result;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/author")
public class AuthorController {


    @Resource
    private AuthorService authorService;

//    private String author_cover = "/usr/tomcat/apache-tomcat-9.0.62/webapps/ROOT/images/author_cover/";


    private String author_cover = "/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/author_cover";



    @RequestMapping("/findById")
    public Author findById(Integer id){
        return authorService.findById(id);
    }

    @RequestMapping("/insertAuthor")
    public Result insertAuthor(String author_name, String introduction){
        Author author1 = authorService.findByName(author_name);
        if(author1 != null){
            return new Result(400,"已有该作者信息","");
        }
        Author author = new Author();
        System.out.println(author_name+"jj"+introduction);
        author.setName(author_name);
        author.setIntroduction(introduction);
        author.setCover_url(author_cover);
        author.setCreated_at(new Date());
        author.setUpdated_at(new Date());
        System.out.println(author);
        authorService.insertAuthor(author);
        author_cover = "/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/author_cover/";
        return new Result(400,"新增作者失败","");
    }


    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile file) throws Exception {
        Set set = new HashSet<>();

        List list = new ArrayList<>();
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //拼接完整的文件保存路径
            String fileFullPath = author_cover+fileName;
            //删除现有的同名文件（如果有）。如果直接调用delete方法，会抛异常
            Files.deleteIfExists(Paths.get(fileFullPath));
            //保存文件
            file.transferTo(new File(fileFullPath));
            author_cover = "/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/author_cover/"+fileName;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "文件上传成功.";
    }

}
