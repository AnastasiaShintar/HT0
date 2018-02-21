package com.epam.tat19.building;

import com.epam.tat19.exception.RoomNotFoundException;

public interface IBuilding {
	
	public void addRoom(Room room);
	
	public Room getRoom(String name) throws RoomNotFoundException;
	
	public void describe();

}
