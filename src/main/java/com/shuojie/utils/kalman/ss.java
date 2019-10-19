package com.shuojie.utils.kalman;

//This class provides specific examples of the abstract
//这个类提供了抽象的具体例子
//methods in class Kalman that arise from smoothing
//类中由平滑产生的Kalman方法
//spline considerations.
///样条注意事项
//The x process corresponds to m-fold integrated Brownian
//x过程对应于m次积分的布朗函数
//motion.
//运动

class ss extends kalman{
    //members
    vector tau;//vector of "time" ordinates		//时间向量坐标
    int m;//order of derivative in penalty		//导数的阶
    double lam;//smoothing parameter		//平滑参数

    //constructor
    ss(int T, vector Tau, int M, double Lambda){
	t=T;
	tau=new vector(Tau);
	m=M;
	lam=Lambda;
    }

    matrix Q(int k){
	double den=0, num=0;
	double delta=0;

	if(k==0)delta=tau.value(0);
	if(k!=0)delta=tau.value(k)-tau.value(k-1);

	matrix A = new matrix(m,0);
	for(int i=0;i<m;i++)
	    for(int j=0;j<m;j++){
		den=fac(m-1-i)*fac(m-1-j);
		den*=(double)(2*m-1-i-j);
		num=Math.pow(delta,2*m-1-i-j)/lam;
		A.setValue(i,j,num/den);
	    }
	
	return A;
    }
	
   
    matrix F(int k){
	matrix A;
	double delta=0;
	if(k==tau.GetNrow()){
	    A=new matrix(m,0);
	}

	else{
	    if(k==0)delta=tau.value(0);
	
	    if(k!=0)delta=tau.value(k)-tau.value(k-1);

	    A=new matrix(m,1);//m by m identity 		//m 通过 m 统一
	    if(m!=1){
		for(int i=0;i<(m-1);i++)
		    for(int j=i+1;j<m;j++){
			A.setValue(i,j,Math.pow(delta,j-i)/fac(j-i));
		    }
	    }
	}
	return A;
    }


    matrix H(int k){
	matrix A=new matrix(1,m,0);
	A.setValue(0,0,1);
	return A;
    }

    matrix W(int k){
	matrix A= new matrix(1,1,1);
	return A;
    }

    static double fac(int k){
	double f=1;
	if(k==0)f=1;
	if(k!=0){
	    for(int i=1;i<=k;i++)
		f=f*(double)i;
	}
	return f;
    }

    vector Gettau(){
	return tau;
    }

    int Getm(){
	return m;
    }

}
