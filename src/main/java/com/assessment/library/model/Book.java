package com.assessment.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.assessment.library.util.CustomDateDeserializer;
import com.assessment.library.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;


@Entity
@Table(name = "book")
public class Book implements Serializable {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	@NotNull
	@Size(min = 0, max = 100)
	private String title;
	
	@Column(name = "author")
	@NotNull
	@Size(min = 0, max = 50)
	private String author;
	
	@Column(name = "isbn")
	@NotNull
	@Size(min = 0, max = 20)
	private String isbn;
	
	@Column(name = "genre")
	@Size(min = 0, max = 20)
	private String genre;
	
	@Column(name = "publish_date")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date publishDate;
	
	public Book() {
		super();
	}

	public Book(Long id, @NotNull String title, @NotNull String author, @NotNull String isbn, String genre,
			Date publishDate) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.publishDate = publishDate;
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}
