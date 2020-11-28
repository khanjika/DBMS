package sql;

public class InternalQuery {
    private String action = "";
    private String subject = "";
    private String option = "";
    private String condition = "";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if(subject == null){
            subject = "";
        }
        this.subject = subject;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        if(option == null){
            option = "";
        }
        this.option = option;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        if(condition == null){
            condition = "";
        }
        this.condition = condition;
    }
}
