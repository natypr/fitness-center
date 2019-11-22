package by.naty.fitnesscenter.model.entity;

import java.util.List;

public class ClientInfoWorkout {
    private Client client;
    private List<Workout> workoutList;

    public ClientInfoWorkout(Client client, List<Workout> workoutList) {
        this.client = client;
        this.workoutList = workoutList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientInfoWorkout that = (ClientInfoWorkout) o;

        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        return workoutList != null ? workoutList.equals(that.workoutList) : that.workoutList == null;
    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (workoutList != null ? workoutList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientInfoWorkout{");
        sb.append("client=").append(client);
        sb.append(", workoutList=").append(workoutList);
        sb.append('}');
        return sb.toString();
    }
}
