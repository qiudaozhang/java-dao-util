import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


@Test
public class TestVar {

    public void var(){
        var a = "love";
        System.out.println(a);
    }

    public void blockText() {

        var bt = """
                这个真的
                很有趣
                """;
        System.out.println(bt);
    }

    public void usa2cn(){
        // Jul-28-2020 02:57:33 AM +UTC

        String s = "Jul-28-2020 02:57:33 AM +UTC";


    }

}
