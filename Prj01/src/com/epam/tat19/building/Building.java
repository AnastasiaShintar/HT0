package com.epam.tat19.building;

/*предметы в помещении не должны занимать более 70% площади
 * освещённость помещения не должна выходить за пределы 300-4000 лк*/

import java.util.ArrayList;
import java.util.List;
import com.epam.tat19.building.Room;
import com.epam.tat19.exception.RoomNotFoundException;

public class Building implements IBuilding {

	String name;
	List<Room> rooms = new ArrayList<>();

	public Building(String name) {
		this.name = name;
	}

	@Override
	public void addRoom(Room room) {

		rooms.add(room);

	}

	@Override
	public Room getRoom(String name) throws RoomNotFoundException { // возвращаем комнату по заданному имени
		Room needRoom = null;
		for (Room room : rooms) {
			if (name.equals(room.getRoomName())) {
				needRoom = room;
			} 			
		}
		if (needRoom == null) {
			throw new RoomNotFoundException("Room not found: " + name);
		}
		return needRoom;
	}

	@Override
	public void describe() { //описание созданных помещений
		System.out.println(name);
		for(Room room : rooms) {
			System.out.println(room);
		}
	}
}
