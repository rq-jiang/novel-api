package com.example.demo.start;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Type;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Value("${fileDirectory}")
    private String fileDirectory;

    private String cover_url = "http://localhost:8888/images/book_cover/";

    @Resource
    private BookService bookService;


    @Resource
    private AuthorService authorService;

    @Resource
    private TypeService typeService;

    @RequestMapping("/list")
    public List<Book> listbook(){
        List<Book> list = bookService.findAll();
        return bookService.findAll();
    }
    @RequestMapping("/findById")
    public Book findBookById(Integer id){
        return bookService.findById(id);
    }

    @RequestMapping("/findByAuthorId")
    public List<Book> findByAuthorId(Integer id){
        return bookService.findByAuthor(id);
    }

    @RequestMapping("/findByName")
    public List<Book> findByName(String name) {
        if(name == ""){
            return null;
        }
        return bookService.findByName(name);
    }

    @RequestMapping("/findByTypeId")
    public List<Book> findByTypeId(Integer id) {
        return bookService.findByType(id);
    }

    @RequestMapping("/insertBook")
    public boolean insertBook(String book_name, String introduction, Integer word_count, String author_name , String type_name){
        Author author = authorService.findByName(author_name);
        if(author == null) {
            System.out.println("没有该作者");
            return false;
        }
        Type type = typeService.findByName(type_name);
        if(type == null) {
            System.out.println("没有该类型");
            return false;
        }
       if(isBookSame(book_name)){
           Book book = new Book();
           book.setName(book_name);
           book.setAuthor_id(author.getId());
           book.setType_id(type.getId());
           book.setIntroduction(introduction);
           book.setWord_count(word_count);
           book.setUpdated_at(new Date());
           book.setCreated_at(new Date());
           book.setCover_url(cover_url);
           cover_url = "http://localhost:8888/images/book_cover/";
           System.out.println(book);
           return bookService.insertBook(book);
       }
        return false;
    }

    public boolean isBookSame(String bookName){
        List<Book> bookList = bookService.findBybookName(bookName);
        for(int i = 0;i < bookList.size();i++) {
            System.out.println(bookList.get(i));
        }
        if(bookList.size() == 0) {
            return true;
        }
        System.out.println("书名相同");
        return false;
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile file) throws Exception{
        try{
            //获取文件名
            String fileName=file.getOriginalFilename();
            //拼接完整的文件保存路径
            String fileFullPath=fileDirectory+"book_cover/"+fileName;
            //删除现有的同名文件（如果有）。如果直接调用delete方法，会抛异常
            Files.deleteIfExists(Paths.get(fileFullPath));
            //保存文件
            file.transferTo(new File(fileFullPath));
            cover_url += fileName;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "文件上传成功.";
    }

    @RequestMapping("/updateBook")
    public boolean updateBook(Integer id,String book_name, String introduction, Integer word_count, String author_name , String type_name){
        Book book = bookService.findById(id);
        Author author = authorService.findByName(author_name);
        Type type = typeService.findByName(type_name);
        if(book != null){
            book.setName(book_name);
            book.setIntroduction(introduction);
            book.setWord_count(word_count);
            book.setAuthor_id(author.getId());
            book.setType_id(type.getId());
            return bookService.updateBook(book);
        }
        return false;
    }

    @RequestMapping("/deleteBook")
    public boolean deleteBook(Integer id) {
        return bookService.deleteBook(id);
    }
}
