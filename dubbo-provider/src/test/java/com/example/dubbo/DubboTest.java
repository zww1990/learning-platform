package com.example.dubbo;

import org.junit.Test;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Filter;

public class DubboTest {
	@Test
	public void main() {
		try {
			ExtensionLoader<Filter> f = ExtensionLoader.getExtensionLoader(Filter.class);
			System.err.println(f.getLoadedExtensions());
			System.err.println(f.getSupportedExtensions());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
