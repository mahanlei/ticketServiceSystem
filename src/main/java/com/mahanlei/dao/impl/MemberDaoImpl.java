package com.mahanlei.dao.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.MemberDao;
import com.mahanlei.model.DiscountCoupon;
import com.mahanlei.model.MemberInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoImpl  implements MemberDao {
    private static MemberDaoImpl memberDao = new MemberDaoImpl();

    public static MemberDaoImpl getInstance() {
        return memberDao;
    }

    DaoHelper daoHelper = new DaoHelperImpl();

    public Message addMember(MemberInfo memberInfo, String password) {
        Connection connection = daoHelper.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        PreparedStatement statement4 = null;
        ResultSet resultSet = null;
        try {
            statement1 = connection.prepareStatement("SELECT * from memberinfo WHERE mid=?");
            statement1.setString(1, memberInfo.getMid());
            resultSet = statement1.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet.next()) {
                return Message.REGISTER_FAILED;
            } else {//不存在相同用户名，可插入
                try {
                    //存入个人信息
                    statement2 = connection.prepareStatement("insert INTO memberinfo VALUES (?,?,?,?,?,?,?)");
                    statement2.setString(1, memberInfo.getMid());
                    statement2.setInt(2, memberInfo.getAge());
                    statement2.setString(3, memberInfo.getEmail());
                    statement2.setInt(4, memberInfo.getRank());
                    statement2.setInt(5, memberInfo.getPoints());
                    statement2.setInt(6, memberInfo.getState());
                    statement2.setString(7, memberInfo.getCode());

                    statement2.executeUpdate();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //存入个人密码
                    statement3 = connection.prepareStatement("INSERT INTO userp VALUES (?,?)");
                    statement3.setString(1, memberInfo.getMid());
                    statement3.setString(2, password);
                    statement3.executeUpdate();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //存入个人优惠券信息
                    statement4 = connection.prepareStatement("INSERT INTO discountcoupon VALUES (?,0,0,0,0,)");
                    statement4.setString(1, memberInfo.getMid());
                    statement4.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                    connection.rollback();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoHelper.closeConnection(connection);
            daoHelper.closePreparedStatement(statement1);
            daoHelper.closePreparedStatement(statement2);
            daoHelper.closePreparedStatement(statement3);
            daoHelper.closeResult(resultSet);
        }
        return Message.REGISTER_SUCCESS;
    }

    public boolean activeMember(String code) {
        int num = 0;
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("update memberinfo set state=1 AND rank=1 WHERE code=?");
            statement.setString(1, code);
            num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoHelper.closeConnection(connection);
            daoHelper.closePreparedStatement(statement);
        }
        return num != 0;
    }

    public MemberInfo getMemberInfo(String mid) {
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int age = 0;
        String email = null;
        int rank = 0;
        int points = 0;
        int state = 0;
        String code = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM memberinfo WHERE  mid=?");
            statement.setString(1, mid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                age = resultSet.getInt("age");
                email = resultSet.getString("email");
                rank = resultSet.getInt("rank");
                points = resultSet.getInt("points");
                state = resultSet.getInt("state");
                code = resultSet.getString("code");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
        MemberInfo memberInfo = new MemberInfo(mid, age, email, rank, points, state, code);
        return memberInfo;

    }

    public Message updateProfile(String mid, int age, int state) {
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            statement = connection.prepareStatement("update memberinfo SET age=?,state=? WHERE mid=? ");
            statement.setInt(1, age);
            statement.setInt(2, state);
            statement.setString(3, mid);
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if (n > 0) {
            return Message.UPDATE_SUCCESS;
        } else return Message.UPDATE_FAILED;

    }

    public DiscountCoupon getDisInfo(String mid) {
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DiscountCoupon discountCoupon = new DiscountCoupon();
        try {
            statement = connection.prepareStatement("SELECT * FROM discountcoupon WHERE mid=?");
            statement.setString(1, mid);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                discountCoupon.setMid(mid);
                discountCoupon.setCount1(resultSet.getInt("count1"));
                discountCoupon.setCount2(resultSet.getInt("count2"));
                discountCoupon.setCount3(resultSet.getInt("count3"));
                discountCoupon.setCount4(resultSet.getInt("count4"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return discountCoupon;
    }

    public Message addDis(DiscountCoupon discountCoupon) {
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        int exchangeType = 0;
        if (discountCoupon.getDiscountCouponType1() == 1) {
            exchangeType = 1;
        } else if (discountCoupon.getDiscountCouponType2() == 1) {
            exchangeType = 2;
        } else if (discountCoupon.getDiscountCouponType3() == 1) {
            exchangeType = 3;
        } else exchangeType = 4;

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            switch (exchangeType) {
                case 1:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count1=count1+1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    statement1 = connection.prepareStatement("UPDATE memberinfo SET points=points-200 WHERE mid=?");
                    statement1.setString(1, discountCoupon.getMid());
                    break;
                case 2:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count2=count2+1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    statement1 = connection.prepareStatement("UPDATE memberinfo SET points=points-300 WHERE mid=?");
                    statement1.setString(1, discountCoupon.getMid());
                    break;
                case 3:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count3=count3+1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    statement1 = connection.prepareStatement("UPDATE memberinfo SET points=points-400 WHERE mid=?");
                    statement1.setString(1, discountCoupon.getMid());
                    break;
                case 4:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count4=count4+1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    statement1 = connection.prepareStatement("UPDATE memberinfo SET points=points-500 WHERE mid=?");
                    statement1.setString(1, discountCoupon.getMid());
                    break;


            }
            statement.executeUpdate();
            statement1.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                return Message.EXCHANGE_DISCOUNT_FAILED;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return Message.EXCHANGE_DISCOUNT_SUCCESS;

    }

    public Message removeDis(DiscountCoupon discountCoupon) {
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        int useType = 0;
        if (discountCoupon.getDiscountCouponType1() == 1) {
            useType = 1;
        } else if (discountCoupon.getDiscountCouponType2() == 1) {
            useType = 2;
        } else if (discountCoupon.getDiscountCouponType3() == 1) {
            useType = 3;
        } else useType = 4;
        try {
            switch (useType) {
                case 1:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count1=count1-1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    break;
                case 2:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count2=count2-1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    break;
                case 3:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count3=count3-1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    break;
                case 4:
                    statement = connection.prepareStatement("UPDATE discountcoupon SET count4=count4-1 WHERE mid=?");
                    statement.setString(1, discountCoupon.getMid());
                    break;
            }
            n = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if (n > 0) {
            return Message.USE_DISCOUNT_SUCCESS;
        } else return Message.USE_DISCOUNT_FAILED;
    }

    public Message updateMemRank(String mid, int rank) {
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;
        int n = 0;
        try {
            statement = connection.prepareStatement("UPDATE memberinfo SET rank=? WHERE mid=?");
            statement.setInt(1, rank);
            statement.setString(2, mid);
            n = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if (n > 0) {
            return Message.UPDATE_SUCCESS;
        } else return Message.UPDATE_FAILED;
    }

    public double getConsumption(String mid) {
        Connection connection = daoHelper.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet=null;
        double consumption=0.0;
        try {
            statement=connection.prepareStatement("SELECT consumption FROM memberbill WHERE mid=?");
            statement.setString(1,mid);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                consumption=resultSet.getDouble("consumption");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return consumption;
    }
}