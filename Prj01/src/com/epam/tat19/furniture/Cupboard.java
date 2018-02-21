package com.epam.tat19.furniture;

public class Cupboard extends Furniture{

	public Cupboard(int furnitureArea) {
		super(furnitureArea);
	}

	@Override
	public String toString() {
		return "Cupboard (area = " + furnitureArea + " м^2)";
	}

}
