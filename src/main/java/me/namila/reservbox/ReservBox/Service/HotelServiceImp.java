package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Hotel;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImp implements HotelService
{
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Contract addContract( Contract contract )
	{
		return null;
	}

	@Override
	public Iterable<Hotel> getAll()
	{
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getById( int id )
	{
		return hotelRepository.findById( id ).get();
	}

	@Override
	public Hotel deleteById( int id )
	{
		Hotel hotel = hotelRepository.findById( id ).get();
		hotelRepository.deleteById( id );
		return hotel;
	}

	//Todo rooms
	@Override
	public Iterable<Room> getRooms( int id )
	{

		return null;
	}

	@Override
	public List<Contract> getContracts( int id )
	{
		return hotelRepository.findById( id ).get().getContracts();
	}

	@Override
	public boolean deleteAll()
	{
		hotelRepository.deleteAll();
		return true;
	}

	@Override
	public Hotel add( Hotel hotel )
	{

		return hotelRepository.save( hotel );
	}

}
