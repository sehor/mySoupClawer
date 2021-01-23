package clawer.extractor.website.kankan;

import java.time.LocalDate;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import clawer.extractor.InfoExtractor;

public class InfoExtractor_KanKan implements InfoExtractor {

	private final String webName = "KanKan";
	private final String rootUrl = "https://www.kuaikanmanhua.com/tag/0";

	@Override
	public String etrName(Element infoNode) {
		// TODO Auto-generated method stub

		return infoNode.select(" div.TopicHeader.cls > div.right.fl > h3").text();
	}

	@Override
	public String etrAuthor(Element infoNode) {
		// TODO Auto-generated method stub
		return infoNode.select("div.TopicHeader.cls > div.right.fl > div.nickname").text();
	}

	@Override
	public String etrBrief(Element infoNode) {
		// TODO Auto-generated method stub
		return infoNode.select("div.comicIntro > div > div > p").text();
	}

	@Override
	public String etrCoverImageUrl(Element infoNode) {
		// TODO Auto-generated method stub
		Element el=infoNode.select("div.left.fl > img.imgCover").first();
    
		return el.attr("src");
	}

	@Override
	public String etrChapterName(Element infoNode) {
		// TODO Auto-generated method stub
		List<TextNode> textNodes=infoNode.selectFirst("div.titleBox.cls > h3").textNodes();
		String text=textNodes.get(textNodes.size()-1).text();
		return text;
	}

	@Override
	public String etrImageName(Element infoNode) {
		// TODO Auto-generated method stub
		return "noname";
	}

	@Override
	public LocalDate etrImagePublishDate(Element infoNode) {
		// TODO Auto-generated method stub
		return LocalDate.of(2020, 1, 1);
	}

	private String extText(Element element, String extPath) {
        String text=element.select(extPath).text();
        if(text==null||text.isEmpty()) {
        	return "无内容";
        }
		return text.trim();
	}
}
