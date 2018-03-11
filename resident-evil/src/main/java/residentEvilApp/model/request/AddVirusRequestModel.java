package residentEvilApp.model.request;

import residentEvilApp.model.enums.CreatorType;
import residentEvilApp.model.enums.MagnitudeType;
import residentEvilApp.model.enums.MutationType;

import java.util.Date;
import java.util.Set;

/**
 * Created by George-Lenovo on 11/03/2018.
 */
public class AddVirusRequestModel {

    private String name;

    private String description;

    private String sideEffects;

    private CreatorType creatorType;

    private Boolean isDeadly;

    private Boolean isCurable;

    private MutationType mutationType;

    private Integer turnoverRate;

    private Integer hoursUntilTurn;

    private MagnitudeType magnitudeType;

    private Date releaseOn;

    private String[] capitalsNames;

    private Set<CapitalModel> capitals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public CreatorType getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(CreatorType creatorType) {
        this.creatorType = creatorType;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    public MutationType getMutationType() {
        return mutationType;
    }

    public void setMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
    }

    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public MagnitudeType getMagnitudeType() {
        return magnitudeType;
    }

    public void setMagnitudeType(MagnitudeType magnitudeType) {
        this.magnitudeType = magnitudeType;
    }

    public Date getReleaseOn() {
        return releaseOn;
    }

    public void setReleaseOn(Date releaseOn) {
        this.releaseOn = releaseOn;
    }

    public String[] getCapitalsNames() {
        return capitalsNames;
    }

    public void setCapitalsNames(String[] capitalsNames) {
        this.capitalsNames = capitalsNames;
    }

    public Set<CapitalModel> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<CapitalModel> capitals) {
        this.capitals = capitals;
    }
}
