package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Hotel;
import me.namila.reservbox.ReservBox.Model.Room;

public interface ContractService
{
	Contract addContract( Contract contract );

	Iterable<Contract> getAllContracts();

	Contract getById( int id );

	Contract deleteById( int id );

	Iterable<Room> getRooms( int id );

	Room getRoom( int cid, int rid );

	Room addNewRoom( int id, Room room );

	boolean deleteAll();

	Hotel getHotel( int id );
}
