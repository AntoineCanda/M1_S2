package car.tp2;


import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTPFile;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.Node;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Form;
import com.hp.gagawa.java.elements.Input;
import com.hp.gagawa.java.elements.Label;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Meta;
import com.hp.gagawa.java.elements.Script;
import com.hp.gagawa.java.elements.Span;
import com.hp.gagawa.java.elements.Style;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.Ul;
/**
 * Classe ConstructeurHTML basé sur la lib Gagawa disponible à l'adresse suivante : 
 * https://code.google.com/archive/p/gagawa/
 * 
 * La classe repose sur le design pattern Singleton 
 * @author antoine
 *
 */
public class ConstructeurHTML {

	/** Constructeur privee */
	private ConstructeurHTML(){
		super();
	}
	
	/** Holder */
	private static class SingletonHolder
	{		
		/** Instance unique non préinitialisée */
		private final static ConstructeurHTML instance = new ConstructeurHTML();
	}
	
	// Les icones ont ete trouves sur internet libre d'acces et le code a ete minifier 
	/** */
	private static final String SUPPRIMER_ICONE = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"> <path d=\"M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2v-12h-12v12zm13-15h-3.5l-1-1h-5l-1 1h-3.5v2h14v-2z\"/> <path d=\"M0 0h24v24h-24z\" fill=\"none\"/></svg>";

	private static final String TELECHARGER_ICONE = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"> <path d=\"M19 9h-4v-6h-6v6h-4l7 7 7-7zm-14 9v2h14v-2h-14z\"/> <path d=\"M0 0h24v24h-24z\" fill=\"none\"/></svg>";

	private static final String RENOMMER_ICONE = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"> <path d=\"M0 0h24v24h-24z\" fill=\"none\"/> <path d=\"M19.43 12.98c.04-.32.07-.64.07-.98s-.03-.66-.07-.98l2.11-1.65c.19-.15.24-.42.12-.64l-2-3.46c-.12-.22-.39-.3-.61-.22l-2.49 1c-.52-.4-1.08-.73-1.69-.98l-.38-2.65c-.03-.24-.24-.42-.49-.42h-4c-.25 0-.46.18-.49.42l-.38 2.65c-.61.25-1.17.59-1.69.98l-2.49-1c-.23-.09-.49 0-.61.22l-2 3.46c-.13.22-.07.49.12.64l2.11 1.65c-.04.32-.07.65-.07.98s.03.66.07.98l-2.11 1.65c-.19.15-.24.42-.12.64l2 3.46c.12.22.39.3.61.22l2.49-1c.52.4 1.08.73 1.69.98l.38 2.65c.03.24.24.42.49.42h4c.25 0 .46-.18.49-.42l.38-2.65c.61-.25 1.17-.59 1.69-.98l2.49 1c.23.09.49 0 .61-.22l2-3.46c.12-.22.07-.49-.12-.64l-2.11-1.65zm-7.43 2.52c-1.93 0-3.5-1.57-3.5-3.5s1.57-3.5 3.5-3.5 3.5 1.57 3.5 3.5-1.57 3.5-3.5 3.5z\"/></svg>";

	private static final String DIR_ICONE = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path d=\"M10 4h-6c-1.1 0-1.99.9-1.99 2l-.01 12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2v-10c0-1.1-.9-2-2-2h-8l-2-2z\"/><path d=\"M0 0h24v24h-24z\" fill=\"none\"/></svg>";
	
	private static final String FILE_ICONE ="<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\"><path d=\"M6 2c-1.1 0-1.99.9-1.99 2l-.01 16c0 1.1.89 2 1.99 2h12.01c1.1 0 2-.9 2-2v-12l-6-6h-8zm7 7v-5.5l5.5 5.5h-5.5z\"/><path d=\"M0 0h24v24h-24z\" fill=\"none\"/></svg>";
	
	private static final String JAVASCRIPT_CODE = "function supprimerRepertoire(a,b){var c=new XMLHttpRequest;c.open(\"DELETE\",\"/rest/tp2/ftp/\"+a+b,!1),c.setRequestHeader(\"Content-Type\",\"text/plain\"),c.send(null),document.location.reload(!0)}function supprimerFichier(a,b){var c=new XMLHttpRequest;c.open(\"DELETE\",\"/rest/tp2/ftp/file/\"+a+b,!1),c.setRequestHeader(\"Content-Type\",\"text/plain\"),c.send(null),document.location.reload(!0)}function renommerFichier(a,b){for(var n=null;null==n||\"\"==n;)n=prompt(\"Renommer le fichier :\",b);var c=new XMLHttpRequest;c.open(\"PUT\",\"/rest/tp2/ftp/file/\"+a+b,!1),c.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\"),c.send(\"newName=\"+n),document.location.reload(!0)}function renommerRepertoire(a,b){for(var n=null;null==n||\"\"==n;)n=prompt(\"Renommer le dossier :\",b);var c=new XMLHttpRequest;c.open(\"PUT\",\"/rest/tp2/ftp/\"+a+b,!1),c.setRequestHeader(\"Content-Type\",\"application/x-www-form-urlencoded\"),c.send(\"newName=\"+n),document.location.reload(!0)}";
 
