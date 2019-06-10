package vo;

public class RelationShip {
    private Double examCore;
    private Double usualCore;
    private Double core;
    private Double time;

    public RelationShip(Double examCore, Double usualCore, Double core, Double time) {
        this.examCore = examCore;
        this.usualCore = usualCore;
        this.core = core;
        this.time = time;
    }

    public RelationShip() {
    }

    public Double getExamCore() {
        return examCore;
    }

    public void setExamCore(Double examCore) {
        this.examCore = examCore;
    }

    public Double getUsualCore() {
        return usualCore;
    }

    public void setUsualCore(Double usualCore) {
        this.usualCore = usualCore;
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
