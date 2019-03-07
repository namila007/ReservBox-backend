package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController
{

	private RoomService roomService;

	/**
	 * @API POST
	 * ADDING NEW ROOM
	 */
	// ToDo : add error handeling
	@PostMapping
	private ResponseEntity<?> create( @RequestBody Room room )
	{

		return new ResponseEntity<>( roomService.addRoom( room ), HttpStatus.CREATED );
	}

	/**
	 * @API GET
	 * GET ALL CONTRACTS
	 */
	@GetMapping
	private ResponseEntity<?> get()
	{
		return new ResponseEntity<Iterable<Room>>( roomService.getAll(), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET SINGLE CONTRACT
	 */
	@GetMapping("/{id}")
	private ResponseEntity<?> getRoom( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<Room>( roomService.getById( id ), HttpStatus.OK );
	}

	/**
	 * @API DELETE
	 * DELETE SINGLE CONTRACT
	 */
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteRoom( @PathVariable(value = "id") int id )
	{

		return new ResponseEntity<Room>( roomService.deleteById( id ), HttpStatus.OK );
	}

	/**
	 * @API DELETE
	 * DELETE contract
	 */
	@DeleteMapping()
	private ResponseEntity<?> deleteAll()
	{
		return new ResponseEntity<>( roomService.deleteAll(), HttpStatus.OK );
	}

	@Autowired
	public void setRoomService( RoomService roomService )
	{
		this.roomService = roomService;
	}
}
