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

public class MenuDAO extends JDBConnect {
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

	// 가게의 메뉴들 가져오기
	public synchronized ArrayList<MenuDTO> getMenuList(int storeId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("select menu_id, store_id, menu_name, price, pic, notice from menu where store_id = ?");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, storeId);

			rs = pstmt.executeQuery();

			ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
			while (rs.next()) {
				MenuDTO dto = new MenuDTO();
				dto.setMenuId(rs.getInt("menu_id"));
				dto.setStoreId(rs.getInt("store_id"));
				dto.setMenuName(rs.getString("menu_name"));
				dto.setPic(rs.getString("pic"));
				dto.setPrice(rs.getInt("price"));
				dto.setNotice(rs.getString("notice"));
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

	// 메뉴 추가
	public synchronized boolean addMenu(MenuDTO menuDTO) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("insert into menu( store_id, menu_name, price, notice, pic ) values ( ?, ?, ?, ?, ? )");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, menuDTO.getStoreId());
			pstmt.setString(2, menuDTO.getMenuName());
			pstmt.setInt(3, menuDTO.getPrice());
			pstmt.setString(4, menuDTO.getNotice());
			pstmt.setString(5, menuDTO.getPic());

			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 추가하기 오류 " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// 메뉴 수정하기
	public synchronized boolean updateMenu(MenuDTO menuDTO) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("UPDATE menu ");
			query.append("SET ");
			query.append("store_id = ?, menu_name = ?, pic = ?, price = ?, notice = ? ");
			query.append("WHERE menu_id = ? ");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, menuDTO.getStoreId());
			pstmt.setString(2, menuDTO.getMenuName());
			pstmt.setString(3, menuDTO.getPic());
			pstmt.setInt(4, menuDTO.getPrice());
			pstmt.setString(5, menuDTO.getNotice());
			pstmt.setInt(6, menuDTO.getMenuId());

			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("가게 메뉴 수정하기 오류 " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// 메뉴 DTO 가져오기
	public synchronized MenuDTO getMenuDTO(int menuId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("select menu_id, store_id, menu_name, price, pic, notice from menu where menu_id = ?");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, menuId);

			rs = pstmt.executeQuery();

			MenuDTO dto = new MenuDTO();
			while (rs.next()) {
				
				dto.setMenuId(rs.getInt("menu_id"));
				dto.setStoreId(rs.getInt("store_id"));
				dto.setMenuName(rs.getString("menu_name"));
				dto.setPic(rs.getString("pic"));
				dto.setPrice(rs.getInt("price"));
				dto.setNotice(rs.getString("notice"));
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메뉴 DTO 가져오기 " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}
	
	// 메뉴 삭제하기
	public synchronized boolean delMenu(int menuId) throws SQLException{
		try {

			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("DELETE FROM menu ");
			query.append("WHERE menu_id = " + menuId + "");

			pstmt = conn.prepareStatement(query.toString());

			if (pstmt.executeUpdate() != 1) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MenuDAO_메뉴삭제_ERROR");
			return false;
		} finally {
			disconnectPstmt();
		}
	}
}
