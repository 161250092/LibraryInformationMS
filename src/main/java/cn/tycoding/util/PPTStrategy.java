package cn.tycoding.util;

public class PPTStrategy implements  Strategy {
    @Override
    public URL getBookURL(long bookId) {
        return new URL("https://view.officeapps.live.com/op/view.aspx?src=http%3a%2f%2fvideo.ch9.ms%2fbuild%2f2011%2fslides%2fTOOL-532T_Sutter.pptx");
    }
}
