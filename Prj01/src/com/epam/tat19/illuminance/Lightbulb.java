package com.epam.tat19.illuminance;

public class Lightbulb {


	int lightbulbLux;

	public Lightbulb(int lightbulbLux) {
		this.lightbulbLux = lightbulbLux;
	}

	public int getLightbulbLux() {
		return lightbulbLux;
	}

	public void setLightbulbLux(int lightbulbLux) {
		this.lightbulbLux = lightbulbLux;
	}

	@Override
	public String toString() {
		return "lightbulbLux=" + lightbulbLux;
	}

}
