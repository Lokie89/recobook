package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GetBookHtml {
    private static final String detailUrl = "https://book.naver.com/bookdb/book_detail.nhn?bid=";
    Set<String> idList;

    public GetBookHtml(Set<String> idList) {
        this.idList = idList;
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

    public String readLine(String id, String containStr, String notContainStr, String startStr, String endStr) {
        StringBuilder sb = new StringBuilder();
        try {
            URL streamUrl = new URL(detailUrl + id);
            BufferedReader br = new BufferedReader(new InputStreamReader(streamUrl.openStream(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(containStr) && !line.contains(notContainStr)) {
                    int startIndex = getIndex(line, startStr);
                    int endIndex = getIndex(line, endStr, startIndex);
                    sb.append(line, startIndex, endIndex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().split(" ")[0];
    }

    public List<String> getSrcs() {
        List<String> imageUrls = new ArrayList<>();
        final String containStr = "<img src=\"https://bookthumb-phinf.pstatic.net/cover/";
        final String notContainStr = "type=";
        final String startStr = "<img src=\"";
        final String endStr = " ";
        for (String id : idList) {
            String imageUrl = readLine(id, containStr, notContainStr, startStr, endStr);
            imageUrl = imageUrl.replace("\"", "");
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }
}
