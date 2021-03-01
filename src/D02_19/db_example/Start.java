package D02_19.db_example;

// 시작 역할만 하는 클래스
public class Start {
    public static void main(String[] args) {
        App app = App.getInstance();
        app.run();
        app.exit();
    }
}
