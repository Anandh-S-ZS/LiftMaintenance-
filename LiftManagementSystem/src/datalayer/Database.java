package datalayer;

import java.util.HashMap;

import models.Lift;

public class Database {
	private static Database db;
	private Database() {
		lifts = new HashMap<String, Lift>();
	};
	
	private HashMap<String, Lift> lifts;
	// lift id, lift object

	public static Database getInstance() {
		if(db == null)db = new Database();
		return db;
	}

	public HashMap<String, Lift> getLifts() {
		return lifts;
	}

	public void setLifts(HashMap<String, Lift> lifts) {
		this.lifts = lifts;
	}
	
}
