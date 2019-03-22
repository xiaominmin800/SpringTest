package example;

/**
 * created by chenminqing
 */
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.springframework.util.StringUtils;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class RemoteExecuteCommand {
    //字符编码默认是utf-8
    private static String  DEFAULTCHART="UTF-8";
    private static Connection conn;
    private String ip;
    private String userName;
    private String userPwd;

    public RemoteExecuteCommand(String ip, String userName, String userPwd) {
        this.ip = ip;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public RemoteExecuteCommand() {

    }

    /**
     * 远程登录linux的主机
     * @return
     * 登录成功返回true，否则返回false
     */
    public Boolean login(){
        boolean flg=false;
        try {
            conn = new Connection(ip);
            conn.connect();//连接
            flg=conn.authenticateWithPassword(userName, userPwd);//认证
            if (flg){
                System.out.println("认证成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }
    /**
     * 远程执行shll脚本或者命令
     * @param cmd
     *      即将执行的命令
     * @return
     *      命令执行完后返回的结果值
     */
    public String execute(String cmd){
        String result="";
        try {
            if(login()){
                Session session= conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result=processStdout(session.getStdout(),DEFAULTCHART);
                //如果为得到标准输出为空，说明脚本执行出错了
                if(StringUtils.isEmpty(result)){
                    result=processStdout(session.getStderr(),DEFAULTCHART);
                }
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 远程执行shll脚本或者命令
     * @param cmd
     *      即将执行的命令
     * @return
     *      命令执行成功后返回的结果值，如果命令执行失败，返回空字符串，不是null
     */
    public String executeSuccess(String cmd){
        String result="";
        try {
            if(login()){
                Session session= conn.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result=processStdout(session.getStdout(),DEFAULTCHART);
                conn.close();
                session.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     * @param in 输入流对象
     * @param charset 编码
     * @return
     */
    public static String processStdout(InputStream in, String charset){
        InputStream    stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout,charset));
            String line=null;
            while((line=br.readLine()) != null){
                buffer.append(line+"\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static List<Map<String,String>> getlogstr(){

        RemoteExecuteCommand rec=new RemoteExecuteCommand("47.97.171.60", "root","1017~Fulin2017");
        List<Map<String, String>> list = new ArrayList();
        //执行命令
        try {
            if(rec.login()){
                Session session2= conn.openSession();//打开一个会话
                //TODO:多条命令
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String point = "gateway-point-"+sdf.format(d).toString()+".log";
                session2.execCommand("cd /home/admin/jars/logs/point;cat "+point);//执行命令
                String result2=processStdout(session2.getStdout(),DEFAULTCHART);
                //如果为得到标准输出为空，说明脚本执行出错了
                if(StringUtils.isEmpty(result2)){
                    System.out.println("脚本出错");
                    result2=processStdout(session2.getStderr(),DEFAULTCHART);
                }
                String[] strs=result2.split("\n");

                String log[] = new String[strs.length];
                for(int i=0,len=log.length;i<len;i++){
                    System.out.println(strs[i].toString());
                    log[i] = strs[i].toString();
                    String[] str=log[i].split("\\|");
                    Map<String, String> parm = new HashMap<String, String>();
                    parm.put("order",str[8]);
                    parm.put("name",str[10]);
                    parm.put("idCard",str[11]);
                    parm.put("phone",str[12]);
                    parm.put("Cache",str[13]);
                    parm.put("source",str[5]);
                    list.add(parm);
                }
                session2.close();


                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {

        List<Map<String, String>> list = new ArrayList();
        list = RemoteExecuteCommand.getlogstr();
        System.out.println(list);

    }



    public static void setCharset(String charset) {
        DEFAULTCHART = charset;
    }
    public Connection getConn() {
        return conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}

