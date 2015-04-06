//package org.succlz123.doubanbooklog.dao;
//
//import android.util.Log;
//import org.succlz123.doubanbooklog.DoubanApplication;
//import org.succlz123.doubanbooklog.R;
//import org.succlz123.doubanbooklog.bean.Book22Info.BookInfo;
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.*;
//import java.util.ArrayList;
//
///**
// * Created by fashi on 2015/3/30.
// */
//public class XmlStudy {
//
//    final static BookInfo bookInfo = new BookInfo();
//
//    static ArrayList<BookInfo> books = new ArrayList<BookInfo>();
//
//    public static void getBooks(String id) {
//
//        String url = ApiUrlHelper.USER_ALL_NOTE;
//        url = url.replace(":id", id);
//
//        new Thread(new Runnable() {
//            String xml = rawRead();
//
//            @Override
//            public void run() {
//
//                parseXMLwithPull(xml);
//
//            }
//        }).start();
//    }
//
//
//    private static void parseXMLwithPull(String xmldate) {
//
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xmlPullParser = factory.newPullParser();
//            xmlPullParser.setInput(new StringReader(xmldate));
//            int evenType = xmlPullParser.getEventType();
//
//            while (evenType != XmlPullParser.END_DOCUMENT) {
//
//                String nodeName = xmlPullParser.getName();
//
//                String xx = xmlPullParser.getAttributeValue("db:attribute", "author-intro");
//
//                switch (evenType) {
//
//                    case XmlPullParser.START_TAG:
//                        if ("name".equals(nodeName)) {
//
//                            bookInfo.setAuthor(xmlPullParser.nextText());
//
//
//                        } else if ("db:attribute".equals(nodeName)) {
//
////                            bookInfo.setContent(xmlPullParser.getAttributeValue(null, "name"));
//                            if ("author-intro".equals(xmlPullParser.getAttributeValue(null, "name"))) {
//
//                            }
//
////                                bookInfo.setContent(xmlPullParser.nextText());
//
//                        }
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        if ("entry".equals(nodeName)) {
//
//                            books.add(bookInfo);
//                        }
//                        break;
//
//                    default:
//                        break;
//                }
//                evenType = xmlPullParser.next();
//            }
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
////    public static class MyHandler extends DefaultHandler {
////
////        private String nodeName;
////
////        private StringBuilder id;
////        private StringBuilder title;
////        private StringBuilder name;
////
////        private StringBuilder summary;
////
////        private StringBuilder isbn10;
////        private StringBuilder isbn13;
////
////        ArrayList<BookInfo> bookinfos = new ArrayList<BookInfo>();
////
////        HashMap<String, String> hashMap = new HashMap<String, String>();
////
////        private String value;
////
////        @Override
////        public void startDocument() throws SAXException {
////            id = new StringBuilder();
////            title = new StringBuilder();
////            name = new StringBuilder();
////
////        }
////
////        @Override
////        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
////            nodeName = localName;
////
////
////            if (qName.equals("db:attribute")) {
////                String key = attributes.getValue("name");
////
//////                String value = attributes.getValue(key);
//////                hashMap.put(key, value);
////            }
////        }
////
////        @Override
////        public void characters(char[] ch, int start, int length) throws SAXException {
////
//////            if ("id".equals(nodeName)) {
//////                id.append(ch, start, length);
//////            } else if ("title".equals(nodeName)) {
//////                title.append(ch, start, length);
//////            } else if ("name".equals(nodeName)) {
//////                name.append(ch, start, length);
//////            }
////            super.characters(ch, start, length);
////            String value = new String(ch, start, length);
////
////            this.value = value.trim();
////        }
////
////        @Override
////        public void endElement(String uri, String localName, String qName) throws SAXException {
////            if ("title".equals(localName)) {
////                Log.e("13433333", id.toString().trim());
////
////                bookInfo.setId(this.value);
////
////
////            }
////            id.setLength(0);
////        }
////
////        @Override
////        public void endDocument() throws SAXException {
////
////        }
////
////    }
////
////    private static void parseXMLwithSAX(String xmlDate) {
////
////
////        try {
////            SAXParserFactory factory = SAXParserFactory.newInstance();
////            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
////            MyHandler handler = new MyHandler();
////            xmlReader.setContentHandler(handler);
////            xmlReader.parse(new InputSource(new StringReader(xmlDate)));
////
////        } catch (SAXException e) {
////            e.printStackTrace();
////        } catch (ParserConfigurationException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//    private static String rawRead() {
//
//        StringBuilder stringBuffer = new StringBuilder();
//        try {
//            InputStream inputStream = DoubanApplication.getInstance().getResources().openRawResource(R.raw.dd);
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//            String info = "";
//            while ((info = bufferedReader.readLine()) != null) {
//                Log.i("info", info);
//                stringBuffer.append(info);
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return stringBuffer.toString();
//    }
//
//}
