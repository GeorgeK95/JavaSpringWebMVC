package carDealer.service;

import carDealer.model.entity.Supplier;
import carDealer.model.request.AddSupplierRequestModel;
import carDealer.model.response.SupplierResponseModel;
import carDealer.repository.SuppliersRepository;
import carDealer.utils.DTOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public SupplierResponseModel findOne(Long id) {
        return DTOConvertUtil.convert(this.repository.findOne(id), SupplierResponseModel.class);
    }

    public void addSale(AddSupplierRequestModel supplierRequestModel, RedirectAttributes attributes) {
        this.repository.saveAndFlush(DTOConvertUtil.convert(supplierRequestModel, Supplier.class));

        attributes.addFlashAttribute("supplier_added",
                String.format("Successfully added supplier with name %s.", supplierRequestModel.getSupplierName()));
    }

    public void editSupply(Long id, AddSupplierRequestModel requestModel) {
        Supplier supplyToEdit = this.repository.findOne(id);
        supplyToEdit.setImporter(requestModel.isImporter());
        supplyToEdit.setName(requestModel.getSupplierName());
        this.repository.saveAndFlush(supplyToEdit);
    }

    public void deleteSupply(Long id) {
        this.repository.delete(id);
    }
}
