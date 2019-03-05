package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContractController
{
	@Autowired
    private ContractService contractService;

	/**
	 * @API POST
	 * ADDING NEW CONTRACT
	 */
	// ToDo : add error handeling
    @PostMapping("/api/contract")
	private ResponseEntity<?> createContract( @RequestBody Contract contract )
	{

        return new ResponseEntity<Contract>(contractService.addContract(contract), HttpStatus.CREATED);
	}

	/**
	 * @API GET
	 * GET ALL CONTRACTS
	 */
    @GetMapping("/api/contract")
	private ResponseEntity<?> getAllContracts()
	{
        return new ResponseEntity<Iterable<Contract>>(contractService.getAllContracts(), HttpStatus.OK);
	}

	/**
	 * @API GET
	 * GET SINGLE CONTRACT
	 */
    @GetMapping("/api/contract/{id}")
	private ResponseEntity<?> getContract( @PathVariable(value = "id") int id )
	{
        return new ResponseEntity<>(contractService.getById(id), HttpStatus.OK);
	}

	/**
	 * @API DELETE
	 * DELETE SINGLE CONTRACT
	 */
    @DeleteMapping("/api/contract/{id}")
	private ResponseEntity<?> deleteContract( @PathVariable(value = "id") int id )
	{

        return new ResponseEntity<Contract>(contractService.deleteById(id), HttpStatus.OK);
	}

	/**
	 * @API GET
	 * GET ALL ROOMS FOR THE CONTRACT
	 */
    @GetMapping("/api/contract/{id}/rooms/")
	private ResponseEntity<?> getAllRoomsinContact( @PathVariable(value = "id") int id )
	{
        return new ResponseEntity<Iterable<Room>>(contractService.getRooms(id), HttpStatus.OK);
    }

    /**
     * @API GET
     * GET ALL ROOMS IN A CONTRACT
     */
    @GetMapping("/api/contract/{id}/rooms/{rid}")
    private ResponseEntity<?> getRoominContact(@PathVariable(value = "id") int id, @PathVariable(value = "rid") int rid) {
        return new ResponseEntity<Room>(contractService.getRoom(id, rid), HttpStatus.OK);
    }

    /**
     * @API POST
     * PATCH Add a new room to contract
     */
    @PostMapping("/api/contract/{id}/rooms/")
    private ResponseEntity<?> addNewRoom(@PathVariable(value = "id") int id, @RequestBody Room room) {
        return new ResponseEntity<>(contractService.addNewRoom(id, room), HttpStatus.OK);
    }

    /**
     * @API DELETE
     * DELETE contract
     */
    @DeleteMapping("/api/contract")
    private ResponseEntity<?> deleteAll() {
        return new ResponseEntity<>(contractService.deleteAll(), HttpStatus.OK);
    }


}
