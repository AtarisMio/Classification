package src;

import src.extrapackage.Data;
import src.extrapackage.KNN;
/**
 * MyClassifier
 */
public class MyClassifier {

    public MyClassifier (String parameters) {
        System.out.println("hello world");
    }
    public static void main(String[] args) {
        System.out.println("hello world");
        Data a = new Data("0.352941,0.670968,0.489796,0.304348,0.169471,0.314928,0.234415,0.483333,yes", true);
        a.calculateDistance();
        System.out.println(a.getOriginData());
        System.out.println(a.getDistance());
        
    }
}