package org.wolf.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.wolf.baseclasses.Test;

class TestMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Test t = new Test();
		t.setFees(rs.getDouble("fees"));
		t.setLabName(rs.getString("lab_name"));
		t.setSpecialized_doctor(rs.getInt("specialized_doctor"));
		t.setTest_Id(rs.getInt("test_id"));
		t.setTest_name(rs.getString("name"));
		return t;
	}
}

public final class TestCRUD {

	public static Test viewTest(Integer test_id){
		return null;
	}
	
	public static ArrayList<Test> viewTest(){
		return null;
	}
	
	public static Integer insertTest(String test_name, String lab_name, Integer specialized_doctor, Double fees){
		return null;
	}
	
	public static Boolean updateTest(Integer test_id, String test_name, String lab_name, Integer specialized_doctor, Double fees){
		return null;
	}
	
	public static Boolean deleteTest(Integer test_id){
		return null;
	}
}
