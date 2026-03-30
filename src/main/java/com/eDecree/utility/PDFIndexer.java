package com.eDecree.utility;


import java.io.File;
//pdf indexer
import java.io.IOException;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
public class PDFIndexer {
	public IndexItem index(File file) throws IOException {
        PDDocument doc = PDDocument.load(file);
        String content = new PDFTextStripper().getText(doc);
        doc.close();
        return new IndexItem((long)file.getName().hashCode(), file.getName(), content);
    }

}
