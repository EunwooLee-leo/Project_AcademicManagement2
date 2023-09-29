package Management.prof;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import static Management.prof.ProfForm.table;
import static Management.prof.ProfForm.tableModel;

public class UserDAO {

    private Connection c; // DB에 접근하게 해주는 객체

    public Connection getC() {
        return c;
    }

    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2; // 정보를 담을 객체

    UserDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/univ";
            String dbID = "root";
            String dbPassword = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deptInfoAll() {
        try {
            String query = "SELECT * FROM dept ";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 결과가 있는 경우
            while (resultSet.next()) {
                String deptCode = resultSet.getString("deptCode");
                String deptName = resultSet.getString("deptName");
                String majorName = resultSet.getString("majorName");

                tableModel.addRow(new Object[]{deptCode, deptName, majorName});
            }
            // PreparedStatement와 ResultSet를 닫습니다.
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void profInfoAll() {
        try {
            String query = "SELECT * FROM prof ";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 결과가 있는 경우
            while (resultSet.next()) {
                String profCode = resultSet.getString("profCode");
                String profName = resultSet.getString("profName");
                String addr = resultSet.getString("addr");
                String rrn = resultSet.getString("rrn");
                String phone = resultSet.getString("phone");
                String year = resultSet.getString("year");
                String degree = resultSet.getString("degree");
                String majorName = resultSet.getString("majorName");
                String room = resultSet.getString("room");
                String sex = resultSet.getString("sex");

                tableModel.addRow(new Object[]{profCode, profName, addr, rrn, phone, year, degree, majorName, room, sex});
            }
            // PreparedStatement와 ResultSet를 닫습니다.
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deptDataInsert(String deptCode, String deptName, String majorName) {
        try {
            String query = "INSERT INTO dept (deptCode, deptName, majorName) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, deptCode);
            preparedStatement.setString(2, deptName);
            preparedStatement.setString(3, majorName);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "데이터 삽입 실패.");
            }

            preparedStatement.close();

            // 테이블 모델 초기화
            tableModel.setRowCount(0);

            // 업데이트된 데이터로 테이블 모델 채우기
            deptInfoAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void profDataInsert(String profCode, String profName, String addr, String rrn, String phone, String year, String degree, String majorName, String room, String sex) {
        try {
            String query = "INSERT INTO prof (profCode, profName, addr, rrn, phone, year, degree, majorName, room, sex) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, profCode);
            preparedStatement.setString(2, profName);
            preparedStatement.setString(3, addr);
            preparedStatement.setString(4, rrn);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, year);
            preparedStatement.setString(7, degree);
            preparedStatement.setString(8, majorName);
            preparedStatement.setString(9, room);
            preparedStatement.setString(10, sex);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "데이터 삽입 실패.");
            }

            preparedStatement.close();

            // 테이블 모델 초기화
            tableModel.setRowCount(0);

            // 업데이트된 데이터로 테이블 모델 채우기
            profInfoAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void categorySearchByDeptName(String deptName) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            String query = "SELECT deptCode, deptName, majorName FROM dept WHERE deptName LIKE ? or deptName = ? or deptName LIKE ?";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, "%" + deptName);
            preparedStatement.setString(2, deptName);
            preparedStatement.setString(3, deptName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            // 결과가 있는 경우
            while (resultSet.next()) {
                String deptCode = resultSet.getString("deptCode");
                String foundDeptName = resultSet.getString("deptName");
                String majorName = resultSet.getString("majorName");

                model.addRow(new Object[]{deptCode, foundDeptName, majorName});
            }
            // PreparedStatement와 ResultSet를 닫습니다.
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void categorySearchByProfName(String profName) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            String query = "SELECT profCode, profName, addr, rrn, phone, year, degree, majorName, room, sex FROM prof WHERE profName LIKE ? or profName = ? or profName LIKE ?";
            PreparedStatement preparedStatement = c.prepareStatement(query);
            preparedStatement.setString(1, "%" + profName);
            preparedStatement.setString(2, profName);
            preparedStatement.setString(3, profName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            // 결과가 있는 경우
            while (resultSet.next()) {
                String profCode = resultSet.getString("profCode");
                String foundProfName = resultSet.getString("profName");
                String addr = resultSet.getString("addr");
                String rrn = resultSet.getString("rrn");
                String phone = resultSet.getString("phone");
                String year = resultSet.getString("year");
                String degree = resultSet.getString("degree");
                String majorName = resultSet.getString("majorName");
                String room = resultSet.getString("room");
                String sex = resultSet.getString("sex");


                model.addRow(new Object[]{profCode, foundProfName, addr, rrn, phone, year, degree, majorName, room, sex});
            }
            // PreparedStatement와 ResultSet를 닫습니다.
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void categorySearchBySubjectName() {

        try {
            String query = "SELECT deptName  FROM dept ";
            PreparedStatement preparedStatement = c.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            // 결과가 있는 경우
            while (resultSet.next()) {
                String deptName = resultSet.getString("deptName");

                ProfForm.comboBox2.addItem(deptName);
            }
            // PreparedStatement와 ResultSet를 닫습니다.
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDeptInfo(String deptCodeToUpdate, String deptNameToUpdate, String majorNameToUpdate) {
        try {
            // SQL UPDATE 쿼리 작성
            String updateQuery = "UPDATE dept SET deptName = ?, majorName = ? WHERE deptCode = ?";

            PreparedStatement preparedStatement = c.prepareStatement(updateQuery);

            // 파라미터 설정
            preparedStatement.setString(1, deptNameToUpdate);
            preparedStatement.setString(2, majorNameToUpdate);
            preparedStatement.setString(3, deptCodeToUpdate);

            // UPDATE 쿼리 실행
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(null, "데이터가 성공적으로 업데이트되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "업데이트할 데이터가 없습니다.");
            }

            // PreparedStatement와 연결 닫기
            preparedStatement.close();

            // 테이블 모델 초기화
            tableModel.setRowCount(0);

            // 업데이트된 데이터로 테이블 모델 채우기
            deptInfoAll();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    public void updateProfInfo(String profCode,String profName,String addr, String rrn,String phone,String year,String degree,String majorName, String room, String sex) {
        try {
            // SQL UPDATE 쿼리 작성
            String updateQuery = "UPDATE prof SET profCode = ?, profName = ?, addr = ?, rrn = ?, phone = ?, year = ?, degree = ?, majorName = ?, room = ?, sex = ? WHERE  profCode = ?";

            PreparedStatement preparedStatement = c.prepareStatement(updateQuery);

            // 파라미터 설정
            preparedStatement.setString(1, profCode);
            preparedStatement.setString(2, profName);
            preparedStatement.setString(3, addr);
            preparedStatement.setString(4, rrn);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, year);
            preparedStatement.setString(7, degree);
            preparedStatement.setString(8, majorName);
            preparedStatement.setString(9, room);
            preparedStatement.setString(10, sex);
            preparedStatement.setString(11, profCode);

            // UPDATE 쿼리 실행
            int updatedRows = preparedStatement.executeUpdate();

            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(null, "데이터가 성공적으로 업데이트되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "업데이트할 데이터가 없습니다.");
            }

            // PreparedStatement와 연결 닫기
            preparedStatement.close();

            // 테이블 모델 초기화
            tableModel.setRowCount(0);

            // 업데이트된 데이터로 테이블 모델 채우기
            profInfoAll();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    public void deleteDeptInfo(String deptCodeToDelete, String deptNameToDelete, String majorNameToDelete) {
        try {
            // SQL DELETE 쿼리 작성
            String deleteQuery = "DELETE FROM dept WHERE deptCode = ? AND deptName = ? AND majorName = ?";

            PreparedStatement preparedStatement = c.prepareStatement(deleteQuery);

            // 파라미터 설정
            preparedStatement.setString(1, deptCodeToDelete);
            preparedStatement.setString(2, deptNameToDelete);
            preparedStatement.setString(3, majorNameToDelete);

            // DELETE 쿼리 실행
            int deletedRows = preparedStatement.executeUpdate();

            if (deletedRows > 0) {
                JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삭제되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "삭제할 데이터가 없습니다.");
                ;
            }

            // PreparedStatement와 연결 닫기
            preparedStatement.close();

            tableModel.setRowCount(0);

            // 업데이트된 데이터로 테이블 모델 채우기
            deptInfoAll();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터 삭제 중 오류 발생: " + e.getMessage());
            ;
        }
    }

        public void deleteProfInfo (String profCode, String profName, String phone) {
            try {
                // SQL DELETE 쿼리 작성
                String deleteQuery = "DELETE FROM prof WHERE profCode = ? AND profName = ? AND phone = ?";

                PreparedStatement preparedStatement = c.prepareStatement(deleteQuery);

                // 파라미터 설정
                preparedStatement.setString(1, profCode);
                preparedStatement.setString(2, profName);
                preparedStatement.setString(3, phone);

                // DELETE 쿼리 실행
                int deletedRows = preparedStatement.executeUpdate();

                if (deletedRows > 0) {
                    JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삭제되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(null, "삭제할 데이터가 없습니다.");
                    ;
                }

                // PreparedStatement와 연결 닫기
                preparedStatement.close();

                tableModel.setRowCount(0);

                // 업데이트된 데이터로 테이블 모델 채우기
                profInfoAll();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "데이터 삭제 중 오류 발생: " + e.getMessage());
                ;
            }
        }



}