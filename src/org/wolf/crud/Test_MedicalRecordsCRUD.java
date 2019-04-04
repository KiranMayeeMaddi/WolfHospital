package org.wolf.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.wolf.baseclasses.Test_MedicalRecords;

class TestMedicalRecordMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Test_MedicalRecords t = new Test_MedicalRecords();
		t.setRecord_id(rs.getInt("record_id"));
		t.setResult(rs.getString("result"));
		t.setTest_date(rs.getString("datetime"));
		t.setTest_id(rs.getInt("test_id"));
		return t;
	}
}

public final class Test_MedicalRecordsCRUD {
	
	public static Test_MedicalRecords viewTestMedicalRecords(Integer record_id, Integer test_id){
		return null;
	}
	
	public static ArrayList<Test_MedicalRecords> viewTestMedicalRecordsByRecordId(Integer record_id){
		return null;
	}
	
	public static ArrayList<Test_MedicalRecords> viewTestMedicalRecordsByTestId(Integer test_id){
		return null;
	}
	
	public static ArrayList<Test_MedicalRecords> viewTestMedicalRecords(){
		return null;
	}
	
	public static Boolean insertTest_MedicalRecords(Integer record_id, Integer test_id, String test_date, String result){
		return null;
	}
	
	public static Boolean updateTest_MedicalRecords(Integer record_id, Integer test_id, String test_date, String result){
		return null;
	}
	
	public static Boolean deleteTest_MedicalRecords(Integer record_id, Integer test_id, String test_date, String result){
		return null;
	}
}
