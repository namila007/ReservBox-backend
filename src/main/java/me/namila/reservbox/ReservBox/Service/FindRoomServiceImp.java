package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.Contract;
import me.namila.reservbox.ReservBox.Model.Room;
import me.namila.reservbox.ReservBox.Model.SearchResult;
import me.namila.reservbox.ReservBox.Repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class FindRoomServiceImp implements FindRoomsService
{

	private ContractRepository contractRepository;
	private List<SearchResult> searchResults = null;

	public List<SearchResult> findRooms( int noOfRooms, int noOfAdults, Date startDate, Date endDate )
	{
		List<Contract> contracts = contractRepository.getContractsByStartDateBetweenAndEndDate( startDate, endDate );
		List<SearchResult> sr = filterRooms( contracts, noOfRooms, noOfAdults );
		return sr;
	}

	private List<SearchResult> filterRooms( List<Contract> contracts, int noOfRooms, int noOfAdults )
	{
		this.searchResults = new ArrayList<>();
		Iterator iterator = contracts.listIterator();
		while ( iterator.hasNext() )
		{
			Contract fcontract = ( Contract ) iterator.next();
			List<Room> rooms = fcontract.getRooms();
			Iterator roomIterator = rooms.listIterator();

			while ( roomIterator.hasNext() )
			{
				Room room = ( Room ) roomIterator.next();
				if ( !( room.getMaxAdults() >= noOfAdults && room.getNoOfRooms() >= noOfRooms ) )
				{
					roomIterator.remove();
				}

			}
			createObjects( fcontract );
		}

		return searchResults;
	}

	private void createObjects( Contract contract )
	{
		Iterator roomIterator = contract.getRooms().listIterator();

		while ( roomIterator.hasNext() )
		{
			Room room = ( Room ) roomIterator.next();
			SearchResult searchResult = new SearchResult( contract.getHotel().getName(), room.getRoomType(),
					room.getRoomRate() );
			System.out.println( searchResult.getRoomType() );
			this.searchResults.add( searchResult );
		}
	}

	@Autowired
	public void setContractRepository( ContractRepository contractRepository )
	{
		this.contractRepository = contractRepository;
	}
}
