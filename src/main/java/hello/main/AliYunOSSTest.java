package hello.main;

import hello.utils.AliYunOSSUtils;

public class AliYunOSSTest {

    public static void main(String[] args) {
        AliYunOSSUtils.uploadString("PriceHistory/" + "a.txt", "content", true);
    }
}
