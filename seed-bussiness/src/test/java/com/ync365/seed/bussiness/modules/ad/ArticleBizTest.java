package com.ync365.seed.bussiness.modules.ad;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ync365.seed.bussiness.modules.Base.BaseTest;
import com.ync365.seed.bussiness.modules.ad.biz.ArticleBiz;
import com.ync365.seed.bussiness.modules.ad.entity.Article;

public class ArticleBizTest extends BaseTest {

	@Autowired
	ArticleBiz articleBiz;
	
	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectArticlePageList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectArticleListCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectArticleList() {
		 List<Article> list = articleBiz.selectArticleList(6, 12) ;
		 System.out.println(list.size());
	}

}
