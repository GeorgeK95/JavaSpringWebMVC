package carDealer.service;

import carDealer.model.response.SupplierResponseModel;
import carDealer.repository.SuppliersRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by George-Lenovo on 03/03/2018.
 */
@Service
@Transactional
public class SupplierServices {
    private SuppliersRepository repository;

    @Autowired
    public SupplierServices(SuppliersRepository repository) {
        this.repository = repository;
    }

    public List<SupplierResponseModel> filterLocalSuppliers() {
        return DTOConvertUtil.convert(this.repository.findAllByIsImporterTrue(), SupplierResponseModel.class);
    }

    public List<SupplierResponseModel> filterImportersSuppliers() {
        return DTOConvertUtil.convert(this.repository.findAllByIsImporterFalse(), SupplierResponseModel.class);
    }

    public List<SupplierResponseModel> findAll() {
        return DTOConvertUtil.convert(this.repository.findAll(), SupplierResponseModel.class);
    }
}
