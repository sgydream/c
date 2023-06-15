package com.swxy.service;

import com.swxy.pojo.Comment;

public interface CommentService {
    Integer addComment(Comment comment);

    Comment findCommentById(long commentId);

    Integer updateComment(Comment comment);

    Integer deleteComment(long commentId);
}
