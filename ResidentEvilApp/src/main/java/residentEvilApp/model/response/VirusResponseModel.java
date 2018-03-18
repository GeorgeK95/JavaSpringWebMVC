package residentEvilApp.model.response;

import residentEvilApp.model.enums.MagnitudeType;

import java.util.Date;

/**
 * Created by George-Lenovo on 16/03/2018.
 */
public class VirusResponseModel {

    private Long id;

    private String name;

    private MagnitudeType magnitudeType;

    private Date releasedOn;

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
}
