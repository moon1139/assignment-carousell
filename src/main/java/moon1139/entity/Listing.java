package moon1139.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Listing {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String listingId;
	private String title;
	private String description;
	private String price;
	private String userName;
	private String category;
	private Date creationTime;
	
	protected Listing() {
		
	}
	
	public Listing(String userName, String title, String description, String price, String category) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.userName = userName;
		this.category = category;
		
		this.listingId = UUID.randomUUID().toString(); //need generator
		this.creationTime = new Date();
	}
	
	public String getListingId() {
		return listingId;
	}
	public void setListingId(String id) {
		this.listingId = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

//	@Override
//	public String toString() {
//		return "Listing [listingId=" + listingId + ", title=" + title + ", description=" + description + ", price="
//				+ price + ", userName=" + userName + ", category=" + category + ", creationTime=" + creationTime + "]";
//	}
	
	@Override
	public String toString() {
		return title + "|" + description + "|" + price + "|" + creationTime + "|" + category + "|" + userName;
	}
	
}
