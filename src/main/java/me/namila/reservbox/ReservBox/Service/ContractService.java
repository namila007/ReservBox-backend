package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import me.namila.reservbox.ReservBox.Repository.HotelRepository;
import me.namila.reservbox.ReservBox.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ContractService
{
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository repository;

}
