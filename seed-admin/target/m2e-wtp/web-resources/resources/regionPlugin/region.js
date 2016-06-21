var regionPlugin = {
		basePath:"",
		init:function(basePath){
			regionPlugin.basePath = basePath;
		},
		regionInit:function(){
			regionPlugin.regionInitParam({
			})
		},
		regionInitParam:function(param){
			param = param?param:{};
			var provinceId = param.provinceId?param.provinceId:"provinceRegion";
			var cityId = param.cityId?param.cityId:"cityRegion";
			var countyId = param.countyId?param.countyId:"countyRegion";
			var townId = param.townId?param.townId:"townRegion";
			var villageId = param.villageId?param.villageId:"villageRegion";
			
			var defaultProvinceId = param.defaultProvinceId?param.defaultProvinceId:null;
			var defaultCityId = param.defaultCityId?param.defaultCityId:null;
			var defaultCountyId = param.defaultCountyId?param.defaultCountyId:null;
			var defaultTownId = param.defaultTownId?param.defaultTownId:null;
			var defaultVillageId = param.defaultVillageId?param.defaultVillageId:null;
			
			var provinceRegionJQ = $("#"+provinceId);
			var cityRegionJQ = $("#"+cityId);
			var countyRegionJQ = $("#"+countyId);
			var townRegionJQ = $("#"+townId);
			var villageRegionsJQ = $("#"+villageId);
			
			$.ajax({
				type:"get",
				url:regionPlugin.basePath+"/region/getRegionByPid?pid=0",
				async:false,
				success : function(data){
					if(data){
						var object = $("#"+provinceId);
						object.empty();
						object.prepend("<option value=''>请选择</option>");
						$(data).each(function(item){
							object.append('<option value="'+this.id+'">'+this.name+'</option>');
						});
					}
				}
			});
			regionCleanSelect(cityId);
			regionCleanSelect(countyId);
			regionCleanSelect(townId);
			regionCleanSelect(villageId);
			if(defaultProvinceId){
				provinceRegionJQ.val(defaultProvinceId);
			}else if(defaultCityId){
				var regionList = selectAllParentRegionById(defaultCityId);
				provinceRegionJQ.val(regionList[0]);
				getRegionData(regionList[0],cityId);
				cityRegionJQ.val(regionList[1]);
			}else if(defaultCountyId){
				var regionList = selectAllParentRegionById(defaultCountyId);
				provinceRegionJQ.val(regionList[0]);
				getRegionData(regionList[0],cityId);
				cityRegionJQ.val(regionList[1]);
				getRegionData(regionList[1],countyId);
				countyRegionJQ.val(regionList[2]);
			}else if(defaultTownId){
				var regionList = selectAllParentRegionById(defaultTownId);
				provinceRegionJQ.val(regionList[0]);
				getRegionData(regionList[0],cityId);
				cityRegionJQ.val(regionList[1]);
				getRegionData(regionList[1],countyId);
				countyRegionJQ.val(regionList[2]);
				getRegionData(regionList[2],townId);
				townRegionJQ.val(regionList[3]);
			}else if(defaultVillageId){
				var regionList = selectAllParentRegionById(defaultVillageId);
				provinceRegionJQ.val(regionList[0]);
				getRegionData(regionList[0],cityId);
				cityRegionJQ.val(regionList[1]);
				getRegionData(regionList[1],countyId);
				countyRegionJQ.val(regionList[2]);
				getRegionData(regionList[2],townId);
				townRegionJQ.val(regionList[3]);
				getRegionData(regionList[3],villageId);
				villageRegionsJQ.val(regionList[4]);
			}
			
			if(cityRegionJQ.size()>0){
				provinceRegionJQ.change(function(){
					var region_id = $(this).children('option:selected').val();
					regionCleanSelect(cityId);
					regionCleanSelect(countyId);
					regionCleanSelect(townId);
					regionCleanSelect(villageId);
					getRegionData(region_id,cityId);
				});
			}
			if(countyRegionJQ.size()>0){
				cityRegionJQ.change(function(){
					var region_id = $(this).children('option:selected').val();
					regionCleanSelect(countyId);
					regionCleanSelect(townId);
					regionCleanSelect(villageId);
					getRegionData(region_id,countyId);
				});
			}
			if(townRegionJQ.size()>0){
				countyRegionJQ.change(function(){
					var region_id = $(this).children('option:selected').val();
					regionCleanSelect(townId);
					regionCleanSelect(villageId);
					getRegionData(region_id,townId);
				});
			}
			if(villageRegionsJQ.size()>0){
				townRegionJQ.change(function(){
					var region_id = $(this).children('option:selected').val();
					regionCleanSelect(villageId);
					getRegionData(region_id,villageId);
				});
			}
		},
		getAllRegionString:function(regionId){
			if(!regionId){
				return "";
			}
			var regionString = "";
			$.ajax({
				type:"get",
				url:regionPlugin.basePath+"/region/selectAllParentRegionStringById?pid="+regionId,
				async:false,
				success : function(data){
					regionString = data;
				}
			});
			return regionString;
		}
};
function selectAllParentRegionById(regionId){
	var regionList = [];
	$.ajax({
		type:"get",
		url:regionPlugin.basePath+"/region/selectAllParentRegionById?pid="+regionId,
		async:false,
		success : function(data){
			if(data){
				$(data).each(function(item){
					regionList.push(this.id);
				});
			}
		}
	});
	return regionList;
}
function getRegionData(pid,selectId){
	if(!pid){
		return;
	}
	$.ajax({
		type:"get",
		url:regionPlugin.basePath+"/region/getRegionByPid?pid="+pid,
		async:false,
		success : function(data){
			if(data){
				var object = $("#"+selectId);
				$(data).each(function(item){
					object.append('<option value="'+this.id+'">'+this.name+'</option>');
				});
			}
		}
	});
}
function regionCleanSelect(elementId){
	var object = $("#"+elementId);
	object.empty();
	object.prepend("<option value=''>请选择</option>"); //为Select插入一个Option(第一个位置) 
}
