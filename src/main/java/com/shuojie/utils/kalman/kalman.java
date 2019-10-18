package com.shuojie.utils.kalman;


abstract class kalman{
   //members
    int t;
    matrix Sm1, S, R, HTRinv, M, Sn, A;
    vector eps, x, xn, a;

    //default constructor
    //缺省构造函数
    kalman(){}
    

    //Algorithm 4.3
    void forward(matrix Sm1m1, vector xm1m1, vector y){
	Sm1=F(t-1).times(Sm1m1.times(F(t-1).trans())).plus(Q(t-1));
	R=H(t).times(Sm1.times(H(t).trans())).plus(W(t));
	HTRinv=R.chol(H(t)).trans();
	matrix temp1=Sm1.times(HTRinv.times(H(t)));//temporary storage  //暂时存储区
	S=Sm1.minus(temp1.times(Sm1));
	M=F(t).minus(F(t).times(temp1));
	vector temp2=F(t-1).times(xm1m1);//temporary storage       //暂时存储区
	eps=y.minus(H(t).times(temp2));
	x=Sm1.times(HTRinv.times(eps)).plus(temp2);
    }

    abstract matrix Q(int i);
    
    abstract matrix F(int i);

    abstract matrix H(int i);

    abstract matrix W(int i);

    //A variant of Algorithm 5.1 that initializes a and A as 0 with updating
    ////算法5.1的一种变体，通过更新将A和A初始化为0
    //at the beginning of each step rather than the end. Note the ``typo'' in the
    //在每一步的开始而不是结束。请注意其中的“键入错误”
    //text where M(t-1) is not defined for the last step with t=1. Since a and A
    //文本，其中M(t-1)在最后一步没有定义，t=1。因为a和a
    //do not require updating in this case, the algorithm works as written provided
    //在这种情况下不需要更新，算法按规定执
    //the last two lines of the for loop are implemented conditionally on t>1.
    //for循环的最后两行是在t>1上有条件地实现的
    void smooth(vector aIn, matrix AIn, matrix HTRinvIn, vector epsIn){
	a=M.trans().times(HTRinvIn.times(epsIn)).plus(M.trans().times(aIn));
	A=M.trans().times(HTRinvIn.times(H(t+1).times(M))).plus(M.trans().times(AIn.times(M)));
	xn=x.plus(Sm1.times(a));
	Sn=S.minus(Sm1.times(A.times(Sm1)));
    }


    //access to members
    //访问成员

    int Gett(){
	return t;
    }

    matrix GetSm1(){
	return Sm1;
    }

    matrix GetS(){
	return S;
    }

    matrix GetR(){
	return R;
    }

    matrix GetHTRinv(){
	return HTRinv;
    }


    matrix GetM(){
	return M;
    }

    matrix GetSn(){
	return Sn;
    }

    matrix GetA(){
	return A;
    }


    vector Getx(){
	return x;
    }

    vector Geteps(){
	return eps;
    }

    vector Getxn(){
	return xn;
    }

    //useful for setting xn=x for last step in forward
    //(first step in backward) recursion
    //用于将xn=x设置为向前的最后一步
    //(向后递归的第一步)
    void Setxntox(){
	this.xn=new vector(x);
    }

    //useful for setting Sn=S for last step in forward
    //(first step in backward) recursion
    //用于为前一步设置Sn=S
    //(向后递归的第一步)
    void SetSntoS(){
	this.Sn=new matrix(S);
    }



    vector Geta(){
	return a;
    }


    //utilities
    //公共工具

    void printQ(int i){
	System.out.println("Q("+i+")");
	Q(i).printMatrix();
    }

    void printF(int i){
	System.out.println("F("+i+")");
	F(i).printMatrix();
    }

    void printH(int i){
	System.out.println("H("+i+")");
	H(i).printMatrix();
    }

    void printW(int i){
	System.out.println("W("+i+")");
	W(i).printMatrix();
    }

    void forwardPrint(){
	System.out.println("eps("+t+")");
	eps.printVector();

	System.out.println("x("+t+"|"+t+")");
	x.printVector();

	System.out.println("S("+t+"|"+t+")");
	S.printMatrix();
    }

    void backPrint(){
	System.out.println("x("+t+"|n)");
	xn.printVector();
	System.out.println("S("+t+"|n)");
	Sn.printMatrix();
    }

}
