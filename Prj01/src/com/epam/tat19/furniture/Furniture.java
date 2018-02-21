package com.epam.tat19.furniture;

public abstract class Furniture {

	int furnitureArea;

	public Furniture(int furnitureArea) {

		this.furnitureArea = furnitureArea;
	}

	public int getFurnitureArea() {
		return furnitureArea;
	}

	public void setFurnitureArea(int furnitureArea) {
		this.furnitureArea = furnitureArea;
	}

	@Override
	public String toString() {
		return "Furniture [furnitureArea=" + furnitureArea + "]";
	}

}
