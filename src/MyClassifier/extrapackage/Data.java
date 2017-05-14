package MyClassifier.extrapackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ataris on 2017/5/14.
 */
public class Data implements Comparable<Data> {
    private String originData = "";
    private Diabetes isDiabetes = Diabetes.UNSET;
    private Boolean isTrainData = false;
    private List<Double> data = new ArrayList<>();
    private Double distance;

    public String getOriginData () {
        return this.originData;
    }
    public Diabetes getIsDiabetes () {
        return this.isDiabetes;
    }

    public List<Double> getData () {
        return new ArrayList<Double>(this.data);
    }

    public Double getDistance () {
        return this.distance;
    }
    public Data(String originData) {
        new Data(originData, false);
    }
    public Data(String originData, Boolean isTrainData) {
        this.originData = originData;
        this.isTrainData = isTrainData;
        try {
            parseData(this.originData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseData(String origin) throws Exception {
        String[] dataSet = origin.split(",");
        for (String p : dataSet) {
            Double attribute;
            try {
                attribute = Double.parseDouble(p);
                this.data.add(Double.parseDouble(p));
            } catch (Exception e){
                switch (p) {
                    case "yes":
                        this.isDiabetes = Diabetes.YES;
                        break;
                    case "no":
                        this.isDiabetes = Diabetes.NO;
                        break;
                    default:
                        System.out.println(this.originData);
                        throw new Exception("Data is invalid");
                }
            }
        }

    }

    public void calculateDistance(Data o) {
        List<Double> dataSet = this.data;
        Double distance = 0.0;
        for(int i=0, size = dataSet.size(); i < size; i++) {
            distance += (dataSet.get(i) - o.getData().get(i))*(dataSet.get(i) - o.getData().get(i));
        }
        this.distance = Math.sqrt(distance);
    }
    public static void calculateDistanceWithPending(Data o) throws Exception {
        if (KNN.getPending() == null) {
            throw new Exception("should set pending data first");
        }
        Double distance = 0.0;
        List<Double> dataSet = o.data;
        for(int i=0, size = dataSet.size(); i < size; i++) {
            distance += (dataSet.get(i) - KNN.getPending().getData().get(i))*(dataSet.get(i) - KNN.getPending().getData().get(i));
        }
        o.distance = Math.sqrt(distance);
    }
    public void calculateProbability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(Data o) {
        if (this.distance < o.distance) {
            return -1;
        }
        if (this.distance > o.distance) {
            return 1;
        }
        return 0;
    }
}
