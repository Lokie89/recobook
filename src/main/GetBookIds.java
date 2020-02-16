package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class GetBookIds {
    private static final String url = "https://book.naver.com/search/search.nhn?query=";
    private final String keyword;
    private final int limit;
    private final int startPage;

    public GetBookIds(String keyword, int limit, int startPage) {
        this.keyword = keyword;
        this.limit = limit;
        this.startPage = startPage;
    }


    public int getIndex(String line, String getIndexStr) {
        int startIndex = line.indexOf(getIndexStr);
        int strLength = getIndexStr.length();
        return startIndex + strLength;
    }

    public int getIndex(String line, String getIndexStr, int fromIndex) {
        int startIndex = line.indexOf(getIndexStr, fromIndex);
        int strLength = getIndexStr.length();
        return startIndex + strLength;
    }

    public String readLine(String containStr, String startStr, String endStr) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = startPage; i < 10+startPage; i++) {
                URL streamUrl = new URL(url + keyword + "&page=" + i);
                BufferedReader br = new BufferedReader(new InputStreamReader(streamUrl.openStream(), "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(containStr)) {
                        int startIndex = getIndex(line, startStr);
                        int endIndex = getIndex(line, endStr, startIndex);
                        sb.append(line, startIndex, endIndex);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public Set<String> getIds() {
        Set<String> idList = new HashSet<>();
        final String containStr = "<a href=\"http://book.naver.com/bookdb/book_detail.nhn?bid=";
        final String startStr = "<a href=\"";
        final String endStr = "\" ";
        String srcs = readLine(containStr, startStr, endStr);
        String[] srcList = srcs.split(" ");
        for (String src : srcList) {
            if (idList.size() >= limit) {
                break;
            }
            src = src.replace("http://book.naver.com/bookdb/book_detail.nhn?bid=", "").replace("\"", "");
            idList.add(src);
        }
        return idList;
    }

}
