package residentEvilApp.model.request;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import residentEvilApp.annotation.ResidentEvilDate;
import residentEvilApp.model.enums.CreatorType;
import residentEvilApp.model.enums.MagnitudeType;
import residentEvilApp.model.enums.MutationType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 * Created by George-Lenovo on 11/03/2018.
 */
public class AddVirusRequestModel {

    @NotBlank
    @Size(min = 3, max = 10, message = "Name length must be min 3 and max 10 symbols long.")
    private String name;

    @NotBlank
    @Size(min = 5, max = 100, message = "Description length must be min 5 and max 100 symbols long.")
    private String description;

    @NotBlank
    @Size(max = 50, message = "Side effect length must be min 50 symbols long.")
    private String sideEffect;

    private CreatorType creatorType;

    private Boolean isDeadly;

    private Boolean isCurable;

    @NotNull(message = "Mutation cannot be null.")
    private MutationType mutationType;

    @Range(min = 0,max = 100)
    private Integer turnoverRate;

    @Range(min = 1, max = 12)
    private Integer hoursUntilTurn;

    @NotNull
    private MagnitudeType magnitudeType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ResidentEvilDate
    private LocalDate releaseOn;

    private Long[] capitalIds;

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

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
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

    public LocalDate getReleaseOn() {
        return releaseOn;
    }

    public void setReleaseOn(LocalDate releaseOn) {
        this.releaseOn = releaseOn;
    }

    public Long[] getCapitalIds() {
        return capitalIds;
    }

    public void setCapitalIds(Long[] capitalIds) {
        this.capitalIds = capitalIds;
    }
}
