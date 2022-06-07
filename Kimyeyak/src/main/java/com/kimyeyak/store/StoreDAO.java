package com.kimyeyak.store;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.kimyeyak.dbconnect.JDBConnect;

public class StoreDAO extends JDBConnect {
	private static StoreDAO storeDAO = new StoreDAO();

	// 생성자
	private StoreDAO() {

	}

	// getters and setters

	// 인스턴스 getter
	public static StoreDAO getInstance() {
		if (storeDAO == null) {
			storeDAO = new StoreDAO();
		}
		return storeDAO;
	}

	// method

	// 가게를 추가하는 메서드
	public synchronized boolean addStore(StoreDTO storeDTO) throws SQLException {
		try {
			conn = dbConn.getConn();
			StringBuffer query = new StringBuffer();

			query.append("INSERT INTO store(member_id, category, notice, tel, thumb, open_time, close_time, ");
			query.append("rest_day, braketime_start, braketime_end, join_day, store_name, status) ");
			query.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setString(1, storeDTO.getMemberId());
			pstmt.setInt(2, storeDTO.getCategory());
			pstmt.setString(3, storeDTO.getNotice());
			pstmt.setString(4, storeDTO.getTel());
			pstmt.setString(5, storeDTO.getThumb());
			pstmt.setInt(6, storeDTO.getOpenTime());
			pstmt.setInt(7, storeDTO.getCloseTime());
			pstmt.setInt(8, storeDTO.getRestDay());
			pstmt.setInt(9, storeDTO.getBraketimeStart());
			pstmt.setInt(10, storeDTO.getBraketimeEnd());

			// 현재시간 timestamp
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(today);

			pstmt.setTimestamp(11, ts);
			pstmt.setString(12, storeDTO.getStoreName());
			pstmt.setInt(13, 0);

			if (pstmt.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("가게추가오류 " + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// 전체 검색 결과의 갯수를 리턴하는 메서드
	public synchronized int getSearchCount(String keyword) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			query.append("select distinct store.store_id, store.store_name, store.thumb ");
			query.append("from store left join menu ");
			query.append("on store.store_id = menu.store_id and ");
			query.append("(store.store_name like '%" + keyword + "%' or menu.menu_name like '%" + keyword
					+ "%') order by store_name");

			pstmt = conn.prepareStatement(query.toString());

			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt++;
			}
			return cnt;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("검색결과의 갯수를 리턴하는 메서드 오류 " + e.getMessage());
			return 0;
		} finally {
			disconnectPstmt();
		}
	}

	// 가게명 검색해서 검색결과 페이징 해서 List로 가져오기
	// keyword : 검색어
	public synchronized ArrayList<StoreDTO> getSearchList(String keyword, int page, int pageCount) throws SQLException {
		ArrayList<StoreDTO> list = new ArrayList<StoreDTO>();
		try {
			conn = dbConn.getConn();

			query = new StringBuffer();
			query.append("select * from ( ");
			query.append(
					"select distinct st.store_id, st.store_name, st.thumb, row_number() over(order by store_name) as num ");
			query.append("from store as st left join menu as m  ");
			query.append("on st.store_id = m.store_id and ");
			query.append("(st.store_name like '%" + keyword + "%' or m.menu_name like '%" + keyword
					+ "%') group by st.store_id order by store_name limit ? ) as temp ");
			query.append("where num > ? order by num ");

			pstmt = conn.prepareStatement(query.toString());

			pstmt.setInt(1, pageCount);
			pstmt.setInt(2, (page - 1) * pageCount);

			rs = pstmt.executeQuery();
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				StoreDTO dto = new StoreDTO();
				dto.setStoreId(rs.getInt("store_id"));
				dto.setStoreName(rs.getString("store_name"));
				dto.setThumb(rs.getString("thumb"));
				list.add(dto);
			}
			if (cnt == 0) {
				return list;
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// 가게id와 유저id를 넣으면 가게정보 한번에 가져오기
	public synchronized StoreDTO getStoreInfo(int storeId) throws SQLException {
		try {
			conn = dbConn.getConn();

			query = new StringBuffer();

			query.append("SELECT * FROM store ");
			query.append("WHERE store_id = ?");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();

			StoreDTO storeDTO = new StoreDTO();

			int cnt = 0;
			while (rs.next()) {
				storeDTO.setStoreId(storeId);
				storeDTO.setMemberId(rs.getString("member_id"));
				storeDTO.setCategory(rs.getInt("category"));
				storeDTO.setNotice(rs.getString("notice"));
				storeDTO.setTel(rs.getString("tel"));
				storeDTO.setThumb(rs.getString("thumb"));
				storeDTO.setOpenTime(rs.getInt("open_time"));
				storeDTO.setCloseTime(rs.getInt("close_time"));
				storeDTO.setRestDay(rs.getInt("rest_day"));
				storeDTO.setBraketimeStart(rs.getInt("braketime_start"));
				storeDTO.setBraketimeEnd(rs.getInt("braketime_end"));
				storeDTO.setJoinDay(rs.getDate("join_day"));
				storeDTO.setStoreName(rs.getString("store_name"));
				storeDTO.setStatus(rs.getInt("status"));
				cnt++;
			}

			if (cnt < 1) {
				return null;
			}

			return storeDTO;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("storeDAO_가게정보한번에가져오기ERROR" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// 주소 가져오기
	public synchronized StoreDTO getAddressInfo(int storeId) throws SQLException {
		try {
			conn = dbConn.getConn();
			stmt = conn.createStatement();
			query = new StringBuffer();

			query.append("SELECT * FROM store ");
			query.append("WHERE store_id = " + storeId + " and address IS NOT null");

			rs = stmt.executeQuery(query.toString());

			int cnt = 0;
			StoreDTO sDTO = new StoreDTO();
			while (rs.next()) {
				sDTO.setStoreId(rs.getInt("store_id"));
				sDTO.setAddressX(rs.getDouble("address_x"));
				sDTO.setAddressY(rs.getDouble("address_y"));
				sDTO.setAddress(rs.getString("address"));
				cnt++;
			}

			if (cnt == 0) {
				return null;
			}

			return sDTO;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("주소가져오기 에러 \n" + e.getMessage());
			return null;
		} finally {
			disconnectStmt();
		}
	}
}
