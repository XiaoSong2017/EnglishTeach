package vo;

public class RelationShip {
    private Double core;
    private Double time;

    public RelationShip(Double core, Double time) {
        this.core = core;
        this.time = time;
    }

    public RelationShip() {
    }

    public Double getCore() {
        return core;
    }

    public void setCore(Double core) {
        this.core = core;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
