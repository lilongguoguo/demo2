package com.common.district.common.utils;

import java.util.List;


/**
 * 分页的对象，以及分页页码输出
 * 
 */
@SuppressWarnings("serial")
public class Pagination<T> extends SimplePage implements java.io.Serializable, Paginable {

	public static Integer getCp(Integer pageNo,Integer pageSize){
		pageNo= StringUtils.isBlank(pageNo)?0:pageNo;
		pageSize=StringUtils.isBlank(pageSize)?10:pageSize;
		return (pageNo - 1 < 0 ? 0 : (pageNo - 1)) * pageSize;
	}

	public Pagination() {
	}

	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	public Pagination(int pageNo, int pageSize, int totalCount, List<T> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 当前页的数据
	 */
	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/** SOJSON SEO 翻页版本 */
	public String getWebPage(String page) {
		StringBuffer pageHtml = new StringBuffer("<ul class='pagination'>");
		if (this.getPageNo() > 1) {
			if (this.getPageNo() > 5) {
				pageHtml.append("<li><a href='javascript:;' onclick='" + page + "'>首页</a></li>");
			}
			pageHtml.append("<li><a href='" + page + "" + (this.getPageNo() - 1) + "'>上一页</a></li>");
		}
		for (int i = (this.getPageNo() - 2 <= 0 ? 1 : this.getPageNo() - 2), no = 1; i <= this.getTotalPage() && no < 6; i++, no++) {
			if (this.getPageNo() == i) {
				pageHtml.append("<li class='active'><a href='javascript:void(0);' >" + i + "</a></li>");
			} else {
				pageHtml.append("<li><a href='" + page + "" + i + "'>" + i + "</a></li>");
			}
		}
		if (this.getPageNo() < this.getTotalPage()) {
			pageHtml.append("<li><a href='" + page + "" + (this.getPageNo() + 1) + "'>下一页</a></li>");
		}
		pageHtml.append("</ul>");
		return pageHtml.toString();
	}

	/** Ajxa翻页 */
	public String getSiAjaxPageHtml() {
		StringBuffer pageHtml = new StringBuffer("<ul class='pagination'>");
		if (this.getPageNo() > 1) {
			if (this.getPageNo() > 5) {
				pageHtml.append("<li><a href='javascript:;' onclick='goPageByAjax(1)'>首页</a></li>");
			}
			pageHtml.append("<li><a href='javascript:;'  onclick='goPageByAjax(" + (this.getPageNo() - 1) + ")'>上一页</a></li>");
		}
		for (int i = (this.getPageNo() - 2 <= 0 ? 1 : this.getPageNo() - 2), no = 1; i <= this.getTotalPage() && no < 6; i++, no++) {
			if (this.getPageNo() == i) {
				pageHtml.append("<li class='active'><a href='javascript:void(0);' >" + i + "</a></li>");
			} else {
				pageHtml.append("<li><a href='javascript:;' onclick='goPageByAjax(" + i + ")'>" + i + "</a></li>");
			}
		}
		if (this.getPageNo() < this.getTotalPage()) {
			pageHtml.append("<li><a href='javascript:;'  onclick='goPageByAjax(" + (this.getPageNo() + 1) + ")'>下一页</a></li>");
		}
		pageHtml.append("</ul>");
		return pageHtml.toString();
	}

	/** 普通翻页 */
	public String getPageHtml() {
		StringBuffer pageHtml = new StringBuffer("<ul class='pagination'>");
		if (this.getPageNo() > 1) {
			if (this.getPageNo() > 5) {
				pageHtml.append("<li><a href='javascript:;' onclick='_submitform(1)'>首页</a></li>");
			}
			pageHtml.append("<li><a href='javascript:;'  onclick='_submitform(" + (this.getPageNo() - 1) + ")'>上一页</a></li>");
		}
		for (int i = (this.getPageNo() - 2 <= 0 ? 1 : this.getPageNo() - 2), no = 1; i <= this.getTotalPage() && no < 6; i++, no++) {
			if (this.getPageNo() == i) {
				pageHtml.append("<li class='active'><a href='javascript:void(0);' >" + i + "</a></li>");
			} else {
				pageHtml.append("<li><a href='javascript:;' onclick='_submitform(" + i + ")'>" + i + "</a></li>");
			}
		}
		if (this.getPageNo() < this.getTotalPage()) {
			pageHtml.append("<li><a href='javascript:;'  onclick='_submitform(" + (this.getPageNo() + 1) + ")'>下一页</a></li>");
		}
		pageHtml.append("</ul>");
		pageHtml.append("<script>");
		pageHtml.append("	function _submitform(pageNo){");
		pageHtml.append("		$(\"#formId\").append($(\"<input type='hidden' value='\" + pageNo +\"' name='pageNo'>\")).submit();");
		pageHtml.append("	}");
		pageHtml.append("</script>");

		return pageHtml.toString();
	}

	/**
	 * 
	 * @方法描述： ajax分页
	 * 
	 * @创建人：Kevin
	 * @创建时间：2017年2月28日 上午11:22:50
	 * @修改人：Kevin
	 * @修改时间：2017年2月28日 上午11:22:50 @修改备注： @param： @return
	 */
	public String getSiAjaxPageHtml2() {
		StringBuffer pageHtml = new StringBuffer("<div class=\"page\">");
		if (this.getPageNo() > 1) {
			if (this.getPageNo() > 5) {
				pageHtml.append("<a href='javascript:;' onclick='goPageByAjax(1)' class=\"page-head\">首页</a>");
			}
			pageHtml.append("<a href='javascript:;' class=\"no\" onclick='goPageByAjax(" + (this.getPageNo() - 1) + ")'>上一页</a></li>");
		}
		for (int i = (this.getPageNo() - 2 <= 0 ? 1 : this.getPageNo() - 2), no = 1; i <= this.getTotalPage() && no < 6; i++, no++) {
			if (this.getPageNo() == i) {
				pageHtml.append("<a href='javascript:void(0);' class=\"page-on\">" + i + "</a>");
			} else {
				pageHtml.append("<a href='javascript:;' class=\"page-on\" onclick='goPageByAjax(" + i + ")'>" + i + "</a>");
			}
		}
		if (this.getPageNo() < this.getTotalPage()) {
			pageHtml.append("<a href='javascript:;' class=\"num\" onclick='goPageByAjax(" + (this.getPageNo() + 1) + ")'>下一页</a>");
		}
		pageHtml.append("</div>");
		return pageHtml.toString();
	}

	/**
	 * 
	 * @方法描述： 普通翻页
	 * 
	 * @创建人：Kevin
	 * @创建时间：2017年2月28日 下午1:30:03
	 * @修改人：Kevin
	 * @修改时间：2017年2月28日 下午1:30:03 、 @修改备注： @param： @param
	 *                  formId查询FORM的ID @param： @return
	 */
	public String getPageHtml2(String formId) {
		if (StringUtils.isBlank(formId)) {
			formId = "searchForm";
		}
		StringBuffer pageHtml = new StringBuffer("<div class='page'>");
		if (this.getList()!=null && this.getList().size() > 0) {
			if (this.getPageNo() > 1) {
				if (this.getPageNo() > 5) {
					pageHtml.append("<a href='javascript:;' class=\"page-head\" onclick='_submitform(" + this.getTotalPage() + ",1)'>首页</a>");
				}
				pageHtml.append("<a href='javascript:;' class=\"num\" onclick='_submitform(" + this.getTotalPage() + "," + (this.getPageNo() - 1) + ")'>上一页</a>");
			}
			for (int i = (this.getPageNo() - 2 <= 0 ? 1 : this.getPageNo() - 2), no = 1; i <= this.getTotalPage() && no < 6; i++, no++) {
				if (this.getPageNo() == i) {
					pageHtml.append("<a href='javascript:void(0);' class=\"page-on\" >" + i + "</a>");
				} else {
					pageHtml.append("<a href='javascript:;' onclick='_submitform(" + this.getTotalPage() + "," + i + ")'>" + i + "</a>");
				}
			}
			if (this.getPageNo() < this.getTotalPage()) {
				pageHtml.append("<a href='javascript:;' class=\"num\" onclick='_submitform(" + this.getTotalPage() + "," + (this.getPageNo() + 1) + ")'>下一页</a>");
			}
			pageHtml.append("<span>总计" + this.getTotalPage() + "页,</span> ");
			pageHtml.append("<span>共" + this.getTotalCount() + "条记录</span> ");
			pageHtml.append("<span>到第 <input type=\"text\" id=\"changeNum\" style=\"width: 50px; display: inline;\" class=\"form-control\"> 页");
			pageHtml.append("</span><span>");
			pageHtml.append("	<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick='_submitform(" + this.getTotalPage() + ",$(\"#changeNum\").val())'>确定</button>");
			pageHtml.append("</span>");
			pageHtml.append("</div>");
			pageHtml.append("<script>"); 
			pageHtml.append("	function _submitform(totalPage,pageNo){");
			pageHtml.append("		if(pageNo>totalPage){");
			pageHtml.append("			msgAlert('跳转的页码超出总页码。');");
			pageHtml.append("			$(\"#changeNum\").val('');");
			pageHtml.append("		}else if(pageNo<=0 || isNaN(pageNo)){");
			pageHtml.append("			msgAlert('请输入正数。');");
			pageHtml.append("			$(\"#changeNum\").val('');");
			pageHtml.append("		}else{");
			pageHtml.append("			$(\"#" + formId + "\").append($(\"<input type='hidden' value='\" + pageNo +\"' name='pageNo'>\")).submit();");
			pageHtml.append("		}}");
			pageHtml.append("</script>");
		} else {
			pageHtml.append("抱歉，没有找到您想要的数据，请稍后重试……");
			pageHtml.append("</div>");
		}
		return pageHtml.toString();
	}


	/**
	 * @Description: 地区责任盘专用异步请求分页
	 * @MethodName: getPageHtmlWithSyncRequest
	 * @Author: WangGang
	 * @CreatDate: 2017年7月21日 下午6:01:48
	 * @Return: String
	 */
	public String getPageHtmlWithSyncRequest() {
		StringBuffer pageHtml = new StringBuffer("<div class='page'>");
		if (this.getList()!=null && this.getList().size() > 0) {
			if (this.getPageNo() > 1) {
				if (this.getPageNo() > 5) {
					pageHtml.append("<a href='javascript:;' class=\"page-head\" _pageNo='1' >首页</a>");
				}
				pageHtml.append("<a href='javascript:;' class=\"num\" _pageNo='" + (this.getPageNo() - 1) + "'>上一页</a>");
			}
			for (int i = (this.getPageNo() - 2 <= 0 ? 1 : this.getPageNo() - 2), no = 1; i <= this.getTotalPage() && no < 6; i++, no++) {
				if (this.getPageNo() == i) {
					pageHtml.append("<a href='javascript:void(0);' class=\"page-on\"  _pageNo='" + i + "'>" + i + "</a>");
				} else {
					pageHtml.append("<a href='javascript:;' _pageNo='" + i + "'>" + i + "</a>");
				}
			}
			if (this.getPageNo() < this.getTotalPage()) {
				pageHtml.append("<a href='javascript:;' class=\"num\" _pageNo='" + (this.getPageNo() + 1) + "'>下一页</a>");
			}
			pageHtml.append("<span>总计" + this.getTotalPage() + "页,</span> ");
			pageHtml.append("<span>共" + this.getTotalCount() + "条记录</span> ");
			pageHtml.append("<span>到第 <input type=\"text\" id=\"changeNum\" _totalPage=\"" +this.getTotalPage()+ "\" style=\"width: 50px; display: inline;\" class=\"form-control\"> 页");
			pageHtml.append("</span><span>");
			pageHtml.append("	<button id=\"btnSearchData\" type=\"button\" class=\"btn btn-primary btn-sm\">确定</button>");
			pageHtml.append("</span>");
			pageHtml.append("</div>");
		} else {
			pageHtml.append("抱歉，没有找到您想要的数据，请稍后重试……");
			pageHtml.append("</div>");
		}
		return pageHtml.toString();
	}
}
