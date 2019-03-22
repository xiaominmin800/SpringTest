package example;


import baseinfo.DBconn;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static baseinfo.DesCbcSecurity.decrypt;
import static baseinfo.DesCbcSecurity.encrypt;
/**
 * created by chenminqing
 */
@Slf4j
public class getencryptinfo {

    public static void main(String [] args) {
        String aa="49FF4CE308466CF82BEB6A14C9617FACD9239E2C689DD6EE40500BABE8B39959B3DA6FC9E44BAE99C3397EE56E3ADD58ACD2B68C501C6D10BEDB4AFF9CC0F7427A3EAFEE9C02A8522415DA6D6488AE042F00876DBB058F8E8742CCE98B8B3B1CC044176D0C2FC121CA889A933358D1A612A9D115FAD76614319D97CA37B4277E4133DFD51D2E9281C93C0A1A65AD1F3120183AEE1B8036C3661640CACB3B4DD1";
        String signs = decrypt(aa, "CmRzosfz");


        List<Map<String, String>> userinfo =new ArrayList();
        userinfo = getencryptinfo.getdb("select *from eaglehorn_model.t_user_info where id<10");
        String customerId="LZF1804101641";
        String customerProdId="PROD1808243084456019866";
        String key="zKpvm01D";
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
                fileOutputStream.write(userinfosign.getBytes(),0,userinfosign.getBytes().length);
                fileOutputStream.write("\r\n".getBytes());

            }
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.print("Exception");
        }
        /**
         * fileInputStream read 方法
         */
        try {

            File file =new File("Test.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            int len ;
            byte[] b = new byte[100];
            while (-1!=(len = fileInputStream.read(b))) {
                System.out.print(new String(b,0,len));
            }

            fileInputStream.close();

        } catch (Exception e) {
            System.out.print("Exception");
        }

        String name ;
        String idcard;
        String phone;
        try {
            File file =new File("Test.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fis,"UTF-8"); //最后的"GBK"根据文件属性而定，如果不行，改成"UTF-8"试试
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
            //    System.out.println(line);
                String [ ] user=line.split(",");

                    name = user[0];
                    idcard =user[1];
                    phone = user[2];
                    System.out.println("用户："+name+" 的身份证是："+idcard +" 的手机号是："+phone);

            }
            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
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


