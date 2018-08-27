package example;

import lombok.extern.slf4j.Slf4j;

/**
 * created by chenminqing
 */
@Slf4j
public class getP {
    public static void main(String[] args) throws Exception {
        double []temp={0.11,0.22,0.33,0.44,0.55};
        double []X = {0.55,0.77,1.33,1.55};
        double e = Math.E;
        double z= getP.getz(temp,X);
        double P = 1/(1+(Math.pow(e,z)));
        log.info("得到的结果P为："+P);
        log.error("得到的结果P为："+P);
        log.debug("得到的结果P为："+P);
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
