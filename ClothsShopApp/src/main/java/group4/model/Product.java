package group4.model;

// Model describes a PRODUCT: name, price, type, number in stock, size, description and location of the image associated with it.

public class Product 
{
	private int productID;
	private String productName;
	private double price;
	private String productType;
	private int stock;				//Meaning changes depends on context: with products, means the item in stock.
									//								with orders, means how much an item is bought
	private String productSize;
	private String description;
	private String imgURL;

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getType() {
		return productType;
	}
	public void setType(String type) {
		this.productType = type;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}

}