	private static final String CSS = "*,::before,:after{-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box}.ftp-files{position:relative;margin:0 auto;width:1000px}.ftp-files ul{list-style:none;padding:0;width:100%}.ftp-files li{margin:0 auto;position:relative}.ftp-files li:hover{box-shadow:inset 0 0 0 40px rgba(70,145,246,.1)}.ftp-files li.description{border-bottom:1px solid #5e5e5e}.ftp-files li.description:hover{box-shadow:none}.ftp-files li.description label{font-weight:700}.ftp-files label{display:inline-block;width:100%;position:relative;font-size:1.2em;padding:15px 10px 15px 80px;vertical-align:top}.ftp-files label span{vertical-align:middle;display:inline-block;color:#5e5e5e}.ftp-files label span:first-child{width:45%}.ftp-files label a,.ftp-files label span:nth-child(2){width:15%}.ftp-files label span:nth-child(3){width:25%}.ftp-files label span.directory a{color:#4691f6;text-decoration:none}.ftp-files label span.directory a:hover{color:#4691f6;text-decoration:underline}.ftp-files .file-type-icon{width:26px;height:26px;top:50%;left:20px;margin-top:-13px;position:absolute}.ftp-files .delete-icon,.ftp-files .download-icon,.ftp-files .rename-icon{vertical-align:middle;display:inline-block;float:right;width:40px;height:30px;cursor:pointer}.svg-icon{width:100%;height:100%}.svg-icon path,.svg-icon polygon,.svg-icon rect{fill:#4691f6}.svg-icon circle{stroke:#4691f6;stroke-width:1}.delete-icon .svg-icon path,.delete-icon .svg-icon polygon,.delete-icon .svg-icon rect{fill:#ff9300}.delete-icon .svg-icon circle{stroke:#ff9300}input[type=file]{display:inline-block;width:360px;color:#5e5e5e}input[type=text]{display:inline-block;padding:.1em 0;width:360px;border:none;border-bottom:1px solid #5e5e5e;background:0 0;color:#5e5e5e}input[type=text]:focus{outline:0}input[type=submit]{display:inline-block;padding:.1em .5em;margin-left:50px;border:none;border:1px solid #5e5e5e;cursor:pointer;background:0 0}@media screen and (max-width:50em){section{font-size:80%}}article,aside,details,figcaption,figure,footer,header,hgroup,main,nav,section,summary{display:block;}audio,canvas,video{display:inline-block;}audio:not([controls]){display:none;height:0;}[hidden]{display:none;}html{font-family:sans-serif;-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%;}body{margin:0;}a:focus{outline:thin dotted;}a:active,a:hover{outline:0;}h1{font-size:2em;margin:0.67em 0;}abbr[title]{border-bottom:1px dotted;}b,strong{font-weight:bold;}dfn{font-style:italic;}hr{-moz-box-sizing:content-box;box-sizing:content-box;height:0;}mark{background:#ff0;color:#000;}code,kbd,pre,samp{font-family:monospace,serif;font-size:1em;}pre{white-space:pre-wrap;}q{quotes:\"\201C\" \"\201D\" \"\2018\" \"\2019\";}small{font-size:80%;}sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline;}sup{top:-0.5em;}sub{bottom:-0.25em;}img{border:0;}svg:not(:root){overflow:hidden;}figure{margin:0;}fieldset{border:1px solid #c0c0c0;margin:0 2px;padding:0.35em 0.625em 0.75em;}legend{border:0;padding:0;}button,input,select,textarea{font-family:inherit;font-size:100%;margin:0;}button,input{line-height:normal;}button,select{text-transform:none;}button,html input[type=\"button\"],input[type=\"reset\"],input[type=\"submit\"]{-webkit-appearance:button;cursor:pointer;}button[disabled],html input[disabled]{cursor:default;}input[type=\"checkbox\"],input[type=\"radio\"]{box-sizing:border-box;padding:0;}input[type=\"search\"]{-webkit-appearance:textfield;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;box-sizing:content-box;}input[type=\"search\"]::-webkit-search-cancel-button,input[type=\"search\"]::-webkit-search-decoration{-webkit-appearance:none;}button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0;}textarea{overflow:auto;vertical-align:top;}table{border-collapse:collapse;border-spacing:0;}";
	
