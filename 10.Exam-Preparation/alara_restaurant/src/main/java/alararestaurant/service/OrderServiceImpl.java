package alararestaurant.service;

import alararestaurant.domain.dtos.xml.ItemDto;
import alararestaurant.domain.dtos.xml.OrderSeedDto;
import alararestaurant.domain.dtos.xml.OrderSeedRootDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

  private static final String ORDER_XML_INPUT_FILE_PATH =
          "C:\\Users\\Nikolay\\Projects\\Hibernate\\11.Exam-Preparation\\Demonstration"
                  + "\\alara_restaurant\\src\\main\\resources\\files\\orders.xml";

  private final OrderRepository orderRepository;
  private final EmployeeRepository employeeRepository;
  private final ItemRepository itemRepository;
  private final OrderItemRepository orderItemRepository;
  private final ValidationUtil validator;
  private final FileUtil fileUtil;
  private final ModelMapper modelMapper;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository,
                          ItemRepository itemRepository, OrderItemRepository orderItemRepository,
                          ValidationUtil validator, FileUtil fileUtil, ModelMapper modelMapper) {
    this.orderRepository = orderRepository;
    this.employeeRepository = employeeRepository;
    this.itemRepository = itemRepository;
    this.orderItemRepository = orderItemRepository;
    this.validator = validator;
    this.fileUtil = fileUtil;
    this.modelMapper = modelMapper;
  }

  @Override
  public Boolean ordersAreImported() {
    return this.orderRepository.count() > 0;
  }

  @Override
  public String readOrdersXmlFile() throws IOException {
    return this.fileUtil.readFile(ORDER_XML_INPUT_FILE_PATH);
  }

  @Override
  public String importOrders() throws JAXBException {

    StringBuilder builder = new StringBuilder();
    String separator = System.lineSeparator();

    JAXBContext context = JAXBContext.newInstance(OrderSeedRootDto.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();

    OrderSeedRootDto dtos = (OrderSeedRootDto) unmarshaller.unmarshal(new File(ORDER_XML_INPUT_FILE_PATH));

    orderLoop:
    for (OrderSeedDto dto : dtos.getOrders()) {

      Order order = this.modelMapper.map(dto, Order.class);
      Employee employee = this.employeeRepository.findByName(dto.getEmployee());

      if (!this.validator.isValid(order) || employee == null) {
        builder.append("Invalid data format.").append(separator);

        continue;
      }

      List<OrderItem> itemList = new ArrayList<>();
      order.setEmployee(employee);
      for (ItemDto itemDto : dto.getItems().getItems()) {
        Item item = this.itemRepository.findByName(itemDto.getName());

        if (item == null) {
          continue orderLoop;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItems(item);
        orderItem.setQuantity(itemDto.getQuantity());

        itemList.add(orderItem);

        this.orderItemRepository.saveAndFlush(orderItem);
      }

      order.setOrderItems(itemList);
      order.setEmployee(employee);

      this.orderRepository.saveAndFlush(order);

      builder.append(String.format("Order for %S on %s added",
              order.getCustomer(),
              order.getDateTime()))
              .append(separator);
    }

    return builder.toString().trim();
  }

  @Override
  public String exportOrdersFinishedByTheBurgerFlippers() {
    StringBuilder builder = new StringBuilder();

    List<Order> orders = this.orderRepository.finishedByBurgerFlippers();

    for (Order order : orders) {

      builder.append(String.format("Name: %s\n", order.getEmployee().getName()));
      builder.append("Orders:\n");
      builder.append(String.format("   Customer: %s\n", order.getCustomer()));

      for (OrderItem item : order.getOrderItems()) {

        builder.append("   Items:\n");
        builder.append(String.format("\tName: %s\n", item.getItems().getName()))
                .append(String.format("\tPrice: %s\n", item.getItems().getPrice()))
                .append(String.format("\tQuantity: %s\n", item.getQuantity()));
        builder.append(System.lineSeparator());

      }
    }

    return builder.toString().trim();
  }
}
