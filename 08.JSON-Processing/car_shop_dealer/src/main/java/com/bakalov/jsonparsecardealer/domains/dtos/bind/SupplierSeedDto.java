package com.bakalov.jsonparsecardealer.domains.dtos.bind;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SupplierSeedDto implements Serializable {

  @Expose
  private String name;

  @Expose
  private Boolean isImporter;

  public SupplierSeedDto() {
  }

  @NotNull(message = "Name can`t be null")
  @Size(min = 2, message = "Name must be t least 2 symbols long")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NotNull(message = "Importer can`t be null")
  public Boolean getImporter() {
    return isImporter;
  }

  public void setImporter(Boolean importer) {
    isImporter = importer;
  }

}
