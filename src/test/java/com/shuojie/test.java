//package com.shuojie;
//
//import jdk.internal.org.objectweb.asm.tree.analysis.Value;
//import org.junit.Test;
//
//import java.security.Key;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//import java.util.function.Supplier;
//
//public class test {
//    public static void main(String[] args) {
////        System.out.println("helloworld");
////        int a=0xff;
//////        int [] a={25,30,46,78,23,99};
//////        max(3,a);
////        char a=48;
////        char b ='0';
////        int c=(int)b;
////        int d=48;
////        char e=(char) d;
////        int f=15*256+15*16+15;
////        double db=3/510.00;
////        int bytes=(int) 0.063131321313;
////        byte [] bytes3={0x25,(byte) 0x95,(byte) 0xfc,(byte) 0xd0,
////                0x00,0x01,0x5b,0x6c
////                ,0x07, (byte)0xff, (byte) 0xfe,
////                0x01,0x00,0x01,0x2a,0x00};
////        String hexString = HexConvert.BinaryToHexString( bytes3 );
////        hexString =  hexString.replace( " ","" );
////        String asc = HexConvert.convertHexToString( hexString );
////        System.out.println(hexString);
//        Map map=new HashMap<Key, Value>();
//        map.put("123",123);
//        map.put("123",456);
//        for(Object key : map.keySet()){
//            System.out.println(key);
//        }
//        Object o = map.get("123");
//        System.out.println(o);
//    }
//    public  void getSum(Person person){
//        person.sum();
//    }
//    @Test
//    public void test2() {
//        Consumer<String> con = (x) -> System.out.println(x);
//        con.accept("有一个参数，无返回值的用法（Consumer函数式接口）");
//        Consumer<String> con1 = (x) -> {
//            System.out.println(x);
//        };
//
//        getSum(() -> {
//            System.out.println(1);
//        });
////    public static int [] max(int n ,int[] arr){
////        //arr重大到小排序
////
////        int newarr[]=arr.clone();//复制一份
//////        for (int i = 0; i < arr.length; i++) {
//////            int t=i;
//////            for (int j = i+1; j < arr.length ; j++) {
//////                if (arr[i]<arr[t]){
//////                    t=j;
//////                }
//////                if(t!=i){
//////                    int s=arr[i];
//////                    arr[i]=arr[t];
//////                    arr[t]=s;
//////                }
//////            }
//////        }
////        for (int i=0; i<arr.length-1 ; i++){
////            for(int j=i+1; j<arr.length; j++){
////                if(arr[i]<arr[j]) {
////                    int temp = arr[i];
////                    arr[i] = arr[j];
////                    arr[j] = temp;
////                }
////            }
////        }
////            //取前n个数字放入数组
////        for (int i = 0; i < n; i++) {
////            int s=arr[i];
////            for (int j = 0; j <newarr.length; j++) {
////                if (s==newarr[j]){
////                    System.out.println("数组下标"+j);
////                }
////            }
////        }
////        //去原数组找前n个数字的下标
////        return arr;
////    }
//    }
//}
