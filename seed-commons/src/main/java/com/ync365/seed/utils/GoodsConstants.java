package com.ync365.seed.utils;


public class GoodsConstants {
	/**
	 * 逻辑删除状态
	 * */
	public static enum LogDel {
		/**
		 * 1 删除 0 未删除
		 */
		 NO(0,"未删除"),YES(1,"删除");
		 
		 private final int value;
		 private final String content;
		 
		 private LogDel(int value,String content){
			 this.content=content;
			 this.value=value;
		 }
		 
		 public int v(){
			 return this.value;
		 }
		 public String c(){
			 return this.content;
		 }
	}
    /**
     * 商品状态
     **
     */
    public static enum GoodsStatus {
	    /*
	     * 0：默认未审核 1:通过  2：未通过  3：上架  4：下架   
	     */
    	UN_AUDIT(0, "未审核"), PASS(1, "通过"),UN_PASS(2,"未通过"),
    	GROUNDING(3,"上架"),UN_GROUNDING(4,"下架");

        private final int value;
        private final String content;

        private GoodsStatus(int value, String content) {
            this.value = value;
            this.content = content;
        }

        public int v() {
            return value;
        }

        public String c() {
            return content;
        }
    }
    
   
     
}
