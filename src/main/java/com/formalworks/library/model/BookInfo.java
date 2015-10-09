/**
 * FileName : BookInfo.java
 * Created  : 2015. 9. 21.
 * Author   : JUNG UK LEE
 * Summary  :
 * Copyright (C) 2015 Formal Works Inc. All rights reserved.
 *
 * ��臾몄꽌��紐⑤뱺 ��옉沅�諛�吏�쟻 �ъ궛沅뚯� (二��щ��띿뒪�먭쾶 �덉뒿�덈떎.
 * ��臾몄꽌���대뼚��遺�텇���덇� �놁씠 蹂듭젣 �먮뒗 �섏젙 �섍굅�� �꾩넚�����놁뒿�덈떎.
 */
package com.formalworks.library.model;

import com.google.gson.Gson;

/**
 * @author JUNG UK LEE
 * 
 */
public class BookInfo {

	private String callNo;
	private String regNo;
	private String bookID;
	private String bookTitle;
	private String author;
	private String publisher;
	private String description;
	private String imagePath;
	private String originLocation;
	private String currentLocation;
	private String isbn;

	public String getCallNo() {
		return callNo;
	}

	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setReqNo(String regNo) {
		this.regNo = regNo;
	}

	public String getOriginLocation() {
		return originLocation;
	}

	public void setOriginLocation(String originLocation) {
		this.originLocation = originLocation;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
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
