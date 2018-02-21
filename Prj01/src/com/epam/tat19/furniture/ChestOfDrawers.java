package com.epam.tat19.furniture;

public class ChestOfDrawers extends Furniture{

	public ChestOfDrawers(int furnitureArea) {
		super(furnitureArea);
		
	}

	@Override
	public String toString() {
		return "ChestOfDrawers (area = " + furnitureArea + " м^2)";
	}

}
