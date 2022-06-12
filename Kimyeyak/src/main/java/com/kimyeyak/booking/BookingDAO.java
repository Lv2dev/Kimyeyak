package com.kimyeyak.booking;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.kimyeyak.dbconnect.JDBConnect;
import com.kimyeyak.store.StoreDAO;

public class BookingDAO extends JDBConnect {
	private static BookingDAO bookingDAO = new BookingDAO();

	// 생성자
	private BookingDAO() {

	}

	// getters and setters

	// 인스턴스 getter
	public static BookingDAO getInstance() {
		if (bookingDAO == null) {
			bookingDAO = new BookingDAO();
		}
		return bookingDAO;
	}

	// method

	// 선택한 가게의 예약규칙들을 가져옴
	public synchronized ArrayList<BookingRuleDTO> getStoreBookingRuleDTOList(int storeId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("select * from booking_rule where store_id = ? order by time");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();

			ArrayList<BookingRuleDTO> list = new ArrayList<BookingRuleDTO>();
			while (rs.next()) {
				BookingRuleDTO i = new BookingRuleDTO();
				i.setId(rs.getInt("id"));
				i.setStoreId(rs.getInt("store_id"));
				i.setMinPeople(rs.getInt("min_people"));
				i.setMaxPeople(rs.getInt("max_people"));
				i.setCount(rs.getInt("count"));
				i.setTime(rs.getTimestamp("time"));
				i.setNotice(rs.getString("notice"));
				list.add(i);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("내 가게 예약규칙 리스트 가져오기 오류" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// 예약규칙 추가하기
	public synchronized boolean addStoreBookingRule(BookingRuleDTO dto) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append(
					"insert into booking_rule (store_id, min_people, max_people, count, time, notice) values(?,?,?,?,?,?) ");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, dto.getStoreId());
			pstmt.setInt(2, dto.getMinPeople());
			pstmt.setInt(3, dto.getMaxPeople());
			pstmt.setInt(4, dto.getCount());
			pstmt.setTimestamp(5, dto.getTime());
			pstmt.setString(6, dto.getNotice());
			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약규칙 추가하기" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// 예약규칙 삭제하기
	public synchronized boolean delStoreBookingRule(int ruleId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("delete from booking_rule where id = ?");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, ruleId);
			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약규칙 삭제하기" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// 예약규칙을 가져옴 오류
	public synchronized BookingRuleDTO getStoreBookingRuleDTO(int ruleId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("select * from booking_rule where id = ?");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, ruleId);
			rs = pstmt.executeQuery();

			BookingRuleDTO i = new BookingRuleDTO();
			while (rs.next()) {
				i.setId(rs.getInt("id"));
				i.setStoreId(rs.getInt("store_id"));
				i.setMinPeople(rs.getInt("min_people"));
				i.setMaxPeople(rs.getInt("max_people"));
				i.setCount(rs.getInt("count"));
				i.setTime(rs.getTimestamp("time"));
				i.setNotice(rs.getString("notice"));
			}

			return i;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약규칙을 가져옴 오류 " + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// 선택된 날짜의 예약 리스트 가져오기
	public synchronized ArrayList<BookingDTO> getStoreBookingDTOList(int storeId, Timestamp date) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append(
					"select * from booking_rule as rule inner join booking as booking on booking.rule_id = rule.id ");
			query.append(
					"where DATEDIFF(DATE_FORMAT(booking.booking_date, '%Y-%m-%d 00:00:00'), ?) = 1 AND booking.state = 0 AND booking.store_id = ?");
			// date
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setTimestamp(1, date);
			pstmt.setInt(2, storeId);
			rs = pstmt.executeQuery();

			ArrayList<BookingDTO> list = new ArrayList<BookingDTO>();
			while (rs.next()) {
				BookingDTO i = new BookingDTO();
				i.setBookingId(rs.getInt("booking_id"));
				i.setMemberId(rs.getString("member_id"));
				i.setStoreId(rs.getInt("store_id"));
				i.setBookingDate(rs.getTimestamp("booking_date"));
				i.setPeople(rs.getInt("people"));
				i.setState(rs.getInt("state"));
				i.setRuleId(rs.getInt("rule_id"));
				i.setTime(rs.getTimestamp("time"));
				i.setNotice(rs.getString("notice"));
				list.add(i);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("선택된 날짜의 예약 리스트 가져오기" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// 예약을 취소시키기
	public synchronized boolean cancelBooking(int bookingId) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("update booking set state = 1 where booking_id = ?");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, bookingId);
			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약 취소하기 오류" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}

	// 선택된 날짜의 예약 리스트 가져오기 (회원)
	public synchronized ArrayList<BookingDTO> getBookingDTOList(int storeId, Timestamp date, int people)
			throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append("select * from( ");
			query.append(
					"select r.id as rule_id, r.time, (r.count - count(b.booking_id)) as team, r.notice, r.min_people, r.max_people, count(c.id) as close ");
			query.append("from booking_rule as r left join ( ");
			query.append(
					"select * from booking where datediff(booking_date,?) = 0 and state = 0) as b on r.id = b.rule_id "); // date
			query.append("left join close_booking as c on r.id = c.rule_id and datediff(c.date, ?) = 0 ");
			query.append("where r.store_id = ? and r.min_people <= ? and r.max_people >= ?  "); // storeId
			query.append(")as asdf where rule_id is not null ");
			// date
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setTimestamp(1, date);
			pstmt.setTimestamp(2, date);
			pstmt.setInt(3, storeId);
			pstmt.setInt(4, people);
			pstmt.setInt(5, people);
			rs = pstmt.executeQuery();

			ArrayList<BookingDTO> list = new ArrayList<BookingDTO>();
			while (rs.next()) {
				BookingDTO i = new BookingDTO();
				i.setRuleId(rs.getInt("rule_id"));
				i.setTime(rs.getTimestamp("time"));
				i.setTeam(rs.getInt("team"));
				i.setNotice(rs.getString("notice"));
				i.setMinPeople(rs.getInt("min_people"));
				i.setMaxPeople(rs.getInt("max_people"));
				i.setClose(rs.getInt("close"));
				list.add(i);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("선택된 날짜의 예약 리스트 가져오기" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}

	// 예약하기
	public synchronized boolean addBooking(BookingDTO dto) throws SQLException {
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();

			query.append(
					"insert into booking (member_id, store_id, booking_date, people, state, rule_id, timestamp, time) select ?,?,?,?,?,?,? ");
			query.append(
					"from dual where not exists ( select id from close_booking where date = ? and rule_id = ? ) "); // 예약일,
																														// rule_id
			query.append(
					"and not exists (select * from ( select count(booking_id) as cnt from booking where rule_id = ? and booking_date = ? and state = 0) as temp ");
			// rule_id, 예약일
			query.append("where cnt >= ( select count from booking_rule where id = ? ))"); // rule_id
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, dto.getMemberId());
			pstmt.setInt(2, dto.getStoreId());
			pstmt.setTimestamp(3, dto.getBookingDate());
			pstmt.setInt(4, dto.getPeople());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, dto.getRuleId());

			// 현재시간 timestamp
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp ts = Timestamp.valueOf(today);
			
			pstmt.setTimestamp(7, ts);
			pstmt.setTimestamp(8, dto.getTime());
			pstmt.setTimestamp(9, dto.getBookingDate());
			pstmt.setInt(10, dto.getRuleId());
			pstmt.setInt(11, dto.getRuleId());
			pstmt.setTimestamp(12, dto.getBookingDate());
			pstmt.setInt(13, dto.getRuleId());

			pstmt.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예약하기" + e.getMessage());
			return false;
		} finally {
			disconnectPstmt();
		}
	}
	
	//내 예약 목록을 페이징해서 가져오기
	public synchronized ArrayList<BookingDTO> getMyBookingList(int page, int pageCount, String memberId) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			query.append("select * from (");
			query.append("select b.store.id, b.booking_date, b.people, b.state, b.time, rownum() order(order by b.timestamp) as num, st.store_name "); 
			query.append("from booking as b inner join store as st on b.store_id = st.store_id where b.member_id = ? ");//memberId
			query.append(") as temp where num ? between ?");
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, (page - 1) * pageCount + 1);
			pstmt.setInt(3, (page) * pageCount);
			
