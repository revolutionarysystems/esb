package uk.co.revsys.esb.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URLEncoder {
    
    public String encode(String string) throws UnsupportedEncodingException{
        return encode(string, "UTF-8");
    }
    
    public String encode(String string, String encoding) throws UnsupportedEncodingException{
        return java.net.URLEncoder.encode(string, encoding);
    }
    
    public String decode(String string) throws UnsupportedEncodingException{
        return decode(string, "UTF-8");
    }
    
    public String decode(String string, String encoding) throws UnsupportedEncodingException{
        return URLDecoder.decode(string, encoding);
    }
    
    public String encodeQueryString(String queryString) throws UnsupportedEncodingException{
        return encodeQueryString(queryString, "UTF-8");
    }
    
    public String encodeQueryString(String queryString, String encoding) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        String[] parameters = queryString.split("&");
        for(String parameter: parameters){
            String name = parameter.substring(0, parameter.indexOf("="));
            String value = parameter.substring(parameter.indexOf("=")+1);
            result.append(name).append("=").append(encode(value, encoding)).append("&");
        }
        String resultString = result.toString();
        if(resultString.endsWith("&")){
            resultString = resultString.substring(0, resultString.length()-1);
        }
        return resultString;
    }
    
}
