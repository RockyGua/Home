package com.rocky.p101;

import java.util.LinkedList;
import java.util.List;

/**
 * 数组 + 链表(hashCode用于生成数组索引)
 */
public class MyHashMap {

    List[] linkedListArray = new LinkedList[10];

    int size;

    public int size(){
        return size;
    }

    public void put(Object key, Object value){
        Entity e = new Entity(key, value);
        int index = key.hashCode()% linkedListArray.length;

        if (linkedListArray[index] == null){
            LinkedList linkedList = new LinkedList();
            linkedList.add(e);
            linkedListArray[index] = linkedList;
        } else {
            LinkedList linkedList = (LinkedList)linkedListArray[index];
            for (int i=0; i<linkedList.size(); i++){
                Entity entity = (Entity)linkedList.get(i);
                if (key.equals(entity.key)){
                    entity.value = value;
                    return;
                }
            }
            linkedList.add(e);
        }
        size++;
    }

    public Object get(Object key){
        int index = key.hashCode()% linkedListArray.length;
        LinkedList linkedList = (LinkedList)linkedListArray[index];
        if (linkedList != null){
            for (int i=0; i<linkedList.size(); i++){
                Entity e = (Entity)linkedList.get(i);
                if (key.equals(e.key)){
                    return e.value;
                }
            }
        }
        return null;
    }

    class Entity{
        Object key;
        Object value;

        public Entity(){

        }

        public Entity(Object key, Object value){
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("aaa", "aaa-value");
        myHashMap.put("bbb", "bbb-value");
        myHashMap.put("aaa", "aaa-value111");

        System.out.println(myHashMap.get("aaa"));

        MyHashMap stuHashMap = new MyHashMap();
        Student s = new Student(1, "rocky");
        Student s3 = new Student(1, "rocky_3");

        Student s1 = new Student(4, "rocky_1");
        Student s2 = new Student(8, "rocky_2");
        stuHashMap.put(s.id , s);
        stuHashMap.put(s1.id, s1);
        stuHashMap.put(s2.id, s2);
        stuHashMap.put(s3.id, s3);

        System.out.println(((Student) (stuHashMap.get(s2.id))).name);

        System.out.println("-----------------------hashcode相等，equals不相等-----------------------------");

        MyHashMap myHashMap1 = new MyHashMap();
        Student stu = new Student(1, "rocky");
        Student stu3 = new Student(1, "rocky_3");

        Student stu1 = new Student(4, "rocky_1");
        Student stu2 = new Student(8, "rocky_2");


        myHashMap1.put(stu, stu.name);
        myHashMap1.put(stu3, stu3.name);
        myHashMap1.put(stu1, stu1.name);
        myHashMap1.put(stu2, stu2.name);

        System.out.println(myHashMap1.get(stu));
        System.out.println(myHashMap1.get(stu3));
        System.out.println(myHashMap1.get(stu1));
        System.out.println(myHashMap1.get(stu2));

    }

}

class Student{
    int id;
    String name;

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        return !(name != null ? !name.equals(student.name) : student.name != null);

    }

    @Override
    public int hashCode() {
        return id;
    }
}
