package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.dwb.test.framework.Constant;

import au.com.bytecode.opencsv.CSVReader;

public class CSVParser {

	private Logger logger = Logger.getLogger(CSVParser.class);

	private List<String> inputColumnName = new ArrayList<String>();
	private List<String> outputColumnName = new ArrayList<String>();
	private List<LinkedHashMap<String, String>> inputColumnValues = new ArrayList<LinkedHashMap<String, String>>();
	private List<LinkedHashMap<String, String>> outputColumnValues = new ArrayList<LinkedHashMap<String, String>>();

	private String action;


	public void parse(String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(fis, "GBK"));

		// csv中输入列和输出列的分隔符
		int outputIndex = 0;
		String line = null;
		String lineArray[];

		// parser action.
		if ((line = br.readLine()) != null) {
			this.action = line;
			action = action.replaceAll(",", "");
			logger.debug("action:" + action);
		}

		// parser table header.
		if ((line = br.readLine()) != null) {

			List<String> temp = separateComma(line);

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).startsWith("#")) {
					outputIndex = i;
					break;
				}
			}
			for (String s : temp) {
				if (s.startsWith("#")) {
					// 去掉#标识
					outputColumnName.add(s.substring(1));
				} else {
					inputColumnName.add(s);
				}
			}
			logger.debug(inputColumnName + "" + outputColumnName);
		}

		CSVReader reader = new CSVReader(br, ',', '\"', 0);
		while ((lineArray = reader.readNext()) != null) {

			LinkedHashMap<String, String> inputValuePerRow = new LinkedHashMap<String, String>();
			LinkedHashMap<String, String> outputValuePerRow = new LinkedHashMap<String, String>();
			for (int i = 0; i < lineArray.length; i++) {
				if (i < outputIndex) {
					inputValuePerRow.put(inputColumnName.get(i), lineArray[i]);
				} else {
					outputValuePerRow
							.put(outputColumnName.get(i - outputIndex),
									lineArray[i]);
				}
			}
			
			System.out.println("aaa"+inputValuePerRow.get(Constant.IGNORE_CASE));
			//去除标志为不执行 的case.
			if(inputValuePerRow.get(Constant.IGNORE_CASE.toLowerCase())!=null&&inputValuePerRow.get(Constant.IGNORE_CASE.toLowerCase()).equals(Constant.IGNORE_CASE_YES)){
				continue;
			}
			
			inputColumnValues.add(inputValuePerRow);
			outputColumnValues.add(outputValuePerRow);

			logger.debug(inputValuePerRow + "" + outputValuePerRow
					+ (inputValuePerRow.size() + outputValuePerRow.size()));
		}

		br.close();
		reader.close();
		fis.close();
	}

	private List<String> separateComma(String input) {

		String[] array = input.split(",");

		return Arrays.asList(array);

	}

	public int getTestCaseNumber() {
		return inputColumnValues.size();
	}

	public List<String> getInputColumnName() {
		return inputColumnName;
	}

	public void setInputColumnName(List<String> inputColumnName) {
		this.inputColumnName = inputColumnName;
	}

	public List<String> getOutputColumnName() {
		return outputColumnName;
	}

	public void setOutputColumnName(List<String> outputColumnName) {
		this.outputColumnName = outputColumnName;
	}

	public List<LinkedHashMap<String, String>> getInputColumnValues() {
		return inputColumnValues;
	}

	public void setInputColumnValues(
			List<LinkedHashMap<String, String>> inputColumnValues) {
		this.inputColumnValues = inputColumnValues;
	}

	public List<LinkedHashMap<String, String>> getOutputColumnValues() {
		return outputColumnValues;
	}

	public void setOutputColumnValues(
			List<LinkedHashMap<String, String>> outputColumnValues) {
		this.outputColumnValues = outputColumnValues;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
