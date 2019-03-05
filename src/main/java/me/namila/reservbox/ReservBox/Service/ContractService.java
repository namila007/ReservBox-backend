package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import me.namila.reservbox.ReservBox.Repository.HotelRepository;
import me.namila.reservbox.ReservBox.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

@Component
public class ContractService
{
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private HotelRepository hotelRepository;
    final DateFormat format = new SimpleDateFormat( "YYYY-MM-DD", Locale.ENGLISH );
    @Autowired
    private RoomRepository roomRepository;

    public Contract addContract( Contract contract )
    {

        hotelRepository.save( contract.getHotel() );
        contract.getRooms().forEach( room -> roomRepository.save( room ) );
        contract = contractRepository.save( contract );
        //Hotel hotel = new Hotel(payload.get("name" ).toString(),payload.get("address" ).toString()  );
        //        hotel = hotelRepository.save( hotel );
        //            Contract newcontract = contractRepository.save(contract);
        //            newcontract.setHotel( hotel );
        //            newcontract.getRooms().forEach( room -> room.setContract( newcontract ) );
        return contract;
    }


    public Iterable<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getById(int id) {
        return contractRepository.findById(id).get();
    }

    public Contract deleteById(int id) {
        Contract contract = contractRepository.findById(id).get();
        contractRepository.deleteById(id);
        return contract;
    }

    public Iterable<Room> getRooms(int id) {
        Contract contract = contractRepository.findById(id).get();
        Iterable<Room> rooms = contract.getRooms();
        return rooms;
    }

    public Room getRoom(int cid, int rid) {
        Contract contract = contractRepository.findById(cid).get();
        return contract.getRooms().get(rid);
    }

    public Room addNewRoom(int id, Map<String, Object> payload) {
        Room room = new Room(payload.get("roomType").toString(), (Double) payload.get("roomRate"),
                (int) payload.get("noOfRooms"), (int) payload.get("maxAdults"), this.getById(id));
        return room;
    }

    public boolean deleteAll() {
        contractRepository.deleteAll();
        return true;
    }


}
