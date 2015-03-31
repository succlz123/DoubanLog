package org.succlz123.doubanbooklog.dao;

import org.succlz123.doubanbooklog.bean.BookInfo;

import java.io.InputStream;
import java.util.List;

/**
 * Created by fashi on 2015/3/31.
 */
public interface InterFace  {

    public List<BookInfo> parse(InputStream is) throws Exception;

    public String serialize(List<BookInfo> beauties) throws Exception;
}
