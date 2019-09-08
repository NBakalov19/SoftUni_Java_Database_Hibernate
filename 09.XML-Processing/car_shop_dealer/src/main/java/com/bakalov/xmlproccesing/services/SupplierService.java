package com.bakalov.xmlproccesing.services;

import com.bakalov.xmlproccesing.domains.dtos.views.LocalSuppliersDto;

import java.io.BufferedReader;
import java.util.List;

public interface SupplierService {

  void seedSuppliers(BufferedReader reader);

  List<LocalSuppliersDto> localSuppliers();
}
