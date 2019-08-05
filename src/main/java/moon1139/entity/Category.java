package moon1139.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String category;
	private String userName;
	private int listingCount;
	
	protected Category () {
		
	}
	
	public Category(String category, String userName) {
		this.setCategory(category);
		this.setUserName(userName);
		this.setListingCount(0);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getListingCount() {
		return listingCount;
	}

	public void setListingCount(int listingCount) {
		this.listingCount = listingCount;
	}
	
	public void addListingCount() {
		this.listingCount++;
	}
	
	public void minusListingCount() {
		this.listingCount--;
	}
	
	
}
