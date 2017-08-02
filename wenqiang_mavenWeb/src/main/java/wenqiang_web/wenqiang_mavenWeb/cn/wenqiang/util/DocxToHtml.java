package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;


public  class DocxToHtml {

	public static String  getHtml(String sppuer,String url) throws TransformerException, IOException, ParserConfigurationException{
		
		if("doc".equals(sppuer)){
			
		return	convertToHtml(url);
		}
		if("docx".equals(sppuer)){
		 return	doGenerateSysOut(url);
		}
		return null;
	}
	  
	
	public static String convertToHtml(String fileName)
			throws TransformerException, IOException,
			ParserConfigurationException {
	
		  UUID uuid = UUID.randomUUID();
	String basedir="E:/upload/image/htmlFile/";
		File imageFolder = new File(basedir);
		if (!imageFolder.exists()) {
			imageFolder.mkdirs();
		}
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
		// WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder()
						.newDocument());
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType,
					String suggestedName, float widthInches, float heightInches) {
				return suggestedName;
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		// save pictures
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		Map<String,String> m=new HashMap<String,String>();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);

				try {
					String newName =uuid
							+ pic.suggestFullFileName();
					pic.writeImageContent(new FileOutputStream(imageFolder
							+ "//" + newName));
					m.put(pic.suggestFullFileName(), newName);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(out);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		out.close();
		String fileOutName =  basedir+"/"+uuid+ ".html";
		String content = new String(out.toByteArray());
		for (String key : m.keySet()) {
			content=content.replaceAll("\""+key+"\"", "\""+m.get(key)+"\"");
		}
		
		writeFile(content, fileOutName);
		return fileOutName;
	}
	
	public static  void writeFile(String content, String path) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			bw.write(content);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (IOException ie) {
			}
		}
	}

	
	/**
	 *
	 * @param wordName word文件路径
	 * @return
	 * @throws IOException
	 */
	public static String doGenerateSysOut(String wordName) throws IOException {

		  UUID uuid = UUID.randomUUID();
		  
		  String basedir="E:/upload/image/htmlFile/";
		File dirFile=new File(basedir);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File imageFolder = new File(basedir+"/"+uuid);

		String fileOutName =  basedir+"/"+uuid+ ".html";
		XWPFDocument document = new XWPFDocument(new FileInputStream(wordName));
		XHTMLOptions options = XHTMLOptions.create().indent(4);
		// Extract image
		options.setExtractor(new FileImageExtractor(imageFolder));
		// URI resolver
		options.URIResolver(new FileURIResolver(imageFolder));
		File outFile = new File(fileOutName);
		OutputStream out = new FileOutputStream(outFile);
		XHTMLConverter.getInstance().convert(document, out, options);
		return fileOutName;
	}
	
	
}
