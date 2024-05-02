package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import datalayer.Database;
import models.Lift;

public class LiftBoy {
	private static LiftBoy lb;
	Database db;
	private LiftBoy() {
		db = Database.getInstance();
	}

	public static LiftBoy getInstance() {
		if(lb == null)lb = new LiftBoy();
		return lb;
	}

	public boolean createLift(int currentPosition, List<Integer> pos, int cap) {
		Lift l = new Lift(currentPosition, pos, cap);
		db.getLifts().put(l.getId(), l);
		return true;
	}

	public int getLiftData(int liftId) {
		return db.getLifts().get("L"+(liftId+1)).getPosition();
	}

	public String getMyLift(int userCurPos, int userDesPos) {
		boolean availablePos = false,sameDirection = false;
		int near=0,stopCount=0;
		
		boolean lavailablePos = false,LsameDirection = false;
		int Lnear = Integer.MAX_VALUE,LstopCount = Integer.MAX_VALUE;
		String lift = null;
		
		for(String id : db.getLifts().keySet()) {
			availablePos = false;sameDirection = false;
			near=Integer.MAX_VALUE;stopCount=Integer.MAX_VALUE;
			Lift l = db.getLifts().get(id);
			if(l.getPosition() == -1)continue;
			if(l.getTotalCapacity() == l.getCurrentCapacity())continue;
			if(l.getPositions().contains(userCurPos) && l.getPositions().contains(userDesPos)) {
				availablePos = true;
			}
			if(availablePos) {
			near = Math.abs(l.getPosition()- userCurPos);
			stopCount = getStopCount(userCurPos, userDesPos,l.getPositions());
			sameDirection = findDirection(userCurPos, userDesPos,l.getPosition());
			}
			System.out.println("----------------------");
			System.out.println("available: "+availablePos);
			System.out.println("near: "+near);
			System.out.println("stopCount: "+stopCount);
			System.out.println("same direction: "+sameDirection);
			
			
			if(availablePos) {
				if(LstopCount>stopCount && near < Lnear && sameDirection) {
					Lnear = near;
					LsameDirection = sameDirection;
					LstopCount = stopCount;
					lift = id;
				}
				else if(LstopCount>stopCount) {
					if(near <= Lnear && sameDirection) {
					Lnear = near;
					LsameDirection = sameDirection;
					LstopCount = stopCount;
					lift = id;
					}
				}
				else if(near < Lnear && sameDirection) {
					if(LstopCount>=stopCount) {
					Lnear = near;
					LsameDirection = sameDirection;
					LstopCount = stopCount;
					lift = id;
					}
				}
			}
		}
		if(lift != null) {
			db.getLifts().get(lift).setPosition(userDesPos);
			int cap = db.getLifts().get(lift).getCurrentCapacity();
			db.getLifts().get(lift).setCurrentCapacity(++cap);
			System.out.println();
		}
		return lift;
	}

	private boolean findDirection(int userCurPos, int userDesPos, int liftPosition) {
		boolean direction = userCurPos < userDesPos ;
		if(direction) {
			if(liftPosition <= userCurPos)
				return true;
			else
				return false;
		}
		else {
			if(liftPosition >=userCurPos)
				return true;
			else
				return false;
		}
	}

	private int getStopCount(int userCurPos, int userDesPos, List<Integer> positions) {
		Collections.sort(positions);
		int n = 0;
		if(userCurPos > userDesPos) {
			int temp = userCurPos;
			userCurPos = userDesPos;
			userDesPos = temp;
		}
		
		for(int x : positions) {
			if(x >= userCurPos)
				n++;
			if(x == userDesPos)
				break;
		}
		return n;
	}

	public void setLiftMainteinance(String id, boolean b) {
		if(db.getLifts().containsKey(id))
		db.getLifts().get(id).setPosition(b ? 0 : -1);
	}
	
}
