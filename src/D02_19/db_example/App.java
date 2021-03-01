package D02_19.db_example;

// UI 역할을 하는 클래스
public class App {

    // 매니저 객체 인스턴스 선언
    private Manager m = Manager.getInstance();

    // singleton pattern
    private App() {
        GlobalUtil.logo();
    }

    private static App app = new App();

    public static App getInstance() {
        return app;
    }


    public void run() {
        while (true) {
            switch (GlobalUtil.menuPrint()) {
                case '0':
                    break;
                case '1':
                    m.insert();
                    break;
                case '2':
                    m.delete();
                    break;
                case '3':
                    m.update();
                    break;
                case '4':
                    m.select();
                    break;
            }
        }
    }

    public void exit() {
        GlobalUtil.ending();
    }
}
