package D02_19.db_example;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

// DB와 직접 연결되어 데이터를 CRUD하는 클래스
public class ObjectDB {

    Connection con;
    PreparedStatement stmt;

    // manager에서 생성자를 인스턴스로 선언하여 DB 연결
    public ObjectDB() {
        run();
    }

    public void run() {
        try {
            // 드라이버 로딩
            // 드라이버 인터페이스를 구현한 클래스를 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");

            // 드라이버 연결
            // 드라이버 매니저에게 Connection 객체를 달라고 요청
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false", "root", "1234");
            con.setAutoCommit(false);   // 자동으로 commit하는 것을 false로 설정(프로그래머가 직접 commit 해줘야함)
            System.out.println("데이터베이스 연결 성공");
        } catch (Exception e) {
            System.out.println("[연결 오류] " + e.getMessage());
        }
    }
    
    /*
        <Statement 종류>
        1. Statement
            - 기본 객체
            - 텍스트 SQL 호출
        2. PreparedStatement  => 많이 사용
            - 1번 객체 기능 향상
            - 매개변수와 관련된 작업 특화
            - 코드 안정성과 가독성 높음
            - 코드량 증가
            - 텍스트 SQL 호출
        3. CallableStatement
            - 2번 base
            - 프로시저 호출 전용
     */

    // DB에 데이터 추가
    public boolean insert(int id, String name) {
        try {
            // 1) SQL 쿼리문을 String으로 작성
            String insert_sql = "insert into exam(id, name) values(?, ?);";

            // 2) SQL 실행
            stmt = con.prepareStatement(insert_sql);

            // 3) 쿼리문 안에 '?'로 매개변수 값 대입
            // - 필드에 대한 데이터 타입을 확인하고 대입해야 한다.
            stmt.setInt(1, id);
            stmt.setString(2, name);

            // 4) 정적 쿼리를 실행할 때 사용
            // - 보통 insert, update, delete에 사용한다.
            int i = stmt.executeUpdate();

            stmt.close();

            if (i > 0) {
                con.commit();
                return true;
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {

        try {
            String delete_sql = "delete from exam where id = ?;";
            stmt = con.prepareStatement(delete_sql);

            stmt.setInt(1, id);

            int i = stmt.executeUpdate();

            stmt.close();

            if (i > 0) {
                con.commit();
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(int id, String name) {
        try {
            String update_sql = "update exam set name = ? where id = ?;";
            stmt = con.prepareStatement(update_sql);

            stmt.setString(1, name);
            stmt.setInt(2, id);

            int i = stmt.executeUpdate();

            if (i > 0) {
                con.commit();
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Object select(int id) {
        try {
            String select_sql = "select * from exam where id = ?;";
            stmt = con.prepareStatement(select_sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            // 1) 결과가 단일 컬럼일 경우
            rs.next();
            int a = rs.getInt(1);
            String b = rs.getString(2);
            Timestamp c = rs.getTimestamp(3, Calendar.getInstance());

            /*
            // 2) 결과가 다중 컬럼일 경우
            while (rs.next()) {
                int c = rs.getInt(1);
                String d = rs.getString(2);

                // 보통 객체를 생성해 ArrayList에 추가하여 return한다.
                // ArrayList<Member> memlist = new ArrayList<>();
                // memlist.add(new Member(a(or c), b(or d)));
                // return memlist;


                stmt.close();
                return c + "#" + d + "@"; // 또는 문자열로 역직렬화하여 반환처리할 수 있다.
            }
            */

            stmt.close();
            return a + "#" + b + "#" + c + "@";

        } catch (Exception e) {
            return null;
        }
    }

}


