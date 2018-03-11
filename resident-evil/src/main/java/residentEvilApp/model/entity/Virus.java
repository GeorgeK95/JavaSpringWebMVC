package residentEvilApp.model.entity;

import org.hibernate.validator.constraints.Length;
import residentEvilApp.model.enums.CreatorType;
import residentEvilApp.model.enums.MagnitudeType;
import residentEvilApp.model.enums.MutationType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Set;

/**
 * Created by George-Lenovo on 11/03/2018.
 */
@Entity
@Table(name = "viruses")
public class Virus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, max = 10)
    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    @Length(min = 5, max = 100)
    private String description;

    @Length(max = 50)
    private String sideEffects;

    @Enumerated(EnumType.STRING)
    private CreatorType creatorType;

    private boolean isDeadly;

    private boolean isCurable;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MutationType mutationType;

    @Min(0)
    @Max(100)
    private Integer turnoverRate;

    @Min(1)
    @Max(12)
    private Integer hoursUntilTurn;

    @Enumerated(EnumType.STRING)
    private MagnitudeType magnitudeType;

    @Column(nullable = false)
    private Date releasedOn;

    @OneToMany(mappedBy = "virus")
    private Set<Capital> capitals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isDeadly() {
        return isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return isCurable;
    }

    public void setCurable(boolean curable) {
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

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public Set<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}
