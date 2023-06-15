package com.swxy.service.Impl;


import com.swxy.mapper.CommentMapper;
import com.swxy.pojo.Comment;
import com.swxy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Integer addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public Comment findCommentById(long commentId) {
        return this.commentMapper.findCommentById(commentId);
    }

    @Override
    public Integer updateComment(Comment comment) {
        return this.commentMapper.updateComment(comment);

    }

    @Override
    public Integer deleteComment(long commentId) {
        return this.commentMapper.deleteComment(commentId);

    }

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
}
