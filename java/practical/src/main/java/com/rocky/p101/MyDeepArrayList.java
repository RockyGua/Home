package com.rocky.p101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyDeepArrayList<E> implements Iterable{

    private static final int INIT_SIZE = 10;
    private Object[] elementData = new Object[INIT_SIZE];

    private int size;

    public int size(){
        return size;
    }

    public void add(E ele){
        ensureCapacityInternal();
        elementData[size] = ele;
        size++;
    }

    public void remove(int index){
        System.arraycopy(elementData, index+1, elementData, index, size-index);
        size--;
    }

    private void ensureCapacityInternal(){
        if (size +1 > elementData.length)
            grow();
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, size *2 +1);
    }


    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator{

        private int coursor;
        int lastRet = -1; // index of last element returned; -1 if no such

        public boolean hasNext() {
            return coursor < size;
        }

        public E next() {
            Object[] elementData = MyDeepArrayList.this.elementData;
            lastRet = coursor;
            return (E)elementData[coursor++];
        }

        public void remove() {
            MyDeepArrayList.this.remove(lastRet);
            coursor = lastRet;
            lastRet = -1;
        }
    }

    public static void main(String[] args) {
        MyDeepArrayList<String> arrayList = new MyDeepArrayList<String>();
        String s1 = "aaa";
        String s2 = "bbb";
        String s3 = "ccc";
        String s4 = "ddd";
        String s5 = "eee";
        arrayList.add(s1);
        arrayList.add(s2);
        arrayList.add(s3);
        arrayList.add(s4);
        arrayList.add(s5);

//        arrayList.remove(0);

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            String e = (String)iterator.next();
            if (e.equals("bbb")){
                iterator.remove();
                continue;
            }
            System.out.println(e);
        }

//        List list = new ArrayList();
//        list.add("aaa");
//        list.add("bbb");
//        list.add("ccc");
//        list.add("ddd");
//        list.add("eee");
//
//        Iterator<String> iterator = (Iterator<String>)list.iterator();
//        while (iterator.hasNext()){
//            String s = iterator.next();
//            if (s.equals("bbb")){
//                iterator.remove();
//                continue;
//            }
//            System.out.println(s);
//        }

    }
}
