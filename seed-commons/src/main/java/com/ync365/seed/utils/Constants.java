package com.ync365.seed.utils;


public class Constants {

	
    
    /**
     * 状态，通用
     */
    public static enum Status {

        Enabled(1, "启用"), Disabled(0, "禁用");

        private final int value;
        private final String content;

        private Status(int value, String content) {
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
    
    /**
     *库存操作
     */
    public static enum StocksOpertor{
          NEW ("设置新库存",1),ADD("增加库存",2),REDUCE("减少库存",3) ;
          // 成员变量
          private String name;
          private int index;

          // 构造方法
          private StocksOpertor(String name, int index) {
              this.name = name;
              this.index = index;
          }

          // 普通方法
          public static String getName(int index) {
              for (StocksOpertor c : StocksOpertor.values()) {
                  if (c.getIndex() == index) {
                      return c.name;
                  }
              }
              return null;
          }

          // get set 方法
          public String getName() {
              return name;
          }

          public void setName(String name) {
              this.name = name;
          }

          public int getIndex() {
              return index;
          }

          public void setIndex(int index) {
              this.index = index;
          }

    }
    
    
    /**
     * 商品上下架状态
     **
     */
    public static enum GoodsStatus {
    	
    	NOT_AUDIT((byte)0,"未审核"),AUDIT((byte)1,"审核"),AUDIT_FAIL((byte)2,"审核未通过"),PUT_UP((byte)3, "上架"), PUT_DOWN((byte)4, "下架");

        private final Byte value;
        private final String content;

        private GoodsStatus(Byte value, String content) {
            this.value = value;
            this.content = content;
        }

        public Byte v() {
            return value;
        }

        public String c() {
            return content;
        }
    }
    
    public static enum SoilTest{
    	
    	PACKAGE(1,"超值套餐"),FATCARD(2,"精准配肥卡"),SUPPLYAREA(3,"综合供应专区");
    	private final int value;
        private final String content;

        private SoilTest(int value, String content) {
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
    
    /**
     * 扣减库存返回状态
     *     
     * @Title：StockReduceStatus  
     * @Description: TODO   
     * @author: robo        
     * @date: 2015年11月4日 下午2:54:45      
     * @version     
     *
     */
    public static enum StockReduceStatus{
    	UPDATE_FAIL(0,"更新失败"),UPDATE_SUCCESS(1,"更新成功"),STOCK_LESS(2,"库存不足");
    	private final int value;
        private final String content;

        private StockReduceStatus(int value, String content) {
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
    
    
  //*************活动相关的常量******************
    /**
     * 通用的是否删除常量，0：未删除，1：已删除
     * @author lyh
     *
     */
    public static enum IsDelete {
    	NO(0,"未删除"),
    	YES(1,"已删除");   	
    	private final int value;
    	private final String content;
    	private IsDelete(int value,String content){
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
    /**
     * 活动类型
     * 
     */
    public static enum PromotionType {
    	
    	Group((byte)1, "团购活动"),Coupon((byte)2,"红包活动"),
    	Reduce((byte)3,"满减活动"),Retrun((byte)4,"满返活动"),
    	Gift((byte)5,"满赠活动"),Seckill((byte)6,"秒杀活动"),
    	Down((byte)7,"直降活动");

        private final Byte value;
        private final String content;

        private PromotionType(Byte value, String content) {
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
    /**
     * 活动是否删除
     * @author lyh
     *
     */
    public static enum PromotionIsDelete {
    	 Enabled((byte)0, "正常"), Disabled((byte)1, "删除");
    	 

    	  private final byte value;
          private final String content;

          private PromotionIsDelete(Byte value, String content) {
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
    
    /**
     * 活动商品是否删除
     * @author lyh
     *
     */
    public static enum PromotionGoodsSkuIsDelete {
         Enabled((byte)0, "正常"), Disabled((byte)1, "删除");
         

          private final byte value;
          private final String content;

          private PromotionGoodsSkuIsDelete(Byte value, String content) {
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
   
    /**
     * 满减类型
     * @author lyh
     *
     */
    public static enum PromotionReduceType {
    	 FixedReduce((byte)1, "定额满减"), JieTiReduce((byte)2, "阶梯满减");
    	 

    	  private final Byte value;
          private final String content;

          private PromotionReduceType(Byte value, String content) {
              this.value = value;
              this.content = content;
          }

          public Byte v() {
              return value;
          }

          public String c() {
              return content;
          }
    }
    /**
     * 优惠方式
     * @author lyh
     *
     */
    public static enum PromotionReturnType {
    	GeneralType((byte)1, "普通优惠"), MultipleType((byte)2, "多重优惠");    	 

    	  private final Byte value;
          private final String content;

          private PromotionReturnType(Byte value, String content) {
              this.value = value;
              this.content = content;
          }

          public Byte v() {
              return value;
          }

          public String c() {
              return content;
          }
    }
    /**
     * 活动秒杀类型
     * @author lyh
     *
     */
    public static enum PromotionSeckillType {
    	LimitTime((byte)1, "限时"), 
    	LimitAmount((byte)2, "限量"),
    	LimitTimeAndAmount((byte)3, "限时限量");    	 

    	  private final Byte value;
          private final String content;

          private PromotionSeckillType(Byte value, String content) {
              this.value = value;
              this.content = content;
          }

          public Byte v() {
              return value;
          }

          public String c() {
              return content;
          }
    }
    /**
     * 活动团购类型
     * @author lyh
     *
     */
    public static enum PromotionGroupbuyType {
    	MoneyRequire((byte)1, "金额要求"), 
    	AmountRequire((byte)2, "数量要求");    	   	 

    	  private final Byte value;
          private final String content;

          private PromotionGroupbuyType(Byte value, String content) {
              this.value = value;
              this.content = content;
          }

          public Byte v() {
              return value;
          }

          public String c() {
              return content;
          }
    }    
    /**
     * 活动状态
     * @author sunyf
     *
     */
    public static enum PromotionStatus {
    	 New((byte)0, "新建"), Begin((byte)1, "启动"), End((byte)2, "结束") ;
    	 

    	  private final byte value;
          private final String content;

          private PromotionStatus(Byte value, String content) {
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
    /**
     * 是否状态
     * @author sunyf
     *
     */
    public static enum Whether {
    	 Yes((byte)1, "是"), No((byte)0, "否");
    	 

    	  private final byte value;
          private final String content;

          private Whether(Byte value, String content) {
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
   //*************订单相关的常量******************
    /**
     * 订单来源
     * @author lyh
     *
     */
    public static enum OrderSource {
    	PC((byte)1, "PC端"),
    	IOS((byte)2, "苹果手机"),
    	Android((byte)3, "安卓"),
    	Ipad((byte)4, "平板电脑");    	   	 

    	  private final Byte value;
          private final String content;

          private OrderSource(Byte value, String content) {
              this.value = value;
              this.content = content;
          }

          public Byte v() {
              return value;
          }

          public String c() {
              return content;
          }
    }
    /**
     * 订单类型
     * @author ivan
     *
     */
    public static enum OrderType {
    	MAIN(0,"主订单"),
    	SUB(1,"子订单"),
    	MERGE(2,"合并订单");
    	
    	private final Integer value;
    	private final String content;
    	private OrderType(Integer value,String content){
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
     * 支付方式
     * @author ivan
     *
     */
    public static enum PayWay {
        YNB(1,"云农宝"),
        ABC(2,"农行支付");
        
        private final int value;
        private final String content;
        private PayWay(int value,String content){
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
    /**
     * 支付类型
     * @author ivan
     *
     */
    public static enum PayType {
        SELF(0,"自主支付"),
        MERGER(1,"合并支付");
        
        private final int value;
        private final String content;
        private PayType(int value,String content){
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
    /**
     * 订单状态
     * @author lyh
     *
     */
    public static enum OrderStatus {
    	NotPay((byte)1, "待支付"),
    	ChangePriceNotCheck((byte)2, "改价待审核"),
    	ChangePriceChecked((byte)3, "改价已审核"),
    	Paid((byte)4, "已支付"), 
    	PreparedGoods((byte)5, "已配货"),
    	StockOut((byte)6, "已出库"),
    	Delivery((byte)7, "已发货"),    	
    	Completed((byte)9, "已完成"), 
    	Cancled((byte)10, "已取消"), 
    	Closed((byte)11, "已关闭"), 
    	ReturnedGoods((byte)12, "已退货");    	
		private final Byte value;
	    private final String content;
    	private OrderStatus(Byte value, String content) {
            this.value = value;
            this.content = content;
        }

        public Byte v() {
            return value;
        }

        public String c() {
            return content;
        }
    }
    /**
     * 订单商品是否改价
     * @author lyh
     *
     */
    public static enum OrderGoodsIsChangePrice {
    	NO(0,"否"),
    	YES(1,"是");   	
    	private final int value;
    	private final String content;
    	private OrderGoodsIsChangePrice(int value,String content){
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
    
    /**
     * 订单商品改价后的审核状态
     * @author lyh
     *
     */
    public static enum OrderChangePriceAuditStatus {
    	WaitingAudit(0,"待审核"),
    	Auditing(1,"审核中"),
    	AuditPass(2,"审核通过"), 	
    	AuditRefused(3,"审核拒绝");   	
    	private final int value;
    	private final String content;
    	private OrderChangePriceAuditStatus(int value,String content){
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
    /**
     * 订单支付状态
     * @author lyh
     *
     */
    public static enum PayStatus {
    	NotPay(1,"未支付"),
    	PayConfirmming(2,"支付确认中"), 	
    	PaySuccess(3,"支付成功");   	
    	private final int value;
    	private final String content;
    	private PayStatus(int value,String content){
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
    
    /**
     * 用户角色对应id
     * @author xieang
     * 2015年9月22日
     */
    public static enum UserRole {
    	ADMIN((byte)6, "admin角色"),
    	US((byte)5, "US角色"),
    	VS((byte)4, "VS角色"),
    	LC((byte)3, "LC角色"),
    	SE((byte)2, "SE角色"),
    	APlus((byte)1, "A+角色");
    	
    	
    	private final byte value;
    	private final String content;
    	
    	private UserRole(Byte value, String content) {
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
    
    /**1审核通过  2审核失败  0未审核（默认）
     * @author xieang
     * 2015年9月23日
     */
    public static enum AuthenticationState{
    	Success((byte)1,"审核通过"),
    	Failure((byte)2,"审核失败"),
    	Audit((byte)0,"未审核"),
    	;
    	
    	private final byte value;
    	private final String content;
    	
    	private AuthenticationState(Byte value, String content) {
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
    
    /**
     *审核 原因类型 
     * @author lxc
     *
     */
    public static enum LargeCustomerState{
        Large((byte)1,"大客户审核"),
        VS((byte)2,"vs审核"),
        LC((byte)3,"lc审核"),
        SE((byte)4,"se审核"),
        A((byte)5,"A+审核"),
        Admin((byte)6,"admin审核"),
        Other((byte)7,"other审核"),
        ;
        
        private final byte value;
        private final String content;
        
        private LargeCustomerState(Byte value, String content) {
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
    
    /**
     *     
     * @Title：PopStoreType 
     * @Description: PopStore入住类型   
     * @author: leixc       
     * @date: 20151014   
     * @version     
     *
     */
    public static enum PopStoreType{
        DS("DS","PopStore类型DS"),
        SP("SP","PopStore类型SP"),
        FS("FS","PopStore类型FS"),
        ;
        
        private final String value;
        private final String content;
        
        private PopStoreType(String value, String content) {
            this.value = value;
            this.content = content;
        }
        
        public String v() {
            return value;
        }
        
        public String c() {
            return content;
        }
    }
    
// 订单部分枚举
    
    /**
     *     
     * @Title：OrderNoType  
     * @Description: 订单号类型枚举   
     * @author: Ken        
     * @date: 2015年10月11日 下午2:20:52      
     * @version     
     *
     */
    public static enum OrderNoType{
        Master("0","主订单"),
        Sub("1","子订单"),
        MergeOnLine("2","合并线上"),
        MergeOffLine("3","合并线下");
        
        private final String value;
        private final String content;
        
        private OrderNoType(String value, String content) {
            this.value = value;
            this.content = content;
        }
        
        public String v() {
            return value;
        }
        
        public String c() {
            return content;
        }
    }
    
    /**
     *     
     * @Title：OrderNoChannelType  
     * @Description: 订单商户类型   
     * @author: Ken        
     * @date: 2015年10月11日 下午2:27:25      
     * @version     
     *
     */
    public static enum OrderNoChannelType{
        Self("01","自营"),
        Other("02","入驻商"),
        Mix("03","混合");
        
        private final String value;
        private final String content;
        
        private OrderNoChannelType(String value, String content) {
            this.value = value;
            this.content = content;
        }
        
        public String v() {
            return value;
        }
        
        public String c() {
            return content;
        }
    }
    
    public static enum OrderExpenseStatus{
        
        UnSettle(0,"未结算"),
        Settling(1,"结算中"),
        Settle(2,"已结算");
        
        private final Integer value;
        private final String content;
        
        private OrderExpenseStatus(Integer value, String content) {
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
     * 促销活动排序字段
     *     
     * @Title：PromotionQuerySort  
     * @Description: TODO   
     * @author: robo        
     * @date: 2015年10月23日 上午11:52:33      
     * @version     
     *
     */
    public static enum PromotionQuerySort {

        SalesCount(1, "sales_count"), ReleaseTime(2, "release_time"),CostPrice(3,"cost_price");

        private final int value;
        private final String content;

        private PromotionQuerySort(int value, String content) {
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
    
    /**
     * 排序方式 
     *     
     * @Title：SortDirection  
     * @Description: TODO   
     * @author: robo        
     * @date: 2015年10月23日 上午11:52:19      
     * @version     
     *
     */
    public static enum SortDirection{
    	ASC(1,"asc"),DESC(2,"desc");
    	private final int value;
        private final String content;

        private SortDirection(int value, String content) {
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
   /**
    * 
    *     
    * @Title：SortDirection  
    * @Description: TODO   红包状态
    * @author: ivan        
    * @date: 2015年10月29日 下午8:50:00      
    * @version     
    *
    */
    public static enum CouponStatus{
        Valid(0,"asc"),USED(1,"desc"),InValid(2,"");
        private final int value;
        private final String content;

        private CouponStatus(int value, String content) {
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
    /**
     * 
     *     
     * @Title：CouponType  
     * @Description: TODO   红包类型   
     * @author: ivan        
     * @date: 2015年10月30日 下午4:21:39      
     * @version     
     *
     */
    public static enum CouponType {
        REGISTER(1,"注册发放"),USERGROUP(2,"用户组"),ONLINE(3,"线上发放"),APP(4,"APP端"),COUPONPROMOTION(5,"红包活动");
        private final int value;
        private final String content;
        
        private CouponType (int value, String content) {
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
