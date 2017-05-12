package src.extrapackage;

import src.extrapackage.Data;
import java.util.ArrayList;
import java.util.List;
/**
 * KNN
 */
public class KNN {
    private static final KNN instance = new KNN();
    private ArrayList<Data> dataSets = new ArrayList<Data>();
    private Boolean isTrain = true;

    public static KNN getInstance () {
        return KNN.getInstance(true);
    }

    public static KNN getInstance (Boolean train) {
        KNN.instance.setTrain(train);
        return KNN.instance;
    }

    private KNN() {
    }

    private void insertData(Data d) {
        this.dataSets.add(d);
    }

    private void setTrain(Boolean isTrain) {
        this.isTrain = isTrain;
    }

    public Boolean isTrainData() {
        return this.isTrain;
    }

    private void insertDataFromString(String d) {
        this.dataSets.add(new Data(d), this.isTrain);
    }

    private static Data pending = null;
    private static Double wrost = Double.MAX_VALUE;
    public static void setPendingData(Data o) {
        KNN.pending = o;
    }

    private void calculateEveryDistance(Data o) {
        KNN.setPendingData(o);
        this.dataSets.stream().map(Data.calculateDistanceWithPending);
        this.dataSets.sort((d1,d2) -> d1.compareTo(d2));
    }

    private List<Data> getNearest(int k, List<Data> trainSet) {
        List<Data> Nearest = new ArrayList<>();
        int i = 0
        for (; i < k; i++) {
            if (trainSet.get(i).getDistance() < wrost)
                Nearest.add(trainSet.get(i));
        }
        KNN.wrost = this.dataSets.get(i).getDistance();
        return Nearest;
    }
}