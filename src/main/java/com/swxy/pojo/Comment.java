package com.swxy.pojo;


import java.util.Date;
public class Comment {
	private long commentId;
	private long userId; //所属用户编号
	private String commentContent; //评论内容
	private long movieId; //所属电影编号
	private Date commentTime; //评论时间
	private Userss commentUserss; //所属用户

	@Override
	public String toString() {
		return "Comment{" +
				"commentId=" + commentId +
				", userId=" + userId +
				", commentContent='" + commentContent + '\'' +
				", movieId=" + movieId +
				", commentTime=" + commentTime +
				", commentUserss=" + commentUserss +
				'}';
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Userss getCommentUserss() {
		return commentUserss;
	}

	public void setCommentUserss(Userss commentUserss) {
		this.commentUserss = commentUserss;
	}
}
