package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController(value = "/api/contract")
public class ContractController
{
	@Autowired
    private ContractService contractService;

	/**
	 * @API POST
	 * ADDING NEW CONTRACT
	 */
	// ToDo : add error handeling
	@PostMapping()
	private ResponseEntity<?> createContract( @RequestBody Contract contract )
	{

        return new ResponseEntity<Contract>(contractService.addContract(contract), HttpStatus.CREATED);
	}

	/**
	 * @API GET
	 * GET ALL CONTRACTS
	 */
	@GetMapping()
	private ResponseEntity<?> getAllContracts()
	{
        return new ResponseEntity<Iterable<Contract>>(contractService.getAllContracts(), HttpStatus.OK);
	}

	/**
	 * @API GET
	 * GET SINGLE CONTRACT
	 */
	@GetMapping("/{id}")
	private ResponseEntity<?> getContract( @PathVariable(value = "id") int id )
	{
        return new ResponseEntity<>(contractService.getById(id), HttpStatus.OK);
	}

	/**
	 * @API DELETE
	 * DELETE SINGLE CONTRACT
	 */
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteContract( @PathVariable(value = "id") int id )
	{

        return new ResponseEntity<Contract>(contractService.deleteById(id), HttpStatus.OK);
	}

	/**
	 * @API GET
	 * GET ALL ROOMS FOR THE CONTRACT
	 */
	@GetMapping("/{id}/Rooms/")
	private ResponseEntity<?> getAllRoomsinContact( @PathVariable(value = "id") int id )
	{
        return new ResponseEntity<Iterable<Room>>(contractService.getRooms(id), HttpStatus.OK);
    }

    /**
     * @API GET
     * GET ALL ROOMS IN A CONTRACT
     */
    @GetMapping("/{id}/Rooms/{rid}")
    private ResponseEntity<?> getRoominContact(@PathVariable(value = "id") int id, @PathVariable(value = "rid") int rid) {
        return new ResponseEntity<Room>(contractService.getRoom(id, rid), HttpStatus.OK);
    }

    /**
     * @API POST
     * PATCH Add a new room to contract
     */
    @PostMapping("/{id}/Rooms/")
    private ResponseEntity<?> addNewRoom(@PathVariable(value = "id") int id, @RequestBody Map<String, Object> payload) {
        return new ResponseEntity<>(contractService.addNewRoom(id, payload), HttpStatus.OK);
    }

    /**
     * @API DELETE
     * DELETE contract
     */
    @DeleteMapping()
    private ResponseEntity<?> deleteAll() {
        return new ResponseEntity<>(contractService.deleteAll(), HttpStatus.OK);
    }


}
