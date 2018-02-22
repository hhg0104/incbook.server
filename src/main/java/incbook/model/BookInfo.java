package incbook.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class BookInfo {

	private int id;

	private String title;

	private String author;

	private String publisher;

	private String description;

	private String imageUrl;

	private String location;

	private String isbn;

	private Date createdDate;

	private Date modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public Map<String, Object> toMap() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", this.id); //$NON-NLS-1$
		map.put("title", this.title); //$NON-NLS-1$
		map.put("author", this.author); //$NON-NLS-1$
		map.put("publisher", this.publisher); //$NON-NLS-1$
		map.put("description", this.description); //$NON-NLS-1$
		map.put("imageUrl", this.imageUrl); //$NON-NLS-1$
		map.put("location", this.location); //$NON-NLS-1$
		map.put("isbn", this.isbn); //$NON-NLS-1$

		return map;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
