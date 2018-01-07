package com.demo.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class ItextpdfTest {
	private static final String PARENT = "E:\\projects\\zww\\mybatis\\src\\test\\resources";

	@Test
	public void readPdf() {
		try {
			String pathname = "E:\\TDDOWNLOAD\\尚德资料\\中国近现代史\\课件\\题海大战.pdf";
			PdfReader reader = new PdfReader(pathname);
			int pages = reader.getNumberOfPages();
			for (int i = 0; i < pages; i++) {
				String text = PdfTextExtractor.getTextFromPage(reader, i + 1);
				System.err.println(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void writePdf() {
		File file = new File(PARENT, "test.pdf");
		try (OutputStream os = new FileOutputStream(file)) {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, os);
			document.open();
			BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font font = new Font(baseFont);
			Element element = new Paragraph("你的名字", font);
			document.add(element);
			document.close();
			writer.close();
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
