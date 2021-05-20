package com.leyou.search.client;

public enum EnumTest {
    //星期一，星期二，星期三，星期四，星期五，星期六
	MON(1), TUE(2),WED(3),THU(4),FRI(5),SAT(6),SUN(0){
		public boolean isRest(){
			return true;
		}
	};
	//星期日

	private int value;
	EnumTest(int value){
		this.value=value;
	}
	public int getValue(){
		return value;
	}
	public boolean isRest(){
		return  false;
	}


}
