package by.naty.fitnesscenter.model.entity;

public enum WorkoutType {
    AEROBIC("aerobic"),
    ANAEROBIC("anaerobic"),
    CARDIO("cardio"),
    POWER("power"),
    INTERVAL("interval");

    private String typeWorkout;

    WorkoutType(String typeWorkout) {
        this.typeWorkout = typeWorkout;
    }

    public String getTypeWorkout() {
        return typeWorkout;
    }

    public void setTypeWorkout(String typeWorkout) {
        this.typeWorkout = typeWorkout;
    }
}
