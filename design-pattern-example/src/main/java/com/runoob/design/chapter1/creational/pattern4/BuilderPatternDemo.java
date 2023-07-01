package com.runoob.design.chapter1.creational.pattern4;

/**
 * 建造者模式（Builder Pattern）
 */
public class BuilderPatternDemo {
	/**
	 * BuiderPatternDemo 使用 MealBuilder 来演示建造者模式（Builder Pattern）。
	 */
	public static void main(String[] args) {
		MealBuilder mealBuilder = new MealBuilder();

		Meal vegMeal = mealBuilder.prepareVegMeal();
		System.out.println("Veg Meal");
		vegMeal.showItems();
		System.out.println("Total Cost: " + vegMeal.getCost());

		Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
		System.out.println("\n\nNon-Veg Meal");
		nonVegMeal.showItems();
		System.out.println("Total Cost: " + nonVegMeal.getCost());
	}
}