	/** Point d'accès pour l'instance unique du singleton */
	public static ConstructeurHTML getInstance()
	{
		return SingletonHolder.instance;
	}
	
	
	public String build(String path, FTPFile[] files){
		//Creation du document
		Document doc = new Document(DocumentType.XHTMLTransitional);
		
		//Creation du charset et ajout a l'entete du document
		Meta charset = new Meta(null);
		charset.setAttribute("charset", "UTF-8");
		doc.head.appendChild(charset);
		
		//Creation et ajout du titre
		
		Title titre = new Title();
		Text text = new Text("TP Passerelle REST");
		titre.appendChild(text);
		doc.head.appendChild(titre);
		
		//creation et ajout d'un style css
		
		Style style = new Style("text/css").appendText(CSS);
		
		doc.head.appendChild(style);
		
		Body body = doc.body;
		
		Div container = new Div();
		container.setCSSClass("ftp-files");
		body.appendChild(container);
		
		// Creation de la liste des fichiers/repertoire avec l'arborescence DOM
		Ul ul = new Ul();
		container.appendChild(ul);
		
		Li li = new Li();
		li.setCSSClass("description");
		ul.appendChild(li);
		
		Label lab = new Label();
		li.appendChild(lab);
		
		Span nom = new Span();
		nom.appendText("Nom");
		lab.appendChild(nom);
		
		Span taille = new Span();
		taille.appendText("Taille");
		lab.appendChild(taille);
		
		Span dateModif = new Span();
		dateModif.appendText("Date derniere modification");
		lab.appendChild(dateModif);
		
		if(!path.isEmpty()){
			Li repertoireParent = new Li();
			ul.appendChild(repertoireParent);
			
			Div icone = new Div();
			icone.setCSSClass("file-type-icon");
			icone.appendText(DIR_ICONE);
			repertoireParent.appendChild(icone);
			
			Label labelRepParent = new Label();
			repertoireParent.appendChild(labelRepParent);
			
			int indexSlash = path.substring(0, path.length() - 1).lastIndexOf('/');
			String repParentPath;
			if(indexSlash == -1){
				repParentPath="";
			}
			else{
				repParentPath = path.substring(0, indexSlash+1);
			}
			
			String ref = "/rest/tp2/ftp/"+repParentPath;
			
			Span repParent = new Span();
			A lienParent = new A();
			lienParent.setHref(ref);
			lienParent.appendText("Retour vers le répertoire parent");
			repParent.setCSSClass("directory");
			repParent.appendChild(lienParent);
			labelRepParent.appendChild(repParent);
		}
		
		//on cree les noeuds li corresppondant au fichier
		for(FTPFile file: files){
			ul.appendChild(this.build(path,file));
		}
		
		// formulaire ajout fichier repertoire courant
		
		Li newFile = this.buildFormAjoutFile(path);
		ul.appendChild(newFile);

		// Formulaire ajout dossier dans le repertoire courant
		
		Li newDir = this.buildFormAjoutDir(path);
		ul.appendChild(newDir);
		
		//ajoute le javascript
		Script javascript = new Script("text/javascript");
		javascript.appendText(JAVASCRIPT_CODE);
		body.appendChild(javascript);
		
		//Retourne le code genere
		return doc.write();
	}
	
	private Li buildFormAjoutFile(String path){
		Li newFile = new Li();
		
		Label labNewFile = new Label();
		newFile.appendChild(labNewFile);
		
		Form form = new Form("Ajout fichier");
		form.setAction("/rest/tp2/ftp/file/"+path);
		form.setMethod("POST");
		form.setEnctype("multipart/form-data");
		labNewFile.appendChild(form);
		
		Span span = new Span();
		span.appendText("Ajouter un fichier au répertoire courant");
		form.appendChild(span);
		
		Input input = new Input();
		input.setType("file");
		input.setId("fileStream");
		input.setName("fileStream");
		form.appendChild(input);
		
		Input submit = new Input();
		submit.setType("submit");
		submit.setValue("Valider");
		form.appendChild(submit);
		
		return newFile;
	}

