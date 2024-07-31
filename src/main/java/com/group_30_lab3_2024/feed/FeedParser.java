package com.group_30_lab3_2024.feed;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.List;

public class FeedParser {

    public static List<Article> parseXML(String xmlData) {
        List<Article> allArticles = new ArrayList<Article>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xmlData)));
            doc.getDocumentElement().normalize();

            List<Element> itemElements = getElements(doc, "item");

            for (Element itemElement : itemElements) {
                String title = getTextContent(itemElement, "title");
                String description = getTextContent(itemElement, "description");
                String link = getTextContent(itemElement, "link");
                Date date = getGenericDate(itemElement, "pubDate");

                allArticles.add(new Article(title, description, link, date));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allArticles;
    }

    private static List<Element> getElements(Document doc, String tagName) {
        List<Element> elements = new ArrayList<>();

        NodeList nodeList = doc.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                elements.add(element);
            }
        }

        return elements;
    }

    private static String getTextContent(Element element, String tagName) {
        String result = null;
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            try {
                Node node = nodeList.item(0);
                result = node.getTextContent();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Date getGenericDate(Element element, String tagName) {
        Date result = null;
        String dateString = getTextContent(element, tagName);
        if (dateString != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            try {
                result = dateFormat.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String fetchFeed(String feedURL) throws MalformedURLException, IOException, Exception {

        URL url = new URL(feedURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        connection.setRequestProperty("User-agent", "lab_paradigmas_group30");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new Exception("HTTP error code: " + status);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();
        }
    }
}
