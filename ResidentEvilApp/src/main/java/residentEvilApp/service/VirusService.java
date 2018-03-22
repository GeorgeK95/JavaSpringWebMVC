package residentEvilApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import residentEvilApp.model.entity.Capital;
import residentEvilApp.model.entity.Virus;
import residentEvilApp.model.request.AddVirusRequestModel;
import residentEvilApp.model.request.EditVirusRequestModel;
import residentEvilApp.model.response.VirusResponseModel;
import residentEvilApp.repository.CapitalRepository;
import residentEvilApp.repository.VirusRepository;
import residentEvilApp.service.contacts.ICapitalService;
import residentEvilApp.service.contacts.IVirusService;
import residentEvilApp.util.DTOConverter;
import residentEvilApp.validator.ResidentEvilDateValidator;

import java.util.*;

/**
 * Created by George-Lenovo on 16/03/2018.
 */
@Service
@Transactional
public class VirusService implements IVirusService {

    private final VirusRepository virusRepository;

    private final ICapitalService capitalService;

    @Autowired
    public VirusService(VirusRepository virusRepository, ICapitalService capitalService) {
        this.virusRepository = virusRepository;
        this.capitalService = capitalService;
    }

    @Override
    public List<VirusResponseModel> findAll() {
        return DTOConverter.convert(this.virusRepository.findAll(), VirusResponseModel.class);
    }

    @Override
    public void addVirus(AddVirusRequestModel requestModel) {
        Virus virus = DTOConverter.convert(requestModel, Virus.class);
        List<Capital> capitalsSet = this.capitalService.findAllById(List.of(requestModel.getCapitalIds()));
        virus.setCapitals(new HashSet<>(capitalsSet));
        capitalsSet.forEach(c->c.setVirus(virus));
        this.capitalService.addVirus(capitalsSet);
        this.virusRepository.saveAndFlush(virus);
    }

    @Override
    public <T> T findOne(Long id, Class<T> clazz) {
        Virus virus = this.virusRepository.findFirstById(id);
        return DTOConverter.convert(virus, clazz);
    }

    @Override
    public void editVirus(Long id, EditVirusRequestModel requestModel) {
        Virus virus = this.virusRepository.findFirstById(id);
        virus = DTOConverter.convert(requestModel, Virus.class);
        this.virusRepository.saveAndFlush(virus);
    }

    @Override
    public void deleteVirus(Long id) {
        this.virusRepository.deleteById(id);
    }

    @Transactional
    @Override
    public String findAllMapViruses() {
        List<Virus> viruses = this.virusRepository.findAll();
        String header = "{\n" +
                "    \"type\": \"FeatureCollection\",\n" +
                "    \"features\": [\n";
        String footer = "]\n" +
                "}\n";

        return header + this.constructJoiner(viruses) + footer;
    }

    private StringJoiner constructJoiner(List<Virus> viruses) {
        StringJoiner joiner = new StringJoiner(",");
        for (Virus virus : viruses) {
            String color = "#f00";
            int magnitude = 0;
            switch (virus.getMagnitudeType()) {
                case LOW:
                    magnitude = 3;
                    break;
                case MEDIUM:
                    magnitude = 5;
                    break;
                case HIGH:
                    magnitude = 7;
                    break;
            }

            for (Capital capital : virus.getCapitals()) {
                String body = String.format("{\n" +
                        "        \"type\": \"Feature\",\n" +
                        "        \"properties\": {\n" +
                        "            \"mag\": %d,\n" +
                        "            \"color\": \"%s\"\n" +
                        "        },\n" +
                        "        \"geometry\": {\n" +
                        "            \"type\": \"Point\",\n" +
                        "            \"coordinates\": [\n" +
                        "                %f,\n" +
                        "                %f\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    }\n", magnitude, color, capital.getLatitude(), capital.getLongitude());
                joiner.add(body);
            }
        }

        return joiner;
    }
}
