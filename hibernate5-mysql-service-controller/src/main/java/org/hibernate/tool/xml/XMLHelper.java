package org.hibernate.tool.xml;

import org.dom4j.io.SAXReader;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

public final class XMLHelper {

	public static SAXReader createSAXReader(ErrorHandler errorHandler) {
		SAXReader saxReader = resolveSAXReader();
		saxReader.setErrorHandler(errorHandler);
		return saxReader;
	}

	private static SAXReader resolveSAXReader() {
		SAXReader saxReader = new SAXReader();
		saxReader.setMergeAdjacentText(true);
		saxReader.setValidation(false);
		saxReader.setEntityResolver((publicId, systemId) -> {
			return new InputSource(
					XMLHelper.class.getResourceAsStream("/org/hibernate/hibernate-reverse-engineering-3.0.dtd"));
		});
		return saxReader;
	}

}
