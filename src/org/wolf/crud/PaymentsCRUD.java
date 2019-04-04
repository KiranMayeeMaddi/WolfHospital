package org.wolf.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.wolf.baseclasses.Payments;

class PaymentMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payments p = new Payments();
		p.setAmountPaid(rs.getDouble("amount_paid"));
		p.setBill_address(rs.getString("billing_address"));
		p.setBill_id(rs.getInt("bill_id"));
		p.setCard_no(rs.getString("card_no"));
		p.setPayer_ssn(rs.getString("payer_ssn"));
		p.setPayment_id(rs.getInt("payment_id"));
		p.setPaymentDate(rs.getString("payment_date"));
		p.setPolicy_no(rs.getString("policy_no"));
		return p;
	}
}

public final class PaymentsCRUD {

	public static ArrayList<Payments> getPaymentsForBill(Integer billId){
		return null;
	}
	
	public static Integer insertPayment(Integer bill_id, String payer_ssn, String bill_address, String policy_no, String card_no, Double amountPaid){
		//Put endDate as current  time stamp
		return null;
	}
}
