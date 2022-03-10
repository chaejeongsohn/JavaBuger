package service;

import dao.CouponDAO;
import dao.CouponDAOImpl;
import dao.UserCouponDAO;
import dao.UserCouponDAOImpl;
import dto.UserCoupon;
import exception.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class UserCouponService {

    UserCouponDAO userCouponDAO = new UserCouponDAOImpl();
    CouponDAO couponDAO = new CouponDAOImpl();

    public void insertUserCoupon(UserCoupon userCoupon) throws SQLException, NotFoundException {
        int result = userCouponDAO.insertUserCoupon(userCoupon);
        if (result == 0) throw new SQLException("쿠폰이 등록되지 않았습니다.");
    }

    public void deleteUserCoupon(int couponNumber) throws SQLException {
        int result = userCouponDAO.deleteUserCoupon(couponNumber);
        if (result == 0) throw new SQLException("쿠폰이 삭제되지 않았습니다.");
    }


    /*한장 사용하는거 */
    public int deleteUserCoupon2(Connection con, int couponNumber) throws SQLException {
        int result=userCouponDAO.deleteUserCoupon2(con,couponNumber);
        //if (result == 0) throw new SQLException("쿠폰이 삭제되지 않았습니다.");
        return result;
    }


    public UserCoupon selectUSerCouponByNumber(String userId, int couponNumber) throws SQLException {
        UserCoupon userCoupon = userCouponDAO.selectUserCouponByNumber(userId, couponNumber);
        if (userCoupon == null) {
            throw new SQLException("쿠폰[ " + couponNumber + " ]을 가지고 있지 않습니다.");
        }
        return userCoupon;
    }

    public List<UserCoupon> selectUserCoupons(String userId) throws SQLException {
        List<UserCoupon> userCouponList = checkCouponListExpiration(userCouponDAO.selectUserCoupons(userId));
        if(userCouponList.isEmpty() || userCouponList.size()==0){
            throw new SQLException("적용할 쿠폰이 없습니다.");
        }
        return userCouponList;
    }

    /**
     * 쿠폰List 기한만료 체크
     * @param userCouponList
     * @return
     * @throws SQLException
     */
    public List<UserCoupon> checkCouponListExpiration(List<UserCoupon> userCouponList) throws SQLException {
        List<UserCoupon> userCouponListOfCheckExpiration = new ArrayList<>();
        for (UserCoupon userCoupon : userCouponList) {
            boolean checkResult = checkCouponExpiration(userCoupon);
            if (checkResult) {
                userCouponListOfCheckExpiration.add(userCoupon);
            }
        }
        return userCouponListOfCheckExpiration;
    }

    /**
     * 쿠폰 하나당 기한만료 체크
     * @param userCoupon
     * @return
     * @throws SQLException
     */
    public boolean checkCouponExpiration(UserCoupon userCoupon) throws SQLException {
        int userCouponExpiration = couponDAO.selectCouponByNumber(userCoupon.getCouponNumber()).getCouponExpiration();
        boolean checkResult = compareDate(userCouponExpiration);
        if (!checkResult) {
            System.out.println("[ 쿠폰번호 " + userCoupon.getCouponNumber() + " ] 이 기한만료로 삭제되었습니다.");
            deleteUserCoupon(userCoupon.getCouponNumber());
            return false;
        }
        return true;
    }


    /*쿠폰 만료기한 현재날짜랑 비교 메소드*/
    public boolean compareDate(int checkDate) {

        LocalDate today = LocalDate.now();
        String asStringDate = String.valueOf(checkDate);
        LocalDate date = LocalDate.parse(asStringDate, DateTimeFormatter.BASIC_ISO_DATE);

        int result = today.compareTo(date);
        if (result < 0) {
            return true;
        }
        return false;


    	/*작동잘되면 삭제할것
    	 * String todayFormat = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    	Date today = new Date(dateFormat.parse(todayFormat).getTime());
    	Date date = new Date(dateFormat.parse(chechDate).getTime());*/


    }

    /*쿠폰 날짜형식 확인 메소드*/
    public boolean validationDate(String checkDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            dateFormat.setLenient(false);
            dateFormat.parse(checkDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


}