	private Li buildFormAjoutDir(String path){
		Li newDir = new Li();

		Label labNewDir = new Label();
		newDir.appendChild(labNewDir);
		
		Form form = new Form("Ajout fichier");
		form.setAction("/rest/tp2/ftp/"+path);
		form.setMethod("POST");
		labNewDir.appendChild(form);
		
		Span span = new Span();
		span.appendText("Ajouter un dossier au répertoire courant");
		form.appendChild(span);
		
		Input input = new Input();
		input.setType("text");
		input.setName("dirName");
		input.setAttribute("placeholder", "Nom du fichier");
		form.appendChild(input);
		
		Input submit = new Input();
		submit.setType("submit");
		submit.setValue("Valider");
		form.appendChild(submit);
		
		return newDir;
	}

	private Node build(String path, FTPFile file) {
		if(file.isDirectory()){
			return this.buildRepertoire(path,file);
		}
		else{
			return this.buildFichier(path,file);
		}
	}

	/**
	 * Construit le code HTML pour representer le repertoire passe en parametre
	 * @param path path vers le repertoire
	 * @param file le repertoire considere
	 * @return balise html corresppondant a une enumeration d'une liste (li)
	 */
	private Node buildRepertoire(String path, FTPFile file) {
		Li li = new Li();
		
		Div icone = new Div();
		icone.setCSSClass("file-type-icon");
		icone.appendText(DIR_ICONE);
		li.appendChild(icone);
		
		Label lab = new Label();
		li.appendChild(lab);
		
		// Nom du repertoire et lien A
		Span name = new Span();
		name.setCSSClass("directory");
		A lien = new A();
		lien.setHref(file.getName()+"/");
		lien.appendText(file.getName());
		name.appendChild(lien);
		lab.appendChild(name);
		
		// Taille du repertoire
		Span taille = new Span();
		taille.appendText(String.valueOf(file.getSize()));
		lab.appendChild(taille);
		
		// Date derniere modif 
		Span dateModif = new Span();
		dateModif.appendText(new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(file.getTimestamp().getTime()));
		lab.appendChild(dateModif);
		
		Div supprimer = new Div();
		supprimer.setAttribute("onClick", "supprimerRepertoire('"+path+"','"+file.getName()+"')");
		supprimer.setCSSClass("delete-icon");
		supprimer.appendText(SUPPRIMER_ICONE);
		lab.appendChild(supprimer);
		
		Div renommer = new Div();
		renommer.setAttribute("onClick", "renommerRepertoire('"+path+"','"+file.getName()+"')");
		renommer.setCSSClass("rename-icon");
		renommer.appendText(RENOMMER_ICONE);
		lab.appendChild(renommer);
		
		return li;
	}

	/**
	 * Construit le code HTML pour representer un fichier passe en parametre
	 * @param path path vers le fichier
	 * @param file fichier du serveur considere
	 * @return balise html corresppondant a une enumeration d'une liste (li)
	 */
	private Node buildFichier(String path, FTPFile file) {
		Li li = new Li();
		
		Div icone = new Div();
		icone.setCSSClass("file-type-icon");
		icone.appendText(FILE_ICONE);
		li.appendChild(icone);
		
		Label lab = new Label();
		li.appendChild(lab);
		
		Span nom = new Span();
		nom.appendText(file.getName());
		lab.appendChild(nom);
		
		Span taille = new Span();
		taille.appendText(String.valueOf(file.getSize()));
		lab.appendChild(taille);
		
		Span dateModif = new Span();
		dateModif.appendText(new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(file.getTimestamp().getTime()));
		lab.appendChild(dateModif);
		
		// faire supprimmer (notamment javascript)
		
		Div supprimer = new Div();
		supprimer.setCSSClass("delete-icon");
		supprimer.setAttribute("onClick", "supprimerFichier('"+path+"','"+file.getName()+"')");
		supprimer.appendText(SUPPRIMER_ICONE);
		lab.appendChild(supprimer);
		
		// faire renommer (notamment javascript)
		
		Div renommer = new Div();
		renommer.setCSSClass("rename-icon");
		renommer.setAttribute("onClick", "renommerFichier('" + path + "', '" + file.getName() + "')");
		renommer.appendText(RENOMMER_ICONE);
		lab.appendChild(renommer);
		
		// faire telecharger
		
		A telecharger = new A();
		telecharger.setCSSClass("download-icon");
		telecharger.setHref("/rest/tp2/ftp/file/"+path+file.getName());
		telecharger.appendText(TELECHARGER_ICONE);
		lab.appendChild(telecharger);
		
		return li;
	}
}
