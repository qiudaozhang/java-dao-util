import org.testng.annotations.Test;


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
}
