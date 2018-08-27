package example;


import baseinfo.DBconn;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static baseinfo.DesCbcSecurity.encrypt;
/**
 * created by chenminqing
 */
@Slf4j
public class getencryptinfo {

    public static void main(String [] args) {
        List<Map<String, String>> userinfo =new ArrayList();
        userinfo = getencryptinfo.getdb("select *from eaglehorn_model.t_user_info where id<20");
        String customerId="CTXD1808231504";
        String customerProdId="PROD1808235070719139629";
        String key="ltxZiXwA";
        String timestamp ="1513389389346";
        String bbbString ="";
        String sign="";
        String userinfosign="";
        try {

            File file =new File("Test.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (Map<String,String> temp : userinfo ) {
                bbbString = "customerId=" + customerId + "&customerProdId=" + customerProdId + "&name=" + temp.get("name") + "&mobile=" + temp.get("mobile") + "&idCardNo=" + temp.get("id_card_no") + "&timestamp=" + timestamp;
                sign = encrypt(bbbString, key);
                userinfosign = temp.get("name") + "," + temp.get("id_card_no") + "," + temp.get("mobile") + "," + sign ;
                fileOutputStream.write(userinfosign.getBytes(),0,userinfosign.length());
                fileOutputStream.write("\r\n".getBytes());

            }
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.print("Exception");
        }
}
    /**
     * 获取数据库返回结果
     * @param sql
     * @return
     */
    static private List<Map<String, String>> getdb( String sql){
        Connection conn = DBconn.getegCon();
        PreparedStatement pst = null;

        // 定义一个list用于接受数据库查询到的内容
        List<Map<String, String>> list = new ArrayList();

        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Map<String, String> userinfo = new HashMap<String, String>();
                userinfo.put("name",rs.getString("name"));
                userinfo.put("id_card_no",rs.getString("id_card_no"));
                userinfo.put("mobile",rs.getString("mobile"));
                list.add(userinfo);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {

        }
        return list;
    }

}


