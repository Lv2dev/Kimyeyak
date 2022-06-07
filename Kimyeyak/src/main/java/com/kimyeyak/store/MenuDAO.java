package com.kimyeyak.store;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kimyeyak.dbconnect.DBConnecter;
import com.kimyeyak.dbconnect.JDBConnect;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.util.LinkedHashMap;

public class MenuDAO extends JDBConnect{
	private static MenuDAO menuDAO = new MenuDAO();

	// 생성자
	private MenuDAO() {
	}

	// 인스턴스 getter
	public static MenuDAO getInstance() {
		if (menuDAO == null) {
			menuDAO = new MenuDAO();
		}
		return menuDAO;
	}
	
	//가게의 메뉴들 가져오기 
	public synchronized ArrayList<MenuDTO> getMenuList(int storeId) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			query.append("select menu_id, store_id, menu_name, price, pic from menu where store_id = ?");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, storeId);
			
			rs = pstmt.executeQuery();
			
			ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
			while(rs.next()) {
				MenuDTO dto = new MenuDTO();
				dto.setMenuId(rs.getInt("menu_id"));
				dto.setStoreId(rs.getInt("store_id"));
				dto.setMenuName(rs.getString("menu_name"));
				dto.setPic(rs.getString("pic"));
				dto.setPrice(rs.getInt("price"));
				list.add(dto);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("가게 메뉴 List로 가져오기 오류 " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}
}
