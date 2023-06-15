package com.swxy.controller;

import com.alibaba.fastjson.JSONObject;

import com.swxy.pojo.Comment;
import com.swxy.pojo.Userss;
import com.swxy.service.CommentService;
import com.swxy.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private MovieService movieService;

    @RequestMapping("addCommentByUser")
    @ResponseBody
    public JSONObject addCommentByUser(@RequestParam("movieId") long movieId, @RequestParam("commentContent") String CommentContent, HttpServletRequest request) {
        Userss user = (Userss) request.getSession().getAttribute("user");
        JSONObject obj = new JSONObject();
        if (user == null) {
            obj.put("code", 200);
            obj.put("msg", "您未登录,登录之后才可评论~");
        } else {
            Comment comment = new Comment();
            comment.setCommentContent(CommentContent);
            comment.setMovieId(movieId);
            comment.setUserId(user.getUserId());
            comment.setCommentTime(new Date());
            Integer rs = commentService.addComment(comment);
            if (rs > 0) {
                Integer rs2 = movieService.addCommentCount(comment.getMovieId());
                if (rs2 > 0) {
                    obj.put("code", 0);
                    obj.put("msg", "评论成功~");
                } else {
                    obj.put("code", 200);
                    obj.put("msg", "评论失败2~");
                }
            } else {
                obj.put("code", 200);
                obj.put("msg", "评论失败~");
            }
        }
        return obj;
    }

    @RequestMapping("updateComment")
    @ResponseBody
    public JSONObject updateComment(@RequestParam("commentId") long commentId, @RequestParam("commentContent") String commentContent) {
        JSONObject obj = new JSONObject();
        Comment comment = this.commentService.findCommentById(commentId);
        comment.setCommentTime(new Date());
        comment.setCommentContent(commentContent);
        Integer rs = commentService.updateComment(comment);
        if (rs > 0) {
            obj.put("code", 0);
            obj.put("msg", "修改成功~");
        } else {
            obj.put("code", 200);
            obj.put("msg", "修改失败~");
        }
        return obj;
    }


    @RequestMapping("deleteComemnt")
    @ResponseBody
    public JSONObject deleteComemnt(@RequestParam("CommentId") long commentId) {

        JSONObject obj = new JSONObject();
        Integer rs2 = movieService.delCommentCount(commentService.findCommentById(commentId).getMovieId());
        Integer rs = commentService.deleteComment(commentId);
        if (rs > 0) {
            obj.put("code", 0);
            obj.put("msg", "删除成功~");
        } else {
            obj.put("code", 200);
            obj.put("msg", "删除失败~");
        }
        return obj;
    }
}
