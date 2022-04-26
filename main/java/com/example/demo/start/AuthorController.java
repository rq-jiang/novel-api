package com.example.demo.start;


import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/author")
public class AuthorController {

    @Value("${fileDirectory}")
    private String fileDirectory;

    @Autowired
    private AuthorService authorService;

    private String cover_url = "http://localhost:8888/images/author_url/";


    @RequestMapping("/findById")
    public Author findById(Integer id){
        return authorService.findById(id);
    }

    @RequestMapping("/insertAuthor")
    public boolean insertAuthor(String author_name,String introduction){
//        Author author = authorService.findByName(author_name);
        Author author = new Author();
        System.out.println(author_name+"jj"+introduction);
        author.setName(author_name);
        author.setIntroduction(introduction);
        author.setCover_url(cover_url);
        cover_url = "http://localhost:8888/images/author_url/";
        author.setCreated_at(new Date());
        author.setUpdated_at(new Date());
        System.out.println(author);
        return authorService.insertAuthor(author);
    }


    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile file) throws Exception {
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //拼接完整的文件保存路径
            String fileFullPath = fileDirectory + "author_cover/" + fileName;
            //删除现有的同名文件（如果有）。如果直接调用delete方法，会抛异常
            Files.deleteIfExists(Paths.get(fileFullPath));
            //保存文件
            file.transferTo(new File(fileFullPath));
            cover_url += fileName;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "文件上传成功.";
    }

}
