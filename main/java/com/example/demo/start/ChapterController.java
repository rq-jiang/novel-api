package com.example.demo.start;

import com.example.demo.entity.Chapter;
import com.example.demo.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/list")
    public List<Chapter> findByBookId(Integer id){
        return chapterService.findByBookId(id);
    }

    @RequestMapping("/listAll")
    public List<Chapter> findByListBookId(Integer id){
        return chapterService.findByListBookId(id);
    }

    @RequestMapping("/findById")
    public Chapter findById(Integer id,Integer book_id){
        return chapterService.findById(id,book_id);
    }

    @RequestMapping("/uploadChapter")
    public boolean uploadChapter(@RequestBody Map<String,Object> map){
        System.out.println("名字为"+map.get("name")+"内容"+map.get("content"));
        String name = (String) map.get("name");
        String content = (String)map.get("content");
        Integer book_id = (Integer)map.get("book_id");
        Chapter chapter = new Chapter();
        chapter.setName(name);
        chapter.setContent(content);
        chapter.setWord_count(content.length());
        chapter.setCreated_at(new Date());
        chapter.setUpdated_at(new Date());
        chapter.setBook_id(book_id);
        chapter.setVolume_id(1);
        return chapterService.insertChapter(chapter);
    }

}
