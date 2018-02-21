package com.epam.tat19.furniture;

public class Table extends Furniture {

	public Table(int furnitureArea) {
		super(furnitureArea);

	}

	@Override
	public String toString() {
		return "Table (area = " + furnitureArea + " м^2)";
	}
}
