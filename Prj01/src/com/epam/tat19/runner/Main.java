package com.epam.tat19.runner;

import com.epam.tat19.building.Building;
import com.epam.tat19.building.Room;
import com.epam.tat19.exception.IlluminanceTooMuchException;
import com.epam.tat19.exception.RoomNotFoundException;
import com.epam.tat19.exception.SpaceUsageTooMuchException;
import com.epam.tat19.furniture.Chair;
import com.epam.tat19.furniture.Couch;
import com.epam.tat19.furniture.Table;
import com.epam.tat19.illuminance.Lightbulb;

public class Main {

	public static void main(String[] args)
			throws IlluminanceTooMuchException, SpaceUsageTooMuchException, RoomNotFoundException {
		Building building = new Building("Building 1");
		building.addRoom(new Room("Room1", 15, 5));
		building.addRoom(new Room("Room2", 15, 5));

		building.getRoom("Room1").addFurniture(new Table(5));
		building.getRoom("Room2").addFurniture(new Table(7));
		building.getRoom("Room1").addLightbulb(new Lightbulb(150));
		building.getRoom("Room1").addLightbulb(new Lightbulb(250));
		building.getRoom("Room1").addFurniture(new Couch(5));
		building.getRoom("Room2").addLightbulb(new Lightbulb(150));
		building.getRoom("Room2").addLightbulb(new Lightbulb(250));

		Building building2 = new Building("Building 2");
		building2.addRoom(new Room("Room1", 15, 2));
		building2.addRoom(new Room("Room2", 15, 7));

		building2.getRoom("Room1").addFurniture(new Table(3));
		building2.getRoom("Room2").addFurniture(new Table(3));
		building2.getRoom("Room1").addLightbulb(new Lightbulb(150));
		building2.getRoom("Room1").addLightbulb(new Lightbulb(250));
		building2.getRoom("Room2").addLightbulb(new Lightbulb(150));
		building2.getRoom("Room2").addLightbulb(new Lightbulb(250));

		building.describe();
		building2.describe();

	}

}
