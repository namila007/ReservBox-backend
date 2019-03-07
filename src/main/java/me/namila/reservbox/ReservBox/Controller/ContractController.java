package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
public class ContractController
{

	private ContractService contractService;

	/**
	 * @API POST
	 * ADDING NEW CONTRACT
	 */
	// ToDo : add error handeling
	@PostMapping
	private ResponseEntity<?> create( @RequestBody Contract contract )
	{

		return new ResponseEntity<Contract>( contractService.addContract( contract ), HttpStatus.CREATED );
	}

	/**
	 * @API GET
	 * GET ALL CONTRACTS
	 */
	@GetMapping
	private ResponseEntity<?> get()
	{
		return new ResponseEntity<Iterable<Contract>>( contractService.getAllContracts(), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET SINGLE CONTRACT
	 */
	@GetMapping("/{id}")
	private ResponseEntity<?> getContract( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<>( contractService.getById( id ), HttpStatus.OK );
	}

	/**
	 * @API DELETE
	 * DELETE SINGLE CONTRACT
	 */
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteContract( @PathVariable(value = "id") int id )
	{

		return new ResponseEntity<Contract>( contractService.deleteById( id ), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET ALL ROOMS FOR THE CONTRACT
	 */
	@GetMapping("{id}/rooms/")
	private ResponseEntity<?> getAllRoomsinContact( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<Iterable<Room>>( contractService.getRooms( id ), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET ALL ROOMS IN A CONTRACT
	 */
	@GetMapping("/{id}/rooms/{rid}")
	private ResponseEntity<?> getRoominContact( @PathVariable(value = "id") int id,
			@PathVariable(value = "rid") int rid )
	{
		return new ResponseEntity<Room>( contractService.getRoom( id, rid ), HttpStatus.OK );
	}

	/**
	 * @API POST
	 * PATCH Add a new room to contract
	 */
	@PostMapping("/{id}/rooms/")
	private ResponseEntity<?> addNewRoom( @PathVariable(value = "id") int id, @RequestBody Room room )
	{
		return new ResponseEntity<>( contractService.addNewRoom( id, room ), HttpStatus.OK );
	}

	/**
	 * @API DELETE
	 * DELETE contract
	 */
	@DeleteMapping()
	private ResponseEntity<?> deleteAll()
	{
		return new ResponseEntity<>( contractService.deleteAll(), HttpStatus.OK );
	}

	/**
	 * @API GET
	 * GET Hotel
	 */
	@GetMapping("/{id}/hotel")
	private ResponseEntity<?> getHotel( @PathVariable(value = "id") int id )
	{
		return new ResponseEntity<>( contractService.getHotel( id ), HttpStatus.OK );
	}

	@Autowired
	public void setContractService( ContractService contractService )
	{
		this.contractService = contractService;
	}
}
