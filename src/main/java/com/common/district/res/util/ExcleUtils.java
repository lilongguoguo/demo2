package com.common.district.res.util;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @author tanwei
 * @create 2019年8月1日
 */
public class ExcleUtils {

	private static Logger logger = LoggerFactory.getLogger(ExcleUtils.class);

	public static Workbook getWorkBook(MultipartFile file) {
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = file.getInputStream();
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007 及2007以上
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return workbook;
	}

	/**
	 * 时间格式处理
	 * 
	 * @return
	 * @author Liu Xin Nan
	 * @data 2017年11月27日
	 */
	public static String stringDateProcess(Cell cell) {
		String result = new String();
		if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
			SimpleDateFormat sdf = null;
			if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
				sdf = new SimpleDateFormat("HH:mm");
			} else {// 日期
				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			}
			Date date = cell.getDateCellValue();
			result = sdf.format(date);
		} else if (cell.getCellStyle().getDataFormat() == 58) {
			// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			double value = cell.getNumericCellValue();
			Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
			result = sdf.format(date);
		} else {
			double value = cell.getNumericCellValue();
			CellStyle style = cell.getCellStyle();
			DecimalFormat format = new DecimalFormat();
			String temp = style.getDataFormatString();
			// 单元格设置成常规
			if (temp.equals("General")) {
				format.applyPattern("#.##");
			}
			result = format.format(value);
		}

		return result;
	}

	/**
	 * 检查文件
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static boolean checkFile(MultipartFile file) throws IOException {
		boolean result = false ;
		// 判断文件是否存在
		if (null == file) {
			return result;
		}
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是excel文件
		if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
			return result;
		}
		return true;
	}
	
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = stringDateProcess(cell);
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}
	
	public static void downExcel(HttpServletRequest request, HttpServletResponse response
			, Map<String, Object> map, String destFileName , String templateName){
		XLSTransformer transformer = new XLSTransformer();
		InputStream in = null;
		OutputStream out = null;
		// 设置响应
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(destFileName, "UTF-8"));
			response.setContentType("application/vnd.ms-excel");
			in = ExcleUtils.class.getResourceAsStream(templateName);
			Workbook workbook = transformer.transformXLS(in, map);
			// 动态添加题头列
			out = response.getOutputStream();
			// 将内容写入输出流并把缓存的内容全部发出去
			workbook.write(out);
			out.flush();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
