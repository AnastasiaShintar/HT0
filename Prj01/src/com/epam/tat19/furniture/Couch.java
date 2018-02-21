package com.epam.tat19.furniture;

public class Couch extends Furniture{

	public Couch(int furnitureArea) {
		super(furnitureArea);
	}

	@Override
	public String toString() {
		return "Couch (area = " + furnitureArea + " м^2)";
	}

}
