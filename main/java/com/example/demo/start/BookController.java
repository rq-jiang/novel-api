package com.example.demo.start;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.web.bind.annotation.*;
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


    @Resource
    private BookService bookService;


    @Resource
    private AuthorService authorService;

    @Resource
    private TypeService typeService;

    @Resource
    private UserService userService;

    @Resource
    private UserCollectBookService userCollectBookService;

//    private String book_cover = "/usr/tomcat/apache-tomcat-9.0.62/webapps/ROOT/images/book_cover/";

    private String book_cover = "/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/book_cover/";

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
        System.out.println(bookService.findByName(name));
        return bookService.findByName(name);
    }

    @RequestMapping("/findByTypeId")
    public List<Book> findByTypeId(Integer id) {
        return bookService.findByType(id);
    }

    @RequestMapping("/insertBook")
    public Result insertBook(String book_name, String introduction, Integer word_count, String author_name , String type_name){
        Author author = authorService.findByName(author_name);
        if(author == null) {
            System.out.println("???????????????");
            return new Result(400,"?????????????????????????????????????????????","");
        }
        Type type = typeService.findByName(type_name);
        if(type == null) {
            System.out.println("???????????????");
            return new Result(400,"??????????????????????????????????????????","");
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
           book.setCover_url(book_cover);
           book_cover = "/Users/rqjiang/Projects/Java/novel-api/src/main/resources/static/images/book_cover/";
           bookService.insertBook(book);
           return new Result(200,"????????????","");
       }
        return new Result(400,"????????????","");
    }

    public boolean isBookSame(String bookName){
        List<Book> bookList = bookService.findBybookName(bookName);
        for(int i = 0;i < bookList.size();i++) {
            System.out.println(bookList.get(i));
        }
        if(bookList.size() == 0) {
            return true;
        }
        System.out.println("????????????");
        return false;
    }

    @RequestMapping("/fileUpload")
    public String fileUpload(MultipartFile file) throws Exception{
        try{
            //???????????????
            String fileName=file.getOriginalFilename();
            //?????????????????????????????????
            String fileFullPath=book_cover+fileName;
            //???????????????????????????????????????????????????????????????delete?????????????????????
            Files.deleteIfExists(Paths.get(fileFullPath));
            //????????????
            file.transferTo(new File(fileFullPath));
            book_cover = "http://localhost:8080/images/book_cover/"+fileName;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return "??????????????????.";
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
    public Result deleteBook(Integer id) {
        if(id != null){
            bookService.deleteBook(id);
            return new Result(200,"????????????","");
        }
        return new Result(400,"????????????","");
    }

    @RequestMapping("/collect")
    public Result collectBook(@RequestParam Integer user_id, Integer book_id) {
        Book book = bookService.findById(book_id);
        User user = userService.findById(user_id);
        System.out.println("book"+book);
        System.out.println("userColl"+userCollectBookService.findByUserAndBookId(book_id,user_id));
        if(book != null && user != null && userCollectBookService.findByUserAndBookId(user_id,book_id) == null) {
            userCollectBookService.insert(book_id,user_id);
            return new Result(200,"????????????","");
        }
        return new Result(400,"????????????","");
    }
    @RequestMapping("/unCollect")
    public Result unCollectBook(@RequestParam Integer user_id, Integer book_id) {
        Book book = bookService.findById(book_id);
        User user = userService.findById(user_id);
        if(book != null && user != null && userCollectBookService.findByUserAndBookId(user_id,book_id) != null) {
            userCollectBookService.delete(book_id,user_id);
            return new Result(200,"????????????","");
        }
        return new Result(400,"????????????","");
    }

    @RequestMapping("/findByUserId")
    public List<Book> findByUserId(Integer user_id){
        return bookService.findByUser(user_id);
    }
}
