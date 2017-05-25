package handler;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class MyHandler extends DefaultHandler {

	private double surfaceTotal;
	private boolean surfaceComptabilise;
	private int profondeur;
	private int profondeurComptabilise;
	private String idMaison;
	
	public void startDocument() {
	this.profondeur = 0;
	}
	
	public void endDocument() {
		System.out.println();
	}
	
	public void startElement(String nameSpaceURI, String localName, String rawName, Attributes attributs) {
		if(localName == "maison"){
			this.idMaison = attributs.getValue("id");
			this.surfaceTotal = 0;
			this.surfaceComptabilise = false;
		}
		this.profondeur++;

		for(int i = 0; i < attributs.getLength(); i++){
			String name = attributs.getQName(i);
			if(name == "surface-m2"){
				if(this.surfaceComptabilise == false){
					this.surfaceComptabilise = true;
					this.surfaceTotal+= Double.parseDouble(attributs.getValue(i));
					this.profondeurComptabilise = this.profondeur;
				}
			}
		}
	}
	
	public void endElement(	String nameSpaceURI, String localName, String rawName)  {
		if(localName == "maison"){
			System.out.println("Maison "+this.idMaison+":");
			System.out.println("\tSurperficie totale: "+this.surfaceTotal+" m2.");
		}
		this.profondeur--;
		if(this.profondeurComptabilise >= this.profondeur){
			this.surfaceComptabilise = false;
			this.profondeurComptabilise = 0;
		}
	}
	
	public static void main(String[] args) {
		try {
			XMLReader saxReader = XMLReaderFactory.createXMLReader();
			saxReader.setContentHandler(new MyHandler());
			saxReader.parse(args[0]);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
}
