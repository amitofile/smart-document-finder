/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

/**
 *
 * @author amit
 */
public class KeywordFinder {

    private String str;
    private final int offset = 200;
    private final int lenght;

    public KeywordFinder(String str) {
        this.str = str;
        this.lenght = str.length();
        System.out.println("K start");
    }

    @Override
    protected void finalize() throws Throwable {
        str = null;
        super.finalize();
        System.out.println("K end");
    }

    public String find(String keyword, boolean caseSensetive) {
        int index = str.indexOf(keyword);
        if (index > -1) {
            int start = index - offset < 0 ? 0 : index - offset;
            int end = index + offset > lenght ? lenght : index + offset;
            return str.substring(start, end);
        }
        return null;
    }

    public String find(String keyword) {
        keyword = keyword.toLowerCase();
        String _str = str.toLowerCase();
        int index = _str.indexOf(keyword);
        if (index > -1) {
            int start = index - offset < 0 ? 0 : index - offset;
            int end = index + offset > lenght ? lenght : index + offset;
            keyword = str.substring(index, index + keyword.length());
            return str.substring(start, end).replaceAll(keyword, "<font color=\"red\">" + keyword + "</font>");
        }
        return null;
    }
}
