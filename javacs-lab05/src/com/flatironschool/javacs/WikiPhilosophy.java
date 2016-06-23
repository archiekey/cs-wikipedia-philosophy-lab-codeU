package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;

public class WikiPhilosophy {
	
	final static WikiFetcher wf = new WikiFetcher();
	
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * 
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 * 
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
        // some example code to get you started

		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)"; //"https://en.wikipedia.org/wiki/Greek_language";
        String ending = "https://en.wikipedia.org/wiki/Philosophy";
        String urlstring = "";
        boolean hashtag = true;
        while (url.equals(ending) == false)
        {
            
           // System.out.println("1: "+ url);
            int foundfirst = 0;
            hashtag = false;
            Elements paragraphs = wf.fetchWikipedia(url);
            Element firstPara = paragraphs.get(0);
            //System.out.println("0: "+ firstPara.toString());
            Iterable<Node> iter = new WikiNodeIterable(firstPara);
            String url1=null;
            boolean checkparen = false;
            for (Node node: iter)
            {
                if (node instanceof TextNode)
                {
                                    
                }

                if(node instanceof Element)
                {
                    Element element = (Element)node;
                    if(element.tagName().equals("a"))
                    {
                        Elements parentelement = element.parents();
                        boolean isitalic = false;
                        for (Element italicelement: parentelement)
                        {
                            if(italicelement.tagName().equals("i") || italicelement.tagName().equals("em"))
                            {
                                isitalic = true;
                            
                            }
                        }
                        foundfirst++;
                        if(isitalic == false && foundfirst == 1)
                        {
                            String endofurl = element.attr("href");
                            String newstring = "https://en.wikipedia.org";
                            if (endofurl.charAt(0) != '#')
                            {
                                urlstring = newstring + endofurl;
                                hashtag = true;
                            }
                            url = urlstring;
                            if ( hashtag == false)
                            {
                                foundfirst = 0;
                            }


                        }
                        else
                        {
                            break;
                        }
                    }
            
        }
            }
        }
      //  }
    
    
        // the following throws an exception so the test fails
        // until you update the code
       // String msg = "Complete this lab by adding your code and removing this statement.";
       // throw new UnsupportedOperationException(msg);
	}
}
