package com.ync365.seed.dto.base;

public class ResponseCode {

    /**
     * 通用状态码
     */
    public static enum CommonCode {

        OK(200, "OK"), /**
                        * 参数错误
                        */
        PARAM_WRONG(301, "PARAM_WRONG"), /**
                                          * Post Token 不存在
                                          */
        TOKEN_NOT_EXISTS(401, "TOKEN_NOT_EXISTS"), /**
                                                    * Token过期
                                                    */
        TOKEN_EXPIRE(402, "TOKEN_EXPIRE"),
        /**
         * Token已被使用
         */
        TOKEN_HAS_USED(403, "TOKEN_HAS_USED"), /**
                                                                             * 未知失败
                                                                             */
        UNKNOWN_FAIL(502, "UNKNOWN_FAIL"),
        
        OBJECT_NOT_EXISTS(601,"OBJECT_NOT_EXISTS");                 /**对象不存在*/
        
       

        private final Integer value;
        private final String content;

        private CommonCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }

    }


    /**
     * 用户状态码
     */
    public static enum UserCode {

        /**
         * 用户不存在
         */
        USER_NOT_EXISTS(1401, "USER_NOT_EXISTS"), /**
                                                   * 已注册
                                                   */
        ALREADY_REG(1402, "ALREADY_REG"), /**
                                           * 昵称已经存在
                                           */
        NICKNAME_ALREADY_EXISTS(1404, "NICKNAME_ALREADY_EXISTS"), /**
                                                                   * 密码错误
                                                                   */
        WRONG_PASSWD(1408, "WRONG_PASSWD"), WRONG_VALID_CODE(1405, "WRONG_VALID_CODE");

        private final Integer value;
        private final String content;

        private UserCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }

    }

    /**
     * 商品状态码
     */
    public static enum GoodsCode {

        GOODS_NOT_EXISTS(2401, "GOODS_NOT_EXISTS");

        private final Integer value;
        private final String content;

        private GoodsCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }

    }
    
    /**
     * 订单状态码
     */
    public static enum OrderCode {

        ORDER_NOT_EXISTS(3401, "ORDER_NOT_EXISTS"),
        ORDER_STATUS_WRONG(3402, "ORDER_STATUS_WRONG"),
        ORDER_GOODS_WRONG(3403, "ORDER_GOODS_WRONG"),
        ORDER_ERROR_REQUEST(3404,"ORDER_ERROR_REQUEST");

        private final Integer value;
        private final String content;

        private OrderCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }

    }

    /**
     * 购物车状态码
     */
    public static enum CartCode {

        CART_IS_EMPTY(4401, "CART_IS_EMPTY"), 
        CART_IS_FULL(4402, "CART_IS_FULL"),
        SKU_LESS_THAN_LOWER_LIMIT(4403, "SKU_LESS_THAN_LOWER_LIMIT"),
        SKU_MORE_THAN_UPPER_LIMIT(4404, "SKU_MORE_THAN_UPPER_LIMIT"),
        SKU_IS_DELETE(4405, "SKU_IS_DELETE"),
        SKU_IS_NOT_ON_SALE(4406, "SKU_IS_NOT_ON_SALE"),
        SKU_STOCK_IS_NOT_ENOUGH(4407, "SKU_STOCK_IS_NOT_ENOUGH");

        private final Integer value;
        private final String content;

        private CartCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }

    }
   
    
    /**
     * 活动商品状态码
     */
    public static enum PromotionGoodsCode {

        PROMOTION_GOODS_NOT_EXISTS(5401, "PROMOTION_GOODS_NOT_EXISTS"),
        NO_PROMOTION(5402, "NO_PROMOTION");

        private final Integer value;
        private final String content;

        private PromotionGoodsCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }

    }
    
    public static enum UserCouponCode{
    	UPDATE_FAIL(7701,"UPDATE_FAIL"),AMOUNT_DIFFER(7702,"AMOUNT_DIFFER"),
    	NO_CAN_USED_COUPON(7003,"NO_CAN_USED_COUPON"),
    	COUPON_HAS_USED(7004,"COUPON_HAS_USED"),
    	COUPON_ERROR(7005,"COUPON_ERROR");
    	private final Integer value;
        private final String content;

        private UserCouponCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }
    }
    
    public static enum PayCode{
    	RSA_VERFIY_FAIL(8801,"RSA_VERFIY_FAIL"),
    	BUSINESS_IS_FAIL(8802,"BUSINESS_IS_FAIL");
    	private final Integer value;
        private final String content;

        private PayCode(Integer value, String content) {
            this.value = value;
            this.content = content;
        }

        public Integer v() {
            return value;
        }

        public String c() {
            return content;
        }
    }
    
}
