package org.wolf.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.wolf.baseclasses.Ward_Nurse;

class WardNurseMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ward_Nurse w = new Ward_Nurse();
		w.setEnd_time(rs.getString("end_time"));
		w.setShiftId(rs.getInt("shift_id"));
		w.setStaffId(rs.getInt("staff_id"));
		w.setStart_time(rs.getString("start_time"));
		w.setWardId(rs.getInt("ward_id"));
		return w;
	}
}

public class Ward_NurseCRUD {

	public static ArrayList<Ward_Nurse> viewShifts(){
		return null;
	}
	
	public static Ward_Nurse viewShift(Integer shiftId){
		return null;
	}
	
	public static Integer insertWardNurse(Integer wardId, Integer staffId, String start_time, String end_time){
		return null;
	}
	
	public static Boolean updateWardNurse(Integer shiftId, Integer wardId, Integer staffId, String start_time, String end_time){
		return null;
	}
	
	public static Boolean deleteWardNurse(Integer shiftId){
		return null;
	}
}
