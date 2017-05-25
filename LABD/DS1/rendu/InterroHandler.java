import org.xml.sax.* ;
import org.xml.sax.helpers.* ;

public class InterroHandler extends DefaultHandler {	
	private int n ;
	private boolean b ;
	
	public void startDocument() {this.n = 0 ; this.b = false ;}
	
	public void endDocument() {System.out.println(n) ;}
	
	public void startElement(String nameSpaceURI, 
							 String localName, 
							 String rawName, 
							 Attributes attributs)  { 
		if (this.b) n++ ; this.b = false ; 
	}
	
	public void endElement(	String nameSpaceURI, 
						   String localName, 
						   String rawName)  {
		this.b = true ;
	}
	
	public static void main(String[] args) {
		try {
			XMLReader saxReader = XMLReaderFactory.createXMLReader();
			saxReader.setContentHandler(new InterroHandler());
			saxReader.parse(args[0]);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}	
}
