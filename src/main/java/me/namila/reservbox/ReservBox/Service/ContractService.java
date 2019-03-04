package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import me.namila.reservbox.ReservBox.Repository.HotelRepository;
import me.namila.reservbox.ReservBox.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ContractService
{
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository repository;

    public Contract addContract(Contract contract) {
        if (contract != null) {
            return contractRepository.save(contract);
        } else return null;
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
