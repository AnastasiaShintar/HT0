package com.epam.tat19.building;
/*предметы в помещении не должны занимать более 70% площади
 * освещённость помещения не должна выходить за пределы 300-4000 лк*/

import java.util.ArrayList;
import java.util.List;

import com.epam.tat19.exception.IlluminanceTooMuchException;
import com.epam.tat19.exception.SpaceUsageTooMuchException;
import com.epam.tat19.furniture.Furniture;
import com.epam.tat19.illuminance.Lightbulb;
import com.epam.tat19.illuminance.Window;

public class Room {

	private int roomArea;
	private int windowCount;
	private String roomName;
	private int minLux = 300;
	private int maxlux = 4000;
	private List<Furniture> furnitures = new ArrayList<>();
	private List<Lightbulb> lightbulbes = new ArrayList<>();

	public Room(String roomName, int roomArea, int windowCount) {
		this.roomName = roomName;
		this.roomArea = roomArea;
		this.windowCount = windowCount;
	}

	public double getArea() {
		return roomArea;
	}

	public void setArea(int area) {
		this.roomArea = area;
	}

	public int getWindowCount() {
		return windowCount;
	}

	public void setWindowCount(int windowCount) {
		this.windowCount = windowCount;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void addFurniture(Furniture furniture) throws SpaceUsageTooMuchException { //добавляем мебель
		if (furniture != null) {
			if (getFurnitureArea() + furniture.getFurnitureArea() <= roomArea * 0.7) {// проверка использованной площади не более 70%
				furnitures.add(furniture);
			} else {
				throw new SpaceUsageTooMuchException("Too much furniture. Remove anything.");
			}
		}
	}

	public int getFurnitureArea() { //общая площадь мебели
		int totalFurnitureArea = 0;
		for (Furniture furniture : furnitures) {
			totalFurnitureArea += furniture.getFurnitureArea();
		}
		return totalFurnitureArea;
	}

	public String getUsedSpace() { //проверка использованной площади
		int usedSpace = getFurnitureArea() * 100 / roomArea;
		return "The area of the room is occupied by " + usedSpace + " %";
	}

	public void addLightbulb(Lightbulb lightbulb) throws IlluminanceTooMuchException { //добавляем лампочки
		int lux = getTotalLux() + lightbulb.getLightbulbLux();
		try {
			if (lux <= maxlux) { //проверка максимального значения освещения 4000лк
				lightbulbes.add(lightbulb);
			} else if (lux > maxlux) {
				throw new IlluminanceTooMuchException("Too Much Illuminance. Current total Lux: " + getTotalLux()); //возбуждение исключения при превышении максимального уровня освещения
			}
			if (lux < minLux) {//проверка минимального значения освещения 300лк
				System.out.println("Not Enough Illuminance. Permissible limits from 300 to 4000 lux.");
			}
		} catch (IlluminanceTooMuchException e) {
			System.out.println("Check the room illumination level! Permissible limits from 300 to 4000 lux.");
		}
	}

	public int getTotalLux() { // метод определяет общую освещенность комнаты
		int totalLux = 0;
		int windowLux = Window.WINDOWLUX * windowCount;
		int lightbulbLux = getTotalLightbulbLux();
		totalLux = windowLux + lightbulbLux;
		return totalLux;
	}
	public int getWindowLux() { //метод определяет общую освещенность окон
		int windowLux = Window.WINDOWLUX * windowCount;
		return windowLux;
	}

	private int getTotalLightbulbLux() { //метод определяет общую освещенность лампочек
		int lux = 0;
		for (Lightbulb lightbulb : lightbulbes) {
			lux += lightbulb.getLightbulbLux();
		}
		return lux;
	}

	@Override
	public String toString() {
		return getRoomName() + "\n" + getUsedSpace() + "\nTotal area of the room: " + roomArea + " м^2\nWindow count=" + windowCount + "\nFurnitures: "
				+ furnitures + "\nTotal furniture area: " + getFurnitureArea() + " м^2" + ", \nTotal lux = "
				+ getTotalLux() + ": lightbulbes: " + lightbulbes + ": Total lightbulbes lux: " + getTotalLightbulbLux()
				+ "lux\n" + "Total window lux: " + getWindowLux() + " (" + windowCount + " windows * " + Window.WINDOWLUX + "lux).\n";
	}

}
