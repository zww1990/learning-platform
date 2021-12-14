package com.example.seataclient.domain;

/**
 * 食品
 */
public class Food {
	private Integer foodId;
	private String foodName;
	private Long stock;

	public Food() {
		super();
	}

	/**
	 * @param foodId   主键
	 * @param foodName 食品名称
	 * @param stock    库存
	 */
	public Food(Integer foodId, String foodName, Long stock) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.stock = stock;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public Long getStock() {
		return stock;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format("Food [foodId=%s, foodName=%s, stock=%s]", foodId, foodName, stock);
	}
}
