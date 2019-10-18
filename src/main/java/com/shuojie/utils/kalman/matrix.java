package com.shuojie.utils.kalman;

class matrix{
    //number of rows and columns
	//若干行和列
    private int nr, nc;
    //a double array to store the data
	//存储数据的双数组
    private double[][] X;
    
    //constructor: double array to a matrix
	//构造函数:将双数组转换为矩阵
    matrix(double [][] A){
	nr=A.length;
	nc=A[0].length;
	X = new double [nr][nc];
	for(int i=0;i<nr;i++){
	    for(int j=0; j<nc; j++)
		X[i][j]=A[i][j];
	}
    }

    //copy constructor: matrix to a matrix
	//复制构造函数:矩阵到矩阵
    matrix(matrix A){
	nr=A.GetNrow();
	nc=A.GetNcol();
	X = new double [nr][nc];
	for(int i=0;i<nr;i++){
	    for(int j=0; j<nc; j++)
		X[i][j]=A.value(i,j);
	}
    }


    //overloaded constructor: diagonal matrix
	//重载构造函数:对角矩阵
    matrix(int Nr, double a){
	nr=Nr;
	nc=Nr;
	X=new double[nr][nc];
	for(int i=0;i<nr;i++)
	    for(int j=0;j<nr;j++){
		if(i == j)
		    X[i][j]=a;
		else
		    X[i][j]=0;
	    }
    }


    //overloaded constructor: constant matrix
	//重载构造函数:常量矩阵
    matrix(int Nr, int Nc, double a){
	nr=Nr;
	nc=Nc;
	X=new double[nr][nc];
	for(int i=0;i<nr;i++){
	    for(int j=0; j<nc; j++)
		X[i][j]=a;
		}
    }

    //overloaded constructor:vector to a matrix
	//重载构造函数:矩阵的向量
    matrix(vector a){
	nr=a.GetNrow();
	nc=1;
	X = new double[nr][1];
	for(int i=0;i<nr;i++)
	    X[i][0]=a.value(i);
    }


    //overloaded constructor:scalar to a matrix
	//重载构造函数:矩阵的标量
    matrix(double a){
	nr=1;
	nc=1;
	X=new double[1][1];
	X[0][0]=a;
    }


    //overloaded constructor:polynomial
	//重载的构造函数:多项式
    matrix(vector a, int m){
	nr=a.GetNrow();
	nc=m;
	X=new double[nr][nc];
	for(int i=0;i<nr;i++){
	    X[i][0]=1;
	    for(int j=1;j<nc;j++)
		X[i][j]=X[i][j-1]*a.value(i);
	}
    }

    //
    //
    //basic matrix operations
    //
    //
 
    //matrix addition
	//矩阵加法
    matrix plus(matrix Y){
	if(nr != Y.GetNrow()){
	    System.out.println("Dimensions not compatible");
		System.out.println("尺寸不匹配");
	    System.exit(1);
	}
	if(nc != Y.GetNcol()){
	    System.out.println("Dimensions not compatible");
		System.out.println("尺寸不匹配");
	    System.exit(1);
	}
	matrix XplusY=new matrix(nr,nc,0);
	for(int i=0;i<nr;i++){
	    for(int j=0;j<nc;j++)
		XplusY.setValue(i,j,X[i][j]+Y.value(i,j));
	}
	return XplusY;
    }


   //matrix subtraction
	//矩阵减法
    matrix minus(matrix Y){
	if(nr != Y.GetNrow()){
	    System.out.println("Dimensions not compatible");
		System.out.println("尺寸不匹配");
	    System.exit(1);
	}
	if(nc != Y.GetNcol()){
	    System.out.println("Dimensions not compatible");
		System.out.println("尺寸不匹配");
	    System.exit(1);
	}
	matrix XminusY=new matrix(nr,nc,0);
	for(int i=0;i<nr;i++){
	    for(int j=0;j<nc;j++)
		XminusY.setValue(i,j,X[i][j]-Y.value(i,j));
	}
	return XminusY;
    }


    //matrix multiplication
	//矩阵乘法
    matrix times(matrix Y){
	matrix XtimesY;
	if((nr==1)&&(nc==1)){
	    XtimesY=Y.times(X[0][0]);
	}
	else{
	    if(nc != Y.GetNrow()){
		System.out.println("Dimensions not compatible");
		System.out.println("尺寸不匹配");
		System.exit(1);
	    }
	    double temp;
	    XtimesY=new matrix(nr,Y.GetNcol(),0);
	    for(int i=0;i<nr;i++){
		for(int j=0;j<Y.GetNcol();j++){
		    temp=0;
		    for(int k=0;k<nc;k++){
			temp+=X[i][k]*Y.value(k,j);
		    }
		    XtimesY.setValue(i,j,temp);
		}
	    }
	}
	return XtimesY;
    }