			rs = pstmt.executeQuery();
			
			ArrayList<BookingDTO> list = new ArrayList<BookingDTO>();
			
			while(rs.next()) {
				BookingDTO i = new BookingDTO();
				i.setStoreId(rs.getInt("store_id"));
				i.setBookingDate(rs.getTimestamp("booking_date"));
				i.setPeople(rs.getInt("people"));
				i.setState(rs.getInt("state"));
				i.setTime(rs.getTimestamp("time"));
				i.setStoreName(rs.getString("store_name"));
				list.add(i);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("내 예약 목록을 페이징해서 가져오기" + e.getMessage());
			return null;
		} finally {
			disconnectPstmt();
		}
	}
	
	//내 예약목록 갯수 가져오기
	public synchronized int getMyBookingListCount(int page, int pageCount, String memberId) throws SQLException{
		try {
			conn = dbConn.getConn();
			query = new StringBuffer();
			
			query.append("select count(*) as cnt from (");
			query.append("select b.store.id, b.booking_date, b.people, b.state, b.time, rownum() order(order by b.timestamp) as num "); 
			query.append("from booking as b inner join store as st on b.store_id = st.store_id where b.member_id = ? ");//memberId
			query.append(") as temp");
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, (page - 1) * pageCount + 1);
			pstmt.setInt(3, (page) * pageCount);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			return cnt;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("내 예약목록 갯수 가져오기" + e.getMessage());
			return 0;
		} finally {
			disconnectPstmt();
		}
	}
}
