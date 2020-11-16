package ultilities;

import java.util.ArrayList;

import model.Bike;
import model.Station;

public class Contants {

	public static Contants sharedInstance = new Contants();
	
	public ArrayList<Station> getAllStations() {
		ArrayList<Station> list = new ArrayList<Station>();
		Station s = new Station("Hust", "So 1 DaiCoViet");
		list.add(s);
		
		Station s2 = new Station("CV Thong Nhat", "So 1  Tran nhan Tong");
		list.add(s2);
		return list;
	}
	
	public ArrayList<Bike> getAllBikes(int stationId) {
		ArrayList<Bike> list = new ArrayList<Bike>();
		Bike bike = new Bike(112233);
		list.add(bike);
		return list;
	}
	
	public Bike getBikeInfomation(int bikeId) {
		Bike bike = new Bike(112233);
		return bike;
	}
	
}
