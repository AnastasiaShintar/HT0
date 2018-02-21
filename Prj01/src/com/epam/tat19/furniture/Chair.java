package com.epam.tat19.furniture;

public class Chair extends Furniture{

	public Chair(int furnitureArea) {
		super(furnitureArea);

	}

	@Override
	public String toString() {
		return "Chair (area = " + furnitureArea + " м^2)";
	}

}
