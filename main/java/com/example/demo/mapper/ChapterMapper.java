package com.example.demo.mapper;

import com.example.demo.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChapterMapper {

    List<Chapter> findByBookId(Integer id);

    List<Chapter> findByListBookId(Integer id);

    Chapter findById(@Param("id") Integer id, @Param("book_id") Integer book_id);

    boolean insertChapter(Chapter chapter);
}
