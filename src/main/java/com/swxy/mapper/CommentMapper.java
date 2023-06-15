package com.swxy.mapper;

import com.swxy.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CommentMapper {
Integer addComment(Comment comment);
    List<Comment> findCommentsByMovieId(long movieId);

    Comment findCommentById(long commentId);

    Integer updateComment(Comment comment);

    Integer deleteComment(long commentId);
}
