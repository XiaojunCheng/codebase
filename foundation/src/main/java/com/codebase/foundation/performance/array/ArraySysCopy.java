package com.codebase.foundation.performance.array;

import com.codebase.foundation.performance.Constants;
import com.codebase.foundation.performance.TestCase;

public class ArraySysCopy implements TestCase {

	public String getName() {
		return name;
	}

	final String	name	= "array-copy-syscpy-" + Constants.ARRAY_LIMIT_NAME;

	public ArraySysCopy() {

	}

	public void init() throws Exception {

	}

	public Object action() throws Exception {
		int len = Constants.INT_ARRAY_1.length;
		int[] a = Constants.INT_ARRAY_1;
		int[] b = Constants.INT_ARRAY_2;
		System.arraycopy(a, 0, b, 0, len);
		return len;
	}

}
