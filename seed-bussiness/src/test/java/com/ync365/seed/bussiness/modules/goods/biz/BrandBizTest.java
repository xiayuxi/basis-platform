package com.ync365.seed.bussiness.modules.goods.biz;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.goods.entity.Brand;


public class BrandBizTest extends BaseTest {

	@Autowired
	private BrandBiz brandBiz;
	
	@Test
	public void testAddBrand() {
		Brand brand = new Brand();
		brand.setChName("测试中文名11");
		brand.setEnName("test111");
		brand.setSeq((short)50);
		brand.setStatus((short)1);
		brand.setAddress("www.test.com");
		
		int amount = brandBiz.addBrand(brand);
		Assert.assertEquals(1, amount);
	}

	@Test
	public void testSearchPage() {
		
		String qChName = null;
		int startIndex = 0;
		int pageSize = 10; 
		
		System.out.println("==================================================="+
		brandBiz.selectPage(qChName, startIndex, pageSize));
	}

	@Test
	public void testSearchPageCount() {
		long a = brandBiz.selectPageCount("山");
		System.out.println(a);
		Assert.assertEquals(a, 31);
	}

	@Test
	public void testDeleteById() {
		int a = brandBiz.deleteById(4032);
		System.out.println(a);
	}

	@Test
	public void testSelectById() {
		String a = brandBiz.selectById(13).getEnName();
		System.out.println(a);
		//Assert.assertEquals(a, 'J');
	}

	@Test
	public void testEdit() {
		Brand brand = brandBiz.selectById(4032);
		brand.setChName("测试中文名1111111");
		brandBiz.edit(brand);
		System.out.println(brandBiz.selectById(4032).getChName());
	}

	/**
	 * 
	 * @Title: testRelate
	 * @Description: TODO    ：没用    
	 * @author: xudawei/14070696    
	 * @date: 2015年9月21日 下午7:32:46       
	 * @version: 
	 *
	 *
	 */
	@Test
	public void testRelate() {
		fail("Not yet implemented");
	}

	/**
	 * 没用
	 * @Title: testGetAllbrandList
	 * @Description: TODO    ：    
	 * @author: xudawei/14070696    
	 * @date: 2015年9月21日 下午7:33:03       
	 * @version: 
	 *
	 *
	 */
	@Test
	public void testGetAllbrandList() {
		fail("Not yet implemented");
	}
	
    @Test
    public void testSelectByChName() {
        String chName = "金";
        System.out.println("================================"+brandBiz.selectByChName(chName));
    }

}
