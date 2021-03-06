package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Hotel;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import me.namila.reservbox.ReservBox.Repository.HotelRepository;
import me.namila.reservbox.ReservBox.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class ContractServiceImp implements ContractService
{
	final DateFormat format = new SimpleDateFormat( "YYYY-MM-DD", Locale.ENGLISH );
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private HotelRepository hotelRepository;

	public Contract addContract( Contract contract )
	{
		if ( contract.getHotel().getId() != 0 )
		{
			//when hotel id is sent.this is working
			Hotel hotel = hotelRepository.findById( contract.getHotel().getId() ).get();
			hotel.addContract( contract );
			contract.setHotel( hotel );
			hotelRepository.save( hotel );
		}
		//else new hotel is added
		return contractRepository.save( contract );

	}

	public Iterable<Contract> getAllContracts()
	{
		return contractRepository.findAll();
	}

	public Contract getById( int id )
	{
		return contractRepository.findById( id ).get();
	}

	public Contract deleteById( int id )
	{
		Contract contract = contractRepository.findById( id ).get();
		contractRepository.deleteById( id );
		return contract;
	}

	public Iterable<Room> getRooms( int id )
	{
		return contractRepository.findById( id ).get().getRooms();

	}

	public Room getRoom( int cid, int rid )
	{
		Contract contract = contractRepository.findById( cid ).get();
		return contract.getRooms().get( rid );
	}

	public Room addNewRoom( int id, Room room )
	{
		room.setContract( contractRepository.findById( id ).get() );
		return roomRepository.save( room );

	}

	public boolean deleteAll()
	{
		contractRepository.deleteAll();
		return true;
	}

	@Override
	public Hotel getHotel( int id )
	{
		Hotel hotel = contractRepository.findById( id ).get().getHotel();
		hotel.setContracts( null );
		return hotel;
	}
}
