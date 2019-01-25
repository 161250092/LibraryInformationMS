package cn.tycoding.util;

public class PDFStrategy implements Strategy{

    @Override
    public URL getBookURL(long bookId) {
        return new URL("http://img11.360buyimg.com/n1/s450x450_jfs/t3160/284/298314156/78089/fd106c0c/57b00f93Nc77f215f.jpg");
    }


}
