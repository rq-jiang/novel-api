package com.example.demo.service;

import com.example.demo.entity.Chapter;
import com.example.demo.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService{

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public List<Chapter> findByBookId(Integer id) {
        return chapterMapper.findByBookId(id);
    }

    @Override
    public List<Chapter> findByListBookId(Integer id) {
        return chapterMapper.findByListBookId(id);
    }

    @Override
    public Chapter findById(Integer id,Integer book_id) {
        return chapterMapper.findById(id,book_id);
    }

    @Transactional
    @Override
    public boolean insertChapter(Chapter chapter) {
        return chapterMapper.insertChapter(chapter);
    }

    @Override
    public List<Chapter> findByListLittle(Integer id) {
        return chapterMapper.findByListLittle(id);
    }
}
