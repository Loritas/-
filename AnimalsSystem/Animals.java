package AnimalsSystem;

public class Animals {
    private String sex;
    private String kind;
    private int age;
    private String id;

    public Animals() {
    }

    public Animals(String sex, String kind, int age,String id) {
        this.sex = sex;
        this.kind = kind;
        this.age = age;
        this.id = id;
    }

    public String getsex() {
        return sex;
    }

    public void setsex(String sex) {
        this.sex = sex;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
