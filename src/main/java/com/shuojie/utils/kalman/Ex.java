package com.shuojie.utils.kalman;

public class Ex{

    public static void main(String[] args){

	double[] ry={

			30.23139,30.23138,30.23145,30.23147,30.23149,
			30.23157,30.23168,30.23186,30.23201,30.23211,
			30.23223,30.23237,30.23208,30.23182,30.23187,

			/*30.2313986403,30.2313847349,30.2314542620,30.2314728025,30.2314913431,
			30.2315794106,30.2316813834,30.2318621532,30.2320104768,30.2321170843,
			30.2322375970,30.2323766499,30.2320800034,30.2318204371,30.2318760585,*/
		/*	30.2371805,30.2371215,30.2370840,30.2369606,30.2369445,30.2369552,
			30.2369606,30.2369552,30.2369499,30.2369499,30.2369499,30.2369499,30.2369499,
			30.2369499,30.2369499,30.2369499,30.2369445,30.2369284,30.2369284,30.2369338,
			30.2369284,30.2369284,30.2369284,30.2369284,30.2369284,30.2369284,30.2369284,
			30.2369284,30.2369284,30.2369284,30.2369284,30.2369284,30.2369284,30.2369284,
	    	30.2369391,30.2370411,30.2373898,30.2378511,30.2381300,30.2384734,30.2388381,
			30.2392405,30.2392995,30.2392727,30.2393049,30.2393317,30.2395355,30.2396696,
			30.2398413,30.2401095,30.2402222,30.2402383,30.2402007,30.2402436,30.2402865,
			30.2403831,30.2406084,30.2407747,30.2409303,30.2410590,30.2411717,30.2412897,
			30.2413755,30.2415096,30.2416223,30.2417778,30.2419281,30.2420729,30.2421641,
			30.2421694,30.2421641,30.2421855,30.2422285,30.2422606,30.2422553,30.2423089,
			30.2423089,30.2422821,30.2422606,30.2422928,30.2423036,30.2423357,30.2423679,
			30.2424001,30.2424484,30.2424967,30.2425450,30.2425503,30.2425664,30.2426308,
			30.2427327,30.2427488,30.2427971,30.2428185,30.2429044,30.2429687,30.2430599,
			30.2431082,30.2430975,30.2429312,30.2425932,30.2421265,30.2415794,30.2409034,
			30.2403080,30.2400988,30.2400988,30.2400988,30.2400988,30.2400988,30.2400451,
			30.2400451,30.2400451,30.2400398,30.2399325,30.2396053,30.2390420,30.2383607,
			30.2376687,30.2369874,30.2361989,30.2353996,30.2346915,30.2342569,30.2338171,
			30.2333343,30.2328139,30.2325296,30.2326261,30.2330929,30.2336293,30.2341979,
			30.2345144,30.2347773,30.2350670,30.2353245,30.2354478,30.2355605,30.2354264,
			30.2350616,30.2346539,30.2343535,30.2340853,30.2336776,30.2331626,30.2325296,
			30.2319020,30.2312314,30.2304857,30.2295738,30.2288550,30.2279752,30.2272027,

			30.2265000,30.2259904,30.2256202,30.2252286,30.2249014,30.2246654,30.2245473,
			30.2245420,30.2245366,30.2245366,30.2244079,30.2240753,30.2236515,30.2231419,
			30.2227127,30.2223372,30.2219349,30.2214789,30.2210068,30.2205294,30.2200520,
			30.2195745,30.2190756,30.2186036,30.2181583,30.2177238,30.2173054,30.2169942,
			30.2167260,30.2163344,30.2159589,30.2157282,30.2155673*/
			};
	int n=ry.length;//number of data values		//数据值个数
	double[] rt=new double[n];//t ordinates		//t纵坐标
	for(int i=0;i<n;i++){
		rt[i]=i+1;
	}

	int m=7;//derivative in penalty		//导数在惩罚
	//m=2 produces a cubic smoothing spline
	//m=2生成一个三次平滑样条

	double lam=124.3984;	//smoothing parameter value		//平滑参数值
	//For this data and this choice of lambda, the same result will
	//对于这个数据和lambda的选择，结果是一样的
	//be obtained using spar=1 in the R smooth.spline function with
	//在R光滑中使用spar=1得到。样条函数逆向
	//all.knots=T

	vector y=new vector(ry);//convert array to vector	//将数组转换为向量
	vector tau=new vector(rt);//convert array to vector


	//ss objects for processing y
	//用于处理y的ss对象
	ss[] s=new ss[n];
		//ss objects for processing polynomial matrix
	//处理多项式矩阵的ss对象
	ss[][] sT=new ss[n][m];
		//polynomial matrix
	//多项式矩阵
	matrix T=new matrix(tau,m);
		//initialize forward recusion for y
	//初始化y的前向循环
	s[0]=new ss(1,tau,m,lam);
	s[0].forward(new matrix(m,0), new vector(m,0),new vector(y.value(0)));

	//initialize forward recusion for T
	//初始化T的前向循环
	for(int j=0;j<m;j++){
	    sT[0][j]=new ss(1,tau,m,lam);
	    sT[0][j].forward(new matrix(m,0), new vector(m,0),new vector(T.value(0,j)));
	}
	//forward recursion
	//向前递归
	for(int i=1;i<n;i++){
	    s[i]=new ss(i+1,tau,m, lam);
	    s[i].forward(s[i-1].GetS(),s[i-1].Getx(),new vector(y.value(i)));for(int j = 0; j<m; j++){
		sT[i][j]=new ss(i+1,tau,m,lam);
		sT[i][j].forward(sT[i-1][j].GetS(), sT[i-1][j].Getx(),new vector(T.value(i,j)));
	    }
	}
	//define xn and Sn for s[n-1] for use in subsequent recusions
	//为s[n-1]定义xn和Sn，以便在以后的重复中使用
	s[n-1].Setxntox();
	s[n-1].SetSntoS();

	//initialize backward recusion for y
	//初始化y的向后回缩
	s[n-2].smooth(new vector(m,0),new matrix(m,0), s[n-1].GetHTRinv(), s[n-1].Geteps());

	//initialize backward recusion for T
	//初始化T的反循环
	for(int j=0;j<m;j++){
	    sT[n-1][j].Setxntox();
	    sT[n-2][j].smooth(new vector(m,0),new matrix(m,0), sT[n-1][j].GetHTRinv(), sT[n-1][j].Geteps());
	}
	//backward recursion
	//向后递归
	for(int i=n-3;i>=0;i--){
	    s[i].smooth(s[i+1].Geta(), s[i+1].GetA(),s[i+1].GetHTRinv(), s[i+1].Geteps());
	    for(int j=0;j<m;j++){
		sT[i][j].smooth(sT[i+1][j].Geta(), sT[i+1][j].GetA(),sT[i+1][j].GetHTRinv(), sT[i+1][j].Geteps());
	    }
	}

	//now compute the fit and leverage values
	//现在计算fit和leverage值

	//Ttil=T-fit_to_T
	matrix Ttil=new matrix(n,m,0);
	vector fit=new vector(n,0);
	for(int i=0;i<n;i++){
	    fit.setValue(i,s[i].Getxn().value(0));
	    for(int j=0;j<m;j++)
		Ttil.setValue(i,j,T.value(i,j)-sT[i][j].Getxn().value(0));
	}
	vector g_0=Ttil.trans().times(y);

	//explicitely evaluate the m by m matrix ${T^T\Sigma_{y_0}^{-1}T)^{-1}$ here
	//这里明确地计算m×m矩阵${T^T\Sigma_{y_0}^{-1}T)^{-1}$
	matrix V=Ttil.trans().times(T).chol(new matrix(m,1));

	fit=fit.plus(Ttil.times(V.times(g_0)));

	System.out.println("The fit");
	fit.printVector();


	vector lev=new vector(n, 0);//leverage values
	vector temp;//temporary storage vector of length m 	//长度为m的临时存储向量
	for(int j=0;j<n;j++){
	    temp=new vector(Ttil.trans(),j);//extract jth row from Ttil
	    lev.setValue(j,s[j].GetSn().value(0,0)+temp.inner(V.times(temp)));
	}
	lev.printVector();
		System.out.println("Levarage values");

    }
}
