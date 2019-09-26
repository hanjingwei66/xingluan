package com.shuojie;

public class test {
    public static void main(String[] args) {
        int [] a={25,30,46,78,23,99};
        max(3,a);
    }
    public static int [] max(int n ,int[] arr){
        //arr重大到小排序

        int newarr[]=arr.clone();//复制一份
//        for (int i = 0; i < arr.length; i++) {
//            int t=i;
//            for (int j = i+1; j < arr.length ; j++) {
//                if (arr[i]<arr[t]){
//                    t=j;
//                }
//                if(t!=i){
//                    int s=arr[i];
//                    arr[i]=arr[t];
//                    arr[t]=s;
//                }
//            }
//        }
        for (int i=0; i<arr.length-1 ; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i]<arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
            //取前n个数字放入数组
        for (int i = 0; i < n; i++) {
            int s=arr[i];
            for (int j = 0; j <newarr.length; j++) {
                if (s==newarr[j]){
                    System.out.println("数组下标"+j);
                }
            }
        }
        //去原数组找前n个数字的下标
        return arr;
    }
}