    //matrix multiplication
	//矩阵乘法
    vector times(vector Y){
	vector XtimesY;
	if((nr==1)&&(nc==1)){
	    XtimesY=new vector(Y.GetNrow(),0);
	    for(int i=0;i<Y.GetNrow();i++)
		XtimesY.setValue(i,X[0][0]*Y.value(i));
	}
	else{
	    if(nc != Y.GetNrow()){
		System.out.println("Dimensions not compatible");
		System.exit(1);
	    }
	    double temp;
	    XtimesY=new vector(nr,0);
	    for(int i=0;i<nr;i++){
		temp=0;

		for(int j=0;j<nc;j++)
		    temp+=X[i][j]*Y.value(j);

		XtimesY.setValue(i, temp);
	    }
	}
	return XtimesY;
    }



    //scalar multiplication
	//标量乘法
    matrix times(double Y){
	matrix XtimesY=new matrix(nr,nc,0);
	for(int i=0;i<nr;i++)
	    for(int j=0;j<nc;j++)
		XtimesY.setValue(i,j,X[i][j]*Y);
	return XtimesY;
    }

    //transposition
	//变换
    matrix trans(){
	matrix B=new matrix(nc,nr,0);
	for(int i=0;i<nc;i++)
	    for(int j=0;j<nr;j++)
		B.setValue(i,j,X[j][i]);
	return B;
    }

    //
    //access to a member elements
	//访问成员元素
    //

    double value(int r, int c){
	return X[r][c];
    }


    int GetNrow(){
	return nr;
    }

    int GetNcol(){
	return nc;
    }

    void setValue(int i, int j, double a){
	X[i][j]=a;
    }


    //
    //utilities
	//公共工具
    //


    void printMatrix(){
	System.out.println(" ");
	for(int i=0; i<nr ;i++){
	    for(int j=0; j<nc ;j++)
		System.out.print(X[i][j]+" ");
	    System.out.println(" ");
	}
    }

    
    //
    //Cholesky decomposition
	//柯列斯基分解
    //

    matrix back(matrix RHS){
	int nrhs=RHS.GetNcol();
	double temp;
	matrix B=new matrix(nr,nrhs,0);
	if(X[nr-1][nr-1]!=0){
	    for(int j=0;j<nrhs;j++)
		B.setValue(nr-1,j,RHS.value(nr-1,j)/X[nr-1][nr-1]);
	}
	else{
	    System.out.println("Singular system!");
		System.out.println("异常的系统!");
	    System.exit(1);
	}
	for(int i=nr-2;i>=0;i--)
	    if(X[i][i]!=0){
		for(int j=0;j<nrhs;j++){
		    temp=RHS.value(i,j);
		    for(int k=i+1;k<nr;k++)
			temp-=X[i][k]*B.value(k,j);
		    B.setValue(i,j,temp/X[i][i]);
		}
	    }
	    else{
		System.out.println("Singular system!");
		System.out.println("异常的系统!");
		System.exit(1);
	    }
	return B;
    }
	

    matrix forward(matrix RHS){
	int nrhs=RHS.GetNcol();
	double temp;
	matrix B=new matrix(nr,nrhs,0);
	if(X[0][0]!=0){
	    for(int j=0;j<nrhs;j++)
		B.setValue(0,j,RHS.value(0,j)/X[0][0]);
	}
	else{
	    System.out.println("Singular system!");
		System.out.println("异常的系统!");
	    System.exit(1);
	}
	for(int i=1;i<nr;i++)
	    if(X[i][i]!=0){
		for(int j=0;j<nrhs;j++){
		    temp=RHS.value(i,j);
		    for(int k=0;k<i;k++)
			temp-=X[i][k]*B.value(k,j);
		    B.setValue(i,j,temp/X[i][i]);
		}
	    }
	    else{
		System.out.println("Singular system!");
		System.out.println("异常的系统!");
		System.exit(1);
	    }
	return B;
    }


    matrix chol(matrix RHS){
	matrix B;
	if(nr==1){
	    B=RHS.times(1./X[0][0]);
	}
	else{
	    double temp=0;
	    if(nr != nc){
		System.out.println("Not a square matrix!");
		System.out.println("不是矩阵");
		System.exit(1);
	    }
	    matrix G=new matrix(nr,0);//square matrix of all zero //所有0的方阵
	    //lots of wasted storage here!

	    for(int j=0; j<nc; j++){//proceed by columns  //进行了列
		if(X[j][j]==0){
		    System.out.println("Singular matrix!");
			System.out.println("不是矩阵");
		    System.exit(1);
		}
		temp=X[j][j];//Compute l_{jj} first		//首先计算 {jj}
		for(int k=0;k<j;k++)
		    temp-=G.value(j,k)*G.value(j,k);
		G.setValue(j,j,Math.sqrt(temp));

		for(int i=j+1;i<nr;i++){//now do the rest of the column		//现在做这一列的其余部分
		    temp=X[i][j];
		    for(int k=0;k<j;k++)
			temp-=G.value(j,k)*G.value(i,k);
		    G.setValue(i,j,temp/G.value(j,j));
		}
	    }
	    //now forward solve Lz=rhs		//现在向前解Lz=rhs
	    matrix h=G.forward(RHS);
	    //now backsolve the system		//现在反解方程
	    B=G.trans().back(h);
	}
	return B;

    }

}
