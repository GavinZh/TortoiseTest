package com.gavin.huawei.prepare;

/**
 * 偷房子金币问题：一个小偷偷一条街上的房子，相邻两间房子的金币不能一起偷，否则触发报警装置
 * 每间房子的收益给你，那么如何偷可以保证收益最大化？
 * 
 * 此为动态规划问题：给你数组V[n]，A存放偷得最大收益，B存放不偷得最大收益
 * ①初始状态：第一个房子的时候，我们偷他获取的最大收益是A[1]=V[1]，不偷获取的最大收益是B[1]=0;
 *                      到第二个房子，偷他获取的最大收益是A[2]=V[2]+B[1]，不偷的最大收益是B[2]=max{A[1],B[1]}
 *                      到第三个，偷的最大收益A[3]=V[3]+B[2]，不偷的最大收益是B[3]=max{A[2],B[2]}
 * ②状态转移方程：偷第N个房子，偷他的最大收益A[n]=V[n]+B[n-1]，不偷的最大收益B[n]=max{A[n-1],B[n-1]}
 * @author GavinZhang
 *
 */
public class HouseRobberProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {4,5,2,3,8};
		int[] test1 = {4,5,2,10,8};
		int[] test2 = {3,2,2,8};
		//aa
		long startTime = System.nanoTime();
		System.out.println(maxValue(test));
		System.out.println(maxValue(test1));
		System.out.println(maxValue(test2));
		long endTime = System.nanoTime();
		System.out.println(endTime-startTime);
		
		startTime = System.nanoTime();
		System.out.println(maxValue2(test));
		System.out.println(maxValue2(test1));
		System.out.println(maxValue2(test2));
		endTime = System.nanoTime();
		System.out.println(endTime-startTime);
	}
	
	public static int  maxValue(int [] house) {
		
		int maxYes = house[0];  //存放某位置偷的时候最大收益
		int maxNo = 0; //存放某位置不偷的时候最大收益
		int temp = 0;
		
		for (int i = 1; i < house.length; i++) {
			temp =maxNo;
			maxNo = compare(maxNo, maxYes);
			maxYes = temp + house[i];
		}
		return compare(maxNo, maxYes);
	}
	
	public static int compare(int a,int b){
		if (a>b) {
			return a;
		}else {
			return b;
		}
	}
	
	/**
	 * 方法2的思想：每个位置都存一个当时的最大收益max[i]，那么到了偷第i+1个房子的时候：
	 * 假如第i+1个偷了，那么就是V[i+1]+max[i-1]
	 * 假如第i+1个没偷，那么就是max[i]（max[i]不一定是i偷了的情况，若没偷就是与max[i-1]相等）
	 * 最后求max{ V[i+1]+max[i-1], max[i] } 即可
	 * @param house
	 * @return
	 */
	public static int maxValue2(int[] house ){
		int length = house.length;
		int[] maxSet = new int[length];
		maxSet[0]=house[0];
		maxSet[1]=compare(house[0], house[1]);
		for (int i = 2; i < maxSet.length; i++) {
			maxSet[i] = compare(house[i]+maxSet[i-2], maxSet[i-1]);
		}
		return maxSet[length-1];
	}

}
