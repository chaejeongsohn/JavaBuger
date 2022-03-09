package dao;

import dto.Coupon;
import exception.AddException;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CouponDAOImpl implements CouponDAO {
    private Properties proFile = DbUtils.getProFile();


    @Override
    public int insertCoupon(Coupon coupon) throws SQLException, AddException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into coupon values(?,?,?,?)";
        //String sql = proFile.getProperty("coupon.insert");
        int result = 0;
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);

                ps.setInt(1, coupon.getCouponNumber());
                ps.setString(2, coupon.getCouponDetail());
                ps.setInt(3, coupon.getCouponDiscountRate());
                ps.setInt(4, coupon.getCouponExpiration());

                result = ps.executeUpdate();

        } finally {
            DbUtils.close(con, ps, null);
        }
        return result;
    }

    @Override
    public int deleteCoupon(int couponNumber) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "delete from coupon where coupon_no = ?";
        //String sql = proFile.getProperty("coupon.delete");
        int result = 0;

        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, couponNumber);

            result = ps.executeUpdate();

        } finally {
            DbUtils.close(con, ps, null);
        }
        return result;
    }


    @Override
    public int updateCoupon(Coupon coupon) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update coupon set coupon_detail=?, coupon_dc_rate=?,coupon_exp=? where coupon_no =? ";
        // String sql = proFile.getProperty("coupon.update");
        int result = 0;

        try {
            con = DbUtils.getConnection();

            ps = con.prepareStatement(sql);
            ps.setString(1, coupon.getCouponDetail());
            ps.setInt(2, coupon.getCouponDiscountRate());
            ps.setInt(3, coupon.getCouponExpiration());
            ps.setInt(4, coupon.getCouponNumber());

            result = ps.executeUpdate();

        } finally {
            DbUtils.close(con, ps, null);
        }
        return result;
    }

    @Override
    public List<Coupon> selectCoupons() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Coupon> couponList = new ArrayList<Coupon>();
        String sql = "select*from coupon order by coupon_no";
        //String sql = proFile.getProperty("coupon.selectAll");
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Coupon coupons = new Coupon(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4));
                couponList.add(coupons);
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return couponList;
    }

    @Override
    public Coupon selectCouponByNumber(int CouponNumber) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Coupon coupon = null;
        String sql = "select*from coupon where coupon_no =?";
        //String sql = proFile.getProperty("coupon.selectByNo");
        try {
            con = DbUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, CouponNumber);

            rs = ps.executeQuery();
            if (rs.next()) {
                coupon = new Coupon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return coupon;
    }

}
