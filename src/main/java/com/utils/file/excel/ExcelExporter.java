package com.utils.file.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ExcelExporter<E> implements FileExporter<E> {
	private Map<String, String> columnFieldMap = Maps.newTreeMap();
	private File toFile;
	private String sheetName;
	private Collection<E> objects;
	private int rowCount = 0;

	public ExcelExporter() {
	}

	public void export() {
		FileOutputStream xlsFile = null;
		Workbook wb = new SXSSFWorkbook();
		try {
			xlsFile = new FileOutputStream(this.toFile);
			Sheet sheet = wb.createSheet(this.sheetName);
			buildHeader(sheet);
			buildContent(sheet, objects);
			wb.write(xlsFile);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(xlsFile);
			IOUtils.closeQuietly(wb);
		}
		
	}

	private void buildContent(Sheet sheet, Collection<E> objects) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (E object : objects) {
			Row row = sheet.createRow(rowCount++);
			int index =0;
			for(String fieldName : columnFieldMap.values()) {
				String methodName = "get"+ StringUtils.capitalize(fieldName);
				Cell cell = row.createCell(index++);
				Method m = object.getClass().getMethod(methodName);
				Object value = m.invoke(object);
				if(value!=null) {
					cell.setCellValue(String.valueOf(value));
				} else {
					cell.setCellValue("");
				}
			}
		}
	}

	private void buildHeader(Sheet sheet) {
		Row row = sheet.createRow(rowCount++);
		int index = 0;
		for(String key: columnFieldMap.keySet()) {
			Cell cell = row.createCell(index);
			cell.setCellValue(key);
		}
	}

	public ExcelExporter<E> addColumn(String columnName, String fieldName) {
		columnFieldMap.put(columnName, fieldName);
		return this;
	}

	public File getToFile() {
		return toFile;
	}

	public ExcelExporter<E> setToFile(File toFile) {
		this.toFile = toFile;
		return this;
	}

	public String getSheetName() {
		return sheetName;
	}

	public ExcelExporter<E> setSheetName(String sheetName) {
		this.sheetName = sheetName;
		return this;
	}
	
	public Collection<E> getObjects() {
		return objects;
	}

	public ExcelExporter<E> setObjects(Collection<E> objects) {
		this.objects = objects;
		return this;
	}

	public static void main(String[] args) {
		File file = new File("test.xlsx");
		List<Person> persons = Lists.newArrayList();
		persons.add(new Person("a" , 11));
		persons.add(new Person("c" ,11));
		persons.add(new Person("b" ,11));
		FileExporter<Person> exporter = new ExcelExporter<Person>().setToFile(file).setSheetName("sheet1").
				addColumn("名字", "name").addColumn("年齡", "age").setObjects(persons);
		exporter.export();
	}

}

class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
