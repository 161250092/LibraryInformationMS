package cn.tycoding.util;

public class DocStrategy implements Strategy {
    @Override
    public URL getBookURL(long bookId) {
        return new URL("https://view.officeapps.live.com/op/view.aspx?src=http%3A%2F%2Fteacher.91yixi.com%2FW008%2Ftest.docx");
    }
}
