package com.epam.tat19.furniture;

public class OtherFurniture extends Furniture {

	public OtherFurniture(int furnitureArea) {
		super(furnitureArea);

	}

	@Override
	public String toString() {
		return "OtherFurniture (area = " + furnitureArea + " м^2)";
	}

}
