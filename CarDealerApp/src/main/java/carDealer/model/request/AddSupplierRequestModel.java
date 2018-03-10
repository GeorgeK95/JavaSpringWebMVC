package carDealer.model.request;

/**
 * Created by George-Lenovo on 03/08/2018.
 */
public class AddSupplierRequestModel {
    private String supplierName;

    private String isImporter;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(String importer) {
        isImporter = importer;
    }

    public boolean isImporter() {
        return this.isImporter != null && !this.isImporter.equals("off");
    }
}
