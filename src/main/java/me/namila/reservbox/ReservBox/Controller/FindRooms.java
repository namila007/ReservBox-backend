package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Model.SearchRequest;
import me.namila.reservbox.ReservBox.Model.SearchResult;
import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import me.namila.reservbox.ReservBox.Service.FindRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class FindRooms
{

    @Autowired
    private ContractRepository contractRepository;

	private FindRoomsService findRoomsService;

	@GetMapping()
	private ResponseEntity<?> getContract( @RequestParam("adults") int adults,
			@RequestParam("rooms") int noOfrooms,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate )
	{
		//List<Object[]> data = contractRepository.getSearch( adults, startDate, endDate );
		return new ResponseEntity<List<SearchResult>>(
				findRoomsService.findRooms( noOfrooms, adults, startDate, endDate ), HttpStatus.OK );
    }

	@PostMapping
	private ResponseEntity<?> getContract( @RequestBody SearchRequest searchRequest )
	{
		//List<Object[]> data = contractRepository.getSearch( adults, startDate, endDate );
		return new ResponseEntity<List<SearchResult>>(
				findRoomsService.findRoomsbyList( searchRequest ), HttpStatus.OK );
	}

	@Autowired
	public void setFindRoomsService( FindRoomsService findRoomsService )
	{
		this.findRoomsService = findRoomsService;
	}
}
