package D02_19.db_example;

// 실제 기능을 하는 클래스
public class Manager {

    private Manager() {

    }

    private static Manager manager = new Manager();

    public static Manager getInstance() {
        return manager;
    }

    private ObjectDB db = new ObjectDB();

    public void insert() {
        int id = GlobalUtil.inputNumber("아이디 입력");
        String name = GlobalUtil.inputString("닉네임 입력");
        db.insert(id, name);
    }

    public void delete() {
        int id = GlobalUtil.inputNumber("삭제할 아이디 입력");
        db.delete(id);
    }

    public void update() {
        int id = GlobalUtil.inputNumber("아이디 입력");
        String name = GlobalUtil.inputString("수정할 닉네임 입력");
        db.update(id, name);
    }

    public void select() {
        int id = GlobalUtil.inputNumber("검색할 아이디 입력");
        String[] result = String.valueOf(db.select(id)).split("@");
        System.out.println("<회원 정보>");
        System.out.println("--------------");
        for (String s : result) {
            String[] s1 = s.split("#");
            System.out.println("아이디 : " + s1[0]);
            System.out.println("이름 : " + s1[1]);
            System.out.println("가입날짜 : " + s1[2]);
            System.out.println("--------------");
        }

    }


}

