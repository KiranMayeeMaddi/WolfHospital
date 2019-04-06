package org.wolf.crud;

import java.sql.*;
import java.util.ArrayList;

import org.wolf.baseclasses.Ward_Nurse;
import org.wolf.config.DatabaseConnection;

public class Ward_NurseCRUD {

	public static ArrayList<Ward_Nurse> viewShifts(){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Ward_Nurse_works_In");
			ArrayList<Ward_Nurse> shifts = new ArrayList<Ward_Nurse>();
			
			while (rs.next()) {
				Ward_Nurse w = new Ward_Nurse();
				w.setEnd_time(rs.getString("end_time"));
				w.setShiftId(rs.getInt("shift_id"));
				w.setStaffId(rs.getInt("staff_id"));
				w.setStart_time(rs.getString("start_time"));
				w.setWardId(rs.getInt("ward_id"));

				shifts.add(w);
			}
			return shifts;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public static Ward_Nurse viewShift(Integer shiftId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from Ward_Nurse_works_In where shift_id="+shiftId);
			Ward_Nurse w = new Ward_Nurse();
			
			while (rs.next()) {
				w.setEnd_time(rs.getString("end_time"));
				w.setShiftId(rs.getInt("shift_id"));
				w.setStaffId(rs.getInt("staff_id"));
				w.setStart_time(rs.getString("start_time"));
				w.setWardId(rs.getInt("ward_id"));
			}
			return w;
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public static Integer insertWardNurse(Integer wardId, Integer staffId, String start_time, String end_time){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
			String query = "insert into Ward_Nurse_works_In (ward_id, staff_id, start_time, end_time)"
					+ " values (?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, wardId);
			st.setInt(2, staffId);
			st.setString(3, start_time);
			st.setString(4, end_time);
			st.executeUpdate();
			
			ResultSet rs = st.executeQuery("select shift_id from Ward_Nurse_works_In order by shift_id desc limit 1");
		    int shift_id = 0;
		    while (rs.next())
		    	shift_id = rs.getInt("shift_id");
		    return shift_id;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return null;
	    }
	}
	
	public static Boolean updateWardNurse(Integer shiftId, Integer wardId, Integer staffId, String start_time, String end_time){
		try {
			Connection conn = DatabaseConnection.getConnection();
			String query = "UPDATE Ward_Nurse_works_In SET ward_id=?, staff_id=?, start_time=?, end_time=? where shift_id=?";
		    PreparedStatement st = conn.prepareStatement(query);
		    st.setInt(1, wardId);
		    st.setInt(2, staffId);
		    st.setString(3, start_time);
		    st.setString(4, end_time);
		    st.setInt(5, shiftId);
		    st.executeUpdate();
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
	
	public static Boolean deleteWardNurse(Integer shiftId){
		try {
			Connection conn = DatabaseConnection.getConnection();
			
		    Statement st = conn.createStatement();
		    st.executeUpdate("DELETE FROM Ward_Nurse_works_In WHERE shift_id= " + shiftId);
		    
		    return true;
	    }
	    catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    	return false;
	    }
	}
}
