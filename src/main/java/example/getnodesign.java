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
public class getnodesign {

    public static void main(String [] args) {
        List<Map<String, String>> userinfo =new ArrayList();
        userinfo = getnodesign.getdb();
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
            BufferedWriter fileOutputStream = new BufferedWriter(new FileWriter(file,true));//true,则追加写入text文本
            //FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (Map<String,String> temp : userinfo ) {
                bbbString = "customerId=" + customerId + "&customerProdId=" + customerProdId + "&name=" + temp.get("name") + "&mobile=" + temp.get("mobile") + "&idCardNo=" + temp.get("id_card_no") + "&timestamp=" + timestamp;
                sign = encrypt(bbbString, key);
                userinfosign = temp.get("name") + "," + temp.get("id_card_no") + "," + temp.get("mobile") + "," + sign ;
                fileOutputStream.write(userinfosign);
                fileOutputStream.write("\r\n");

//                fileOutputStream.write(userinfosign.getBytes(),0,userinfosign.getBytes().length);
//                fileOutputStream.write("\r\n".getBytes());

            }
            fileOutputStream.close();

        } catch (Exception e) {
            System.out.print("Exception");
        }


    }
    /**
     * 获取数据库返回结果
     * @return
     */
    static private List<Map<String, String>> getdb(){

        List<Map<String, String>> list = new ArrayList();

        String name ;
        String idcard;
        String phone;
        try {
            File file =new File("Test1.txt");
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fis,"UTF-8"); //最后的"GBK"根据文件属性而定，如果不行，改成"UTF-8"试试
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                String [ ] user=line.split(",");
                name = user[0];
                idcard =user[1];
                phone = user[2];
                Map<String, String> userinfo = new HashMap<String, String>();
                userinfo.put("name",name);
                userinfo.put("id_card_no",idcard);
                userinfo.put("mobile",phone);
                list.add(userinfo);

            }
            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}


