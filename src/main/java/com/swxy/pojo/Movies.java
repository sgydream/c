package com.swxy.pojo;


import java.util.Date;
import java.util.List;

public class Movies {
	private long movieId;
	private String movieCnName;
	private String movieEgName;
	private String movieActor;     //演职人员
	private String movieDirector;  //导演
	private String movieDetail;    //电影详情
	private String movieHour;  //电影时长


	private String movieType; 	    //电影类型
	private float movieScore;      //电影评分
	private float movieBoxOffice;   //票房
	private long movieCommentCount; //电影参评人数
	private Date movieReleaseDate;        //上映时间
	private String movieCountry;    //制片地区
	private String moviePicture;    //电影海报地址
	private Integer movieState; 		 //电影状态 默认1 1：在线 0：下架
	private List<Comment> commentList; //所有的评论信息

	@Override
	public String toString() {
		return "Movies{" +
				"movieId=" + movieId +
				", movieCnName='" + movieCnName + '\'' +
				", movieEgName='" + movieEgName + '\'' +
				", movieActor='" + movieActor + '\'' +
				", movieDirector='" + movieDirector + '\'' +
				", movieDetail='" + movieDetail + '\'' +
				", movieHour='" + movieHour + '\'' +
				", movieType='" + movieType + '\'' +
				", movieScore=" + movieScore +
				", movieBoxOffice=" + movieBoxOffice +
				", movieCommentCount=" + movieCommentCount +
				", movieReleaseDate=" + movieReleaseDate +
				", movieCountry='" + movieCountry + '\'' +
				", moviePicture='" + moviePicture + '\'' +
				", movieState=" + movieState +
				", commentList=" + commentList +
				'}';
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieCnName() {
		return movieCnName;
	}

	public void setMovieCnName(String movieCnName) {
		this.movieCnName = movieCnName;
	}

	public String getMovieEgName() {
		return movieEgName;
	}

	public void setMovieEgName(String movieEgName) {
		this.movieEgName = movieEgName;
	}

	public String getMovieActor() {
		return movieActor;
	}

	public void setMovieActor(String movieActor) {
		this.movieActor = movieActor;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieDetail() {
		return movieDetail;
	}

	public void setMovieDetail(String movieDetail) {
		this.movieDetail = movieDetail;
	}

	public String getMovieHour() {
		return movieHour;
	}

	public void setMovieHour(String movieHour) {
		this.movieHour = movieHour;
	}

	public String getMovieType() {
		return movieType;
	}

	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	public float getMovieScore() {
		return movieScore;
	}

	public void setMovieScore(float movieScore) {
		this.movieScore = movieScore;
	}

	public float getMovieBoxOffice() {
		return movieBoxOffice;
	}

	public void setMovieBoxOffice(float movieBoxOffice) {
		this.movieBoxOffice = movieBoxOffice;
	}

	public long getMovieCommentCount() {
		return movieCommentCount;
	}

	public void setMovieCommentCount(long movieCommentCount) {
		this.movieCommentCount = movieCommentCount;
	}

	public Date getMovieReleaseDate() {
		return movieReleaseDate;
	}

	public void setMovieReleaseDate(Date movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}

	public String getMovieCountry() {
		return movieCountry;
	}

	public void setMovieCountry(String movieCountry) {
		this.movieCountry = movieCountry;
	}

	public String getMoviePicture() {
		return moviePicture;
	}

	public void setMoviePicture(String moviePicture) {
		this.moviePicture = moviePicture;
	}

	public Integer getMovieState() {
		return movieState;
	}

	public void setMovieState(Integer movieState) {
		this.movieState = movieState;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
}
