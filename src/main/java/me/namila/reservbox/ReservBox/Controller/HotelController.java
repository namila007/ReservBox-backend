package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Hotel;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController
{

	private HotelService hotelService;

	/**
	 * @API POST
	 * ADDING NEW CONTRACT
	 */
	// ToDo : add error handeling
	@PostMapping
	private ResponseEntity<?> create( @RequestBody Hotel hotel )
	{

		return new ResponseEntity<Hotel>( hotelService.add( hotel ), HttpStatus.CREATED );
	}

	/**
	 * @API GET
	 * GET ALL CONTRACTS
	 */
	@GetMapping
	private ResponseEntity<?> get()
	{
		return new ResponseEntity<Iterable<Hotel>>( hotelService.getAll(), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET SINGLE CONTRACT
	 */
	@GetMapping("/{id}")
	private ResponseEntity<?> getHotel( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<Hotel>( hotelService.getById( id ), HttpStatus.OK );
	}

	/**
	 * @API DELETE
	 * DELETE SINGLE CONTRACT
	 */
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteHotel( @PathVariable(value = "id") int id )
	{

		return new ResponseEntity<Hotel>( hotelService.deleteById( id ), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET ALL ROOMS FOR THE CONTRACT
	 */
	@GetMapping("{id}/rooms/")
	private ResponseEntity<?> getAllRooms( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<Iterable<Room>>( hotelService.getRooms( id ), HttpStatus.OK );
	}

	/**
	 * @API DELETE
	 * DELETE contract
	 */
	@DeleteMapping()
	private ResponseEntity<?> deleteAll()
	{
		return new ResponseEntity<>( hotelService.deleteAll(), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET Contracts
	 */
	@GetMapping("/{id}/contract")
	private ResponseEntity<?> getContract( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<List<Contract>>( hotelService.getContracts( id ), HttpStatus.OK );
	}

	//THIS IS USELESS

	/**
	 * @API POST
	 * POST Contracts
	 */
	@PostMapping("/{id}/contract")
	private ResponseEntity<?> addContract( @PathVariable(value = "id") int id, @RequestBody Contract contract )
	{
		return new ResponseEntity<Hotel>( hotelService.addContracts( id, contract ), HttpStatus.OK );
	}



	@Autowired
	public void setHotelService( HotelService hotelService )
	{
		this.hotelService = hotelService;
	}
}
