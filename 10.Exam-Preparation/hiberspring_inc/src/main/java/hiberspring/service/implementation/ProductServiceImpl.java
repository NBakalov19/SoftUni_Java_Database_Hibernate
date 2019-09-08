package hiberspring.service.implementation;

import hiberspring.domain.dtos.xml.ProductImportDto;
import hiberspring.domain.dtos.xml.ProductImportRootDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static hiberspring.common.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final BranchRepository branchRepository;
  private final ValidationUtil validatorUtil;
  private final FileUtil fileUtil;
  private final ModelMapper mapper;
  private final XmlParser xmlParser;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository,
                            ValidationUtil validatorUtil, FileUtil fileUtil,
                            ModelMapper mapper, XmlParser xmlParser) {
    this.productRepository = productRepository;
    this.branchRepository = branchRepository;
    this.validatorUtil = validatorUtil;
    this.fileUtil = fileUtil;
    this.mapper = mapper;
    this.xmlParser = xmlParser;
  }

  @Override
  public Boolean productsAreImported() {
    return this.productRepository.count() > 0;
  }

  @Override
  public String readProductsXmlFile() throws IOException {
    return this.fileUtil.readFile(PATH_TO_FILES + "products.xml");
  }

  @Override
  public String importProducts() throws JAXBException, FileNotFoundException {
    StringBuilder sb = new StringBuilder();

    ProductImportRootDto productImportRootDto =
            this.xmlParser.parseXml(ProductImportRootDto.class, PATH_TO_FILES + "products.xml");

    for (ProductImportDto productImportDto : productImportRootDto.getProductImportDtos()) {

      Product product = this.mapper.map(productImportDto, Product.class);

      Branch branch = this.branchRepository.findByName(productImportDto.getBranch());

      product.setBranch(branch);

      if (!this.validatorUtil.isValid(product)) {
        sb.append(INCORRECT_DATA_MESSAGE).append(SEPARATOR);

        continue;
      }

      this.productRepository.saveAndFlush(product);

      sb.append(String.format(SUCCESSFUL_IMPORT_MESSAGE,
              product.getClass().getSimpleName(),
              product.getName()))
              .append(SEPARATOR);
    }

    return sb.toString().trim();
  }
}
