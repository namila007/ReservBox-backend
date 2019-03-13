package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class FindRooms
{

    @Autowired
    private ContractRepository contractRepository;

	@GetMapping()
	private ResponseEntity<?> getContract( @RequestParam("adults") int adults,
			@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate )
	{
		List<Object[]> data = contractRepository.getSearch( adults, startDate, endDate );
		return new ResponseEntity<List<Object[]>>( data, HttpStatus.OK );
    }
}
