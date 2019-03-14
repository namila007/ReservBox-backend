package me.namila.reservbox.ReservBox.Service;

import me.namila.reservbox.ReservBox.Model.*;
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

	public List<SearchResult> findRoomsbyList( SearchRequest searchRequest )
	{
		List<Contract> contracts = contractRepository
				.getContractsByStartDateBetweenAndEndDate( searchRequest.getStartDate(), searchRequest.getEndDate() );
		int[] arr = filterRequest( searchRequest );
		List<SearchResult> sr = filterRooms( contracts, arr[0], arr[1] );
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
			//System.out.println( searchResult.getRoomType() );
			this.searchResults.add( searchResult );
		}
	}

	private int[] filterRequest( SearchRequest searchRequest )
	{
		Iterator<RoomRequest> roomRequestIterator = searchRequest.getRoomRequestList().listIterator();
		int[] array = new int[2];
		int sumofRooms = 0, maxAdults = 0;
		while ( roomRequestIterator.hasNext() )
		{
			RoomRequest roomRequest = roomRequestIterator.next();
			sumofRooms += roomRequest.getRooms();
			if ( maxAdults < roomRequest.getMaxAdults() )
				maxAdults = roomRequest.getMaxAdults();
		}
		array[0] = sumofRooms;
		array[1] = maxAdults;
		System.out.println( sumofRooms );
		System.out.println( maxAdults );
		return array;
	}

	@Autowired
	public void setContractRepository( ContractRepository contractRepository )
	{
		this.contractRepository = contractRepository;
	}
}
