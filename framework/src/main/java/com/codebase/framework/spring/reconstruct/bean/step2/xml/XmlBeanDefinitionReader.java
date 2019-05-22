package com.codebase.framework.spring.reconstruct.bean.step2.xml;


import com.codebase.common.util.StringUtil;
import com.codebase.framework.spring.reconstruct.bean.BeanDefinitionParseException;
import com.codebase.framework.spring.reconstruct.bean.step2.*;
import com.codebase.framework.spring.reconstruct.bean.step2.io.Resource;
import com.codebase.framework.spring.reconstruct.bean.step2.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Xiaojun.Cheng
 * @date 2017/6/10
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public int loadBeanDefinitions(String... configLocations) {

        String[] resolvedLocations = new String[configLocations.length];
        for (int i = 0; i < configLocations.length; i++) {
            resolvedLocations[i] = resolvePath(configLocations[i]).trim();
        }

        int counter = 0;
        for (String location : configLocations) {
            counter += loadBeanDefinition(location);
        }
        return counter;
    }

    private String resolvePath(String configLocation) {
        return configLocation;
    }

    private int loadBeanDefinition(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        int loadCount = loadBeanDefinitions(resource);
        return loadCount;
    }

    private int loadBeanDefinitions(Resource resource) {
        try (InputStream inputStream = resource.getInputStream()) {
            InputSource inputSource = new InputSource(inputStream);
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(inputSource);
            return doLoadBeanDefinitions(document);
        } catch (ParserConfigurationException e) {
            throw new BeanDefinitionParseException("parse error: " + resource, e);
        } catch (SAXException e) {
            throw new BeanDefinitionParseException("sax error: " + resource, e);
        } catch (IOException e) {
            throw new BeanDefinitionParseException("io error: " + resource, e);
        }
    }


    private int doLoadBeanDefinitions(Document document) {
        Element rootElement = document.getDocumentElement();
        NodeList nodeList = rootElement.getElementsByTagName("bean");
        if (nodeList == null) {
            return 0;
        }

        int counter = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            loadBeanDefinitions(element);
            counter++;
        }
        return counter;
    }

    private void loadBeanDefinitions(Element element) {
        //初始化bean
        BeanDefinition definition = new BeanDefinition();
        String id = element.getAttribute("id");
        String className = element.getAttribute("class");
        definition.setName(id);
        definition.setClassName(className);

        //加载属性
        loadPropertyValues(element, definition);

        //注册
        registerBeanDefinition(definition);
    }

    private void loadPropertyValues(Element element, BeanDefinition definition) {
        PropertyValues propertyValues = new PropertyValues();
        NodeList propertyList = element.getElementsByTagName("property");
        for (int index = 0; index < propertyList.getLength(); index++) {
            Element propertyElement = (Element) propertyList.item(index);
            String name = propertyElement.getAttribute("name");
            String value = propertyElement.getAttribute("value");
            if (StringUtil.isNotEmpty(value)) {
                propertyValues.add(new PropertyValue(name, value));
                continue;
            }

            String ref = propertyElement.getAttribute("ref");
            BeanReference beanReference = new BeanReference();
            beanReference.setRef(ref);
            propertyValues.add(new PropertyValue(name, beanReference));
        }
        definition.setPropertyValues(propertyValues);
    }

}
