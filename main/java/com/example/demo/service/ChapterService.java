package com.example.demo.service;

import com.example.demo.entity.Chapter;

import java.util.List;

public interface ChapterService {
    List<Chapter> findByBookId(Integer id);

    List<Chapter> findByListBookId(Integer id);

    Chapter findById(Integer id,Integer book_id);

    boolean insertChapter(Chapter chapter);

    List<Chapter> findByListLittle(Integer id);

}
