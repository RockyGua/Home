package com.rocky.p4;

public class Bubble {

    public static void main(String[] args) {
        int[] values = {3,6,5,2,1,7,8,10,4,13};
        bubble(values);
        for (int i=0; i<values.length; i++){
            System.out.print(values[i] + ",");
        }
    }

    private static void bubble(int[] values) {
        for (int i=0; i<values.length; i++){
            int temp = values[i];
            for (int j=i+1; j<values.length; j++){
                if (temp > values[j]){
                    temp = values[j];
                    values[j] = values[i];
                    values[i] = temp;
                }
            }
        }
    }
}
