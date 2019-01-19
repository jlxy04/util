package com.yifeng.util.encoding;

import com.yifeng.util.Charset;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.management.RuntimeMBeanException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 16:11
 */
public class XmlUtils {

    public static Document getDocument(String payload)
            throws ParserConfigurationException, SAXException, IOException {
        if (payload == null || payload.length() < 1) {
            return null;
        }

        StringReader sr = new StringReader(payload);
        InputSource source = new InputSource(sr);

        return getDocument(source, null);
    }

    public static Document getDocument(InputSource xml, InputStream xsd)
            throws ParserConfigurationException, SAXException, IOException {
        Document doc = null;

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            if (xsd != null) {
                dbf.setNamespaceAware(true);
            }

            DocumentBuilder builder = dbf.newDocumentBuilder();
            doc = builder.parse(xml);

            if (xsd != null) {
                validateXml(doc, xsd);
            }
        } finally {
            if (xml.getByteStream() != null) {
                try {
                    xml.getByteStream().close();
                } catch (IOException e) {
                }
            }
        }

        return doc;
    }

    public static Element getRootElementFromString(String text)
            throws ParserConfigurationException, SAXException, IOException {
        return getDocument(text).getDocumentElement();
    }

    public static List<Element> getChildElements(Element parent, String tagName) {
        if (null == parent) {
            return null;
        }
        NodeList nodes = parent.getElementsByTagName(tagName);
        List<Element> elements = new ArrayList<Element>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getParentNode() == parent) {
                elements.add((Element) node);
            }
        }

        return elements;
    }

    public static List<Element> getChildElements(Element parent) {
        if (null == parent) {
            return null;
        }

        NodeList nodes = parent.getChildNodes();
        List<Element> elements = new ArrayList<Element>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                elements.add((Element) node);
            }
        }

        return elements;
    }

    public static void validateXml(Node root, InputStream xsd)
            throws SAXException, IOException {
        try {
            Source source = new StreamSource(xsd);
            Schema schema = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(source);

            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(root));
        } finally {
            if (xsd != null) {
                try {
                    xsd.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static <T> T parseXml(String text, Class<T> clazz) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(text));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    public static String toXml(Object o, String charset) {
        String s = null;
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charset);
            StringWriter writer = new StringWriter();
            marshaller.marshal(o, writer);
            s = writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return s;
    }

    public static String toXml(Object o) {
        String s = null;
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Charset.UTF8);
            StringWriter writer = new StringWriter();
            marshaller.marshal(o, writer);
            s = writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
