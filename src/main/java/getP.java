import org.springframework.boot.SpringApplication;

/**
 * created by chenminqing
 */
public class getP {
    public static void main(String[] args) throws Exception {
        double []temp={0.1,1};
        double []X = {0.1};
        double e = Math.E;
        double z= getP.getz(temp,X);
        double P = 1/(1+(Math.pow(e,z)));
        System.out.println("得到的结果P为："+P);
    }


    public static double getz(double [] temp,double [] x){
        double Z;

        int size = temp.length;
        Z = temp[0];
        for(int i=1; i<size; i++){
            Z = Z+ temp[i] * x[i-1] ;
        }
        Z = 0 - Z;
        return Z;
    }
}
