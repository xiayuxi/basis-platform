package com.ync365.seed.bussiness.modules.goods.biz;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.ync365.seed.commons.solr.GoodsQueryBean;
import com.ync365.seed.commons.solr.SolrUtil;
import com.ync365.seed.utils.StringUtils;
import com.ync365.seed.web.vo.Grid;

@Service
public class GoodsQueryBiz {


	public Grid searchPage(GoodsQueryBean bean ,String content,int pageSize,int startIndex){
		Grid grid = new Grid() ;
		
		try {
			if(StringUtils.isNotBlank(content)){
				grid = SolrUtil.searchList(content, pageSize, startIndex);
			}else {
				grid =  SolrUtil.searchList(bean, pageSize, startIndex);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return grid;
	}
}
