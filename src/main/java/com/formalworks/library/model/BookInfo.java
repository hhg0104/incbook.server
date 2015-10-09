/**
 * FileName : BookInfo.java
 * Created  : 2015. 9. 21.
 * Author   : JUNG UK LEE
 * Summary  :
 * Copyright (C) 2015 Formal Works Inc. All rights reserved.
 *
 * 이 문서의 모든 저작권 및 지적 재산권은 (주)포멀웍스에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.formalworks.library.model;

import com.google.gson.Gson;

/**
 * @author JUNG UK LEE
 *
 */
public class BookInfo {

	private String bookID;
	private String bookTitle;
	private String author;
	private String publisher;
	private String description;
	private String imagePath;
	private String location;
	private String isbn;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the bookID
	 */
	public String getBookID() {
		return bookID;
	}

	/**
	 * @param bookID
	 *            the bookID to set
	 */
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle
	 *            the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @return the ispn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param ispn
	 *            the ispn to set
	 */
	public void setIspn(String isbn) {
		this.isbn = isbn;
	}

	public static BookInfo fromJson(String context) {
		Gson gson = new Gson();

		return gson.fromJson(context, BookInfo.class);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
