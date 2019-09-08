package com.bakalov.jsonparsecardealer.services;

import com.bakalov.jsonparsecardealer.domains.dtos.bind.SupplierSeedDto;
import com.bakalov.jsonparsecardealer.domains.dtos.view.LocalSuppliersDto;

import java.util.List;

public interface SupplierService {

  void seedSuppliers(SupplierSeedDto[] supplierSeedDtos);

  List<LocalSuppliersDto> localSuppliers();
}
