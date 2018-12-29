package annotations.database;

@DBTable2
public class Member2 {
    @SQLString2(20)
    private String firestName;
    @SQLString2(value = 30, constraints = @Constraints2(unique = true))
    private String lastName;
    @SQLInteger2
    private Integer age;
    @SQLString2(value = 20, constraints = @Constraints2(primaryKey = true))
    private String handle;

    public String getFirestName() {
        return firestName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

}
