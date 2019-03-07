package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Hotel;
import me.namila.reservbox.ReservBox.Model.Room;

import java.util.List;

public interface HotelService
{
	Contract addContract( Contract contract );

	Iterable<Hotel> getAll();

	Hotel getById( int id );

	Hotel deleteById( int id );

	Iterable<Room> getRooms( int id );

	List<Contract> getContracts( int id );

	boolean deleteAll();

	Hotel add( Hotel hotel );
}
