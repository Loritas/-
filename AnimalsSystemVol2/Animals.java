package AnimalsSystemVol2;

import java.io.Serializable;
import java.util.Objects;

public class Animals implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Sex;
    private String kind;
    private String id;
    private int age;

    public Animals(String Sex, String kind, String id, int age) {
        this.Sex = Sex;
        this.kind = kind;
        this.id = id;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animals = (Animals) o;
        return age == animals.age &&
                Objects.equals(Sex, animals.Sex) &&
                Objects.equals(kind, animals.kind) &&
                Objects.equals(id, animals.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Sex, kind, id, age);
    }

    @Override
    public String toString() {
        return "Animals{" +
                "name='" + Sex + '\'' +
                ", kind='" + kind + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        this.Sex = sex;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animals() {
    }
}
