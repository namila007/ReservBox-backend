package me.namila.reservbox.ReservBox.Controller;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class FindRooms
{
    final DateFormat format = new SimpleDateFormat("YYYY-MM-DD");

    @Autowired
    private ContractRepository contractRepository;

//    @GetMapping
//    private ResponseEntity<?> status() {
//        return new ResponseEntity<>("Okay",HttpStatus.OK);
//    }

    @GetMapping()
    private ResponseEntity<?> getContract(@RequestParam("adults") int adults, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        Date start = null, end = null;
        try {
            start = format.parse(startDate);
            end = format.parse(endDate);
            System.out.println(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<List<Contract>>(contractRepository.getSearch(adults, start, end), HttpStatus.OK);
    }
}
