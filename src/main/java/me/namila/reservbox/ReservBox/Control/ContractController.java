package me.namila.reservbox.ReservBox.Control;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import me.namila.reservbox.ReservBox.Repository.HotelRepository;
import me.namila.reservbox.ReservBox.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/api/contract")
public class ContractController
{
	@Autowired
	private ContractRepository contractRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private RoomRepository repository;

	/**
	 * @API POST
	 * ADDING NEW CONTRACT
	 */
	// ToDo : add error handeling
	@PostMapping()
	private ResponseEntity<?> createContract( @RequestBody Contract contract )
	{

		return new ResponseEntity<Contract>( contractRepository.save( contract ), HttpStatus.CREATED );
	}

	/**
	 * @API GET
	 * GET ALL CONTRACTS
	 */
	@GetMapping()
	private ResponseEntity<?> getAllContracts()
	{
		return new ResponseEntity<Iterable<Contract>>( contractRepository.findAll(), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET SINGLE CONTRACT
	 */
	@GetMapping("/{id}")
	private ResponseEntity<?> getContract( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<>( contractRepository.findById( id ), HttpStatus.OK );
	}

	/**
	 * @API DELETE
	 * DELETE SINGLE CONTRACT
	 */
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteContract( @PathVariable(value = "id") int id )
	{
		Contract contract = contractRepository.findById( id ).get();
		contractRepository.deleteById( id );
		return new ResponseEntity<Contract>( contract, HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET ALL ROOMS FOR THE CONTRACT
	 */
	@GetMapping("/{id}/Rooms/")
	private ResponseEntity<?> getAllRoomsinContact( @PathVariable(value = "id") int id )
	{
		Contract contract = contractRepository.findById( id ).get();
		Iterable<Room> rooms = contract.getRooms();
		return new ResponseEntity<Iterable<Room>>( rooms, HttpStatus.OK );
	}

}
