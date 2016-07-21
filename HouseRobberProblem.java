package com.gavin.huawei.prepare;

/**
 * ͵���ӽ�����⣺һ��С͵͵һ�����ϵķ��ӣ��������䷿�ӵĽ�Ҳ���һ��͵�����򴥷�����װ��
 * ÿ�䷿�ӵ�������㣬��ô���͵���Ա�֤������󻯣�
 * 
 * ��Ϊ��̬�滮���⣺��������V[n]��A���͵��������棬B��Ų�͵���������
 * �ٳ�ʼ״̬����һ�����ӵ�ʱ������͵����ȡ�����������A[1]=V[1]����͵��ȡ�����������B[1]=0;
 *                      ���ڶ������ӣ�͵����ȡ�����������A[2]=V[2]+B[1]����͵�����������B[2]=max{A[1],B[1]}
 *                      ����������͵���������A[3]=V[3]+B[2]����͵�����������B[3]=max{A[2],B[2]}
 * ��״̬ת�Ʒ��̣�͵��N�����ӣ�͵�����������A[n]=V[n]+B[n-1]����͵���������B[n]=max{A[n-1],B[n-1]}
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
		
		int maxYes = house[0];  //���ĳλ��͵��ʱ���������
		int maxNo = 0; //���ĳλ�ò�͵��ʱ���������
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
	 * ����2��˼�룺ÿ��λ�ö���һ����ʱ���������max[i]����ô����͵��i+1�����ӵ�ʱ��
	 * �����i+1��͵�ˣ���ô����V[i+1]+max[i-1]
	 * �����i+1��û͵����ô����max[i]��max[i]��һ����i͵�˵��������û͵������max[i-1]��ȣ�
	 * �����max{ V[i+1]+max[i-1], max[i] } ����
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
