package domain;


import java.util.Date;

/**
 * 封装Emp表的JavaBean
 * @author miehewang
 * @date 2019/5/26
 */
public class Emp {

    private Integer id;

    private String eName;

    private Integer jobId;

    private Integer mgr;

    private Date joinDate;

    private Double salary;

    private Double bonus;

    private Integer deptId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", eName='" + eName + '\'' +
                ", jobId=" + jobId +
                ", mgr=" + mgr +
                ", joinDate=" + joinDate +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", deptId=" + deptId +
                '}';
    }
}
