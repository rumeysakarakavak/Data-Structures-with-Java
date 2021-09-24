import java.sql.Time;

public class Experiment {
    //region Constructors
    public Experiment(String name, Integer dayOf){
        setup = name;
        day = dayOf;
        time = null;
        completed = false;
        accuracy = null;
    }
    public Experiment(){
        setup = null;
        day = null;
        time = null;
        completed = false;
        accuracy = null;
    }

    public Experiment(String name, String timeOf , Boolean isCompleted, Integer dayOf,  Float accuracyOf){
        setup = name;
        day = dayOf;
        time = timeOf;
        completed = isCompleted;
        accuracy = accuracyOf;
    }
    public Experiment(String name, Integer dayOf, String timeOf ,Boolean isCompleted, Float accuracyOf){
        setup = name;
        day = dayOf;
        time = timeOf;
        completed = isCompleted;
        accuracy = accuracyOf;
    }
    //endregion Constructors
    //region instanceVariables
    private String setup;
    private Integer day;
    private String  time;
    private Boolean completed;
    private Float accuracy;
    //endregion instanceVariables
    //region setters
    public void setSetup(String setup) {
        this.setup = setup;
    }

    public void setDay(Integer day) {
        this.day = day;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    //endregion setters
    //region getters
    public String getSetup() {
        return setup;
    }

    public Integer getDay() {
        return day;
    }
    public String getTime() {
        return time;
    }
    public Boolean getCompleted() {
        return completed;
    }
    public Float getAccuracy() {
        return accuracy;
    }
    //endregion getters

    //region Methods

    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }

    //endregion Methods
}
