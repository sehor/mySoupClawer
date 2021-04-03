package clawer.extractor;

import org.jsoup.nodes.Element;

public class Webpage {

	private Element element;
	private String url;
	
	public Webpage(Element element,String url) {
		this.element=element;
		this.url=url;
	}
	
	public Element getElement() {
		return element;
	}

	public String getUrl() {
		return url;
	}

	
}
