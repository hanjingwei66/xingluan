package com.shuojie.utils.kalman;

class vector{
    //number of rows
	//数字的行
    private int nr;
    //a double array to store the data
	//一个double数组存储这个数据
    private double[] X;
    
    //constructor: array to a vector
	//构造函数：数组一个vector
    vector(double [] a){
	nr=a.length;
	X = new double [nr];
	for(int i=0;i<nr;i++)
	    X[i]=a[i];
    }

    //copy constructor: vector to a vector
	//复制构造函数：vector 一个 vector
    vector(vector a){
	nr=a.GetNrow();
	X = new double [nr];
	for(int i=0;i<nr;i++)
	    X[i]=a.value(i);
    }


    //overloaded constructor: constant vector
	//超负荷的构造函数：常量 vector
    vector(int Nr, double a){
	nr=Nr;
	X=new double[nr];
	for(int i=0;i<nr;i++)
	    X[i]=a;
    }

    //overloaded constructor: matrix as a vector
	//超负荷的构造函数：matrix 作为 vector
    vector(matrix A){
	if(A.GetNcol()!=1){
	    System.out.println("A is not a vector");
	    System.exit(1);
	}
	nr=A.GetNrow();
	X=new double[nr];
	for(int i=0;i<nr;i++)
	    X[i]=A.value(i,0);
    }


    //overloaded constructor: extract column of matrix as a vector
	//超负荷的构造函数：提取matrix列的vector
    vector(matrix A, int j){
	nr=A.GetNrow();
	X=new double[nr];
	for(int i=0;i<nr;i++)
	    X[i]=A.value(i,j);
    }

    //overloaded constructor: scalar as a vector
	//超负荷的构造函数：作为一个标量变量
    vector(double a){
	nr=1;
	X=new double[1];
	X[0]=a;
    }


    //
    //
    //basic matrix operations
    //基本的矩阵操作
    //
 
    //vector 加法
	//
    vector plus(vector Y){
	if(nr != Y.GetNrow()){
	    System.out.println("Dimensions not compatible");
	    System.exit(1);
	}
	vector XplusY=new vector(nr, 0);
	for(int i=0;i<nr;i++)
	    XplusY.setValue(i,X[i]+Y.value(i));

	return XplusY;
    }


   //vector subtraction
	//vector 减法
    vector minus(vector Y){
	if(nr != Y.GetNrow()){
	    System.out.println("Dimensions not compatible");
	    System.exit(1);
	}
	vector XminusY=new vector(nr,0);
	for(int i=0;i<nr;i++)
	    XminusY.setValue(i,X[i]-Y.value(i));

	return XminusY;
    }


    //vector multiplication
	//vector 乘法

    //inner product
	//内部乘积
    double inner(vector Y){
	if(nr != Y.GetNrow()){
	    System.out.println("Dimensions not compatible");
	    System.exit(1);
	}
	double prod=0;
	for(int i=0;i<nr;i++)
	    prod+=X[i]*Y.value(i);

	return prod;
    }

    //outer product
	//外部 乘积
    matrix outer(vector Y){
	double temp;
	matrix XtimesY=new matrix(nr,Y.GetNrow(),0.);
	for(int i=0;i<nr;i++)
	    for(int j=0;j<Y.GetNrow();j++)
		XtimesY.setValue(i,j,X[i]*Y.value(j));
	
	return XtimesY;
	}


    //scalar multiplication
	//标量 乘法
    vector times(double Y){
	vector XtimesY=new vector(nr,0);
	for(int i=0;i<nr;i++)
	    XtimesY.setValue(i,X[i]*Y);

	return XtimesY;
    }
    
    //
    //access to a member elements
	//访问成员元素
    //

    double value(int r){
	return X[r];
    }

    int GetNrow(){
	return nr;
    }

    void setValue(int r, double a){
	X[r]=a;
    }
    //
    //utilities
	//公共工具
    //
    double[] getRetValues(){
    	
    	return X;
    	
    }
    
    void printVector(){
	System.out.println(" ");
	for(int i=0; i<nr ;i++){
	    System.out.print(X[i]+" ");
	    System.out.println(" ");
	}
    }


}
