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

        Contract fcontract = contractRepository.save(contract);
        return fcontract;
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
        System.out.println(rooms.toString());
        return rooms;
    }

    public Room getRoom(int cid, int rid) {
        Contract contract = contractRepository.findById(cid).get();
        return contract.getRooms().get(rid);
    }

    public Room addNewRoom(int id, Room room) {
        room.setContract(contractRepository.findById(id).get());
        return roomRepository.save(room);

    }

    public boolean deleteAll() {
        contractRepository.deleteAll();
        return true;
    }


}
