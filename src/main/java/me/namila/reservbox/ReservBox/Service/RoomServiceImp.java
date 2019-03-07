package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImp implements RoomService
{
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Room addRoom( Room room )
	{
		return roomRepository.save( room );
	}

	@Override
	public Iterable<Room> getAll()
	{
		return roomRepository.findAll();
	}

	@Override
	public Room getById( int id )
	{
		return roomRepository.findById( id ).get();
	}

	@Override
	public Room deleteById( int id )
	{
		Room room = roomRepository.findById( id ).get();
		roomRepository.deleteById( id );
		return room;
	}

	@Override
	public boolean deleteAll()
	{
		roomRepository.deleteAll();
		return true;
	}

}
