package com.epam.tat19.furniture;

public class Bed extends Furniture {

	public Bed(int furnitureArea) {
		super(furnitureArea);
	}

	@Override
	public String toString() {
		return "Bed (area = " + furnitureArea + " м^2)";
	}

}
