package com.bisoft.postgre;

public abstract class CSVItem implements ICSVItem{
	
	protected static String _filename = "";
	
	static public String readTitle() {
		return "";
	};
	
	static public String readFileName() {
		return _filename;
	};

	@Override
	public String readDataRow() {
		return "";
	}
}
