package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Room;

public interface RoomService
{
	Room addRoom( Room room );

	Iterable<Room> getAll();

	Room getById( int id );

	Room deleteById( int id );

	boolean deleteAll();

}
