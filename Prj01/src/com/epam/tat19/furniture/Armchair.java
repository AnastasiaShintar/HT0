package com.epam.tat19.furniture;

public class Armchair extends Furniture{

	public Armchair(int furnitureArea) {
		super(furnitureArea);
	}

	@Override
	public String toString() {
		return "Armchair (area = " + furnitureArea + " м^2)";
	}

}
