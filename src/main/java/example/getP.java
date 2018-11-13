package example;

import lombok.extern.slf4j.Slf4j;

/**
 * created by chenminqing
 */
@Slf4j
public class getP {
    public static void main(String[] args) throws Exception {
        double []temp={
                -1.2684,
                0.6905841,
                0.7841144,
                0.6099274,
                0.6451429,
                0.5771117,
                0.9042354,
                0.9336458,
                0.724956,
                1.700626,
                0.416198,
                -2.7355376
                    };
        double []X = {
                0.31,
                0.31,
                -0.19,
                0.29,
                0.35,
                -0.23,
                -0.09,
                -0.11,
                0.07,
                -0.19,
                0.1
        };
        double e = Math.E;
        double z= getP.getz(temp,X);
        double P = 1/(1+(Math.pow(e,z)));
        System.out.println("得到的结果P为："+P);
        log.info("得到的结果P为："+P);
    }


    public static double getz(double [] temp,double [] x){
        double Z;

        int size = temp.length;
        Z = temp[0];
        for(int i=1; i<size; i++){
            Z = Z+ (temp[i] * x[i-1]) ;
        }
        Z = 0 - Z;
        return Z;
    }
}
