package com.rocky.p101;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList {

    private Object[] elementData;

    private int size;

    public MyArrayList(){
        this(10);
    }

    public MyArrayList(int capacity){
        if (capacity<=0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.elementData = new Object[capacity];
    }

    public int size(){
        return size;
    }

    public boolean contains(Object o){
        return indexOf(0) >= 0;
    }

    public int indexOf(Object o){
        if (o==null){
            for (int i=0;i<size;i++){
                if (elementData[i] == null){
                    return i;
                }
            }
        }else {
            for (int i=0;i<size;i++){
                if (o.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    public void add(Object o){
        if (size >= elementData.length){
//            ensureCapacity();
            Object[] newArray = new Object[size*2+1];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
        elementData[size] = o;
        size++;
    }

    public void add(int index, Object o){
        if (index<0 || index>size){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index] = o;
    }

    public Object get(int index){
        if (index<0 || index>size){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return elementData[index];
    }

    public static void main(String[] args) {
//        List list = new ArrayList();
//
//        list.add("aaa");
//        list.add("aaa");
//
//        System.out.println(list.size());
//        System.out.println(list.get(0) == list.get(1));
//        list.remove("aaa");
//        System.out.println(list.size());
//        for (int i=0;i<list.size();i++){
//            System.out.println(list.get(i).hashCode());
//
//        }

        MyArrayList arrayList = new MyArrayList();
        for (int i=0;i<28;i++){
            arrayList.add("aaa");
        }
        arrayList.add(2,"ccc");
        arrayList.add("bbb");

        for (int i=0;i<arrayList.size();i++){

            System.out.println(arrayList.get(i));
        }
    }
}
