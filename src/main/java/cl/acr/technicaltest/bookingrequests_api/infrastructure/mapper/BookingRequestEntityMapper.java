package cl.acr.technicaltest.bookingrequests_api.infrastructure.mapper;

import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingItem;
import cl.acr.technicaltest.bookingrequests_api.domain.model.BookingRequest;
import cl.acr.technicaltest.bookingrequests_api.domain.model.Supplier;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.exception.BookingNotFoundException;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingItemEntity;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.BookingRequestEntity;
import cl.acr.technicaltest.bookingrequests_api.infrastructure.persistance.entity.SupplierEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class BookingRequestEntityMapper {

    public BookingRequest toDomain(BookingRequestEntity entity) {

        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setId(entity.getId());
        bookingRequest.setBookingCode(entity.getBookingCode());
        bookingRequest.setIssueDate(entity.getIssueDate());
        bookingRequest.setExpirationDate(entity.getExpirationDate());
        bookingRequest.setCurrency(entity.getCurrency());
        bookingRequest.setIncotermCode(entity.getIncotermCode());
        bookingRequest.setFreightMode(entity.getFreightMode());
        bookingRequest.setOriginCountry(entity.getOriginCountry());
        bookingRequest.setDestinationCountry(entity.getDestinationCountry());
        bookingRequest.setFobValue(entity.getFobValue());
        bookingRequest.setStatus(entity.getStatus());
        bookingRequest.setCreatedAt(entity.getCreatedAt());
        bookingRequest.setActive(entity.getActive());
        bookingRequest.setSupplier(toSupplier(entity.getSupplier()));
        bookingRequest.setItems(toBookingItemList(entity.getItems()));

        return bookingRequest;
    }

    public Supplier toSupplier(SupplierEntity supplierEntity){
        if(Objects.isNull(supplierEntity)){
            return new Supplier();
        }
        Supplier supplierResponse = new Supplier();
        supplierResponse.setId(supplierEntity.getId());
        supplierResponse.setName(supplierEntity.getName());
        supplierResponse.setTaxId(supplierEntity.getTaxId());
        supplierResponse.setCountry(supplierEntity.getCountry());
        supplierResponse.setAddress(supplierEntity.getAddress());
        supplierResponse.setContactEmail(supplierEntity.getContactEmail());
        return supplierResponse;
    }

    public List<BookingItem> toBookingItemList(List<BookingItemEntity> listBookingRequest){
        if (listBookingRequest == null || listBookingRequest.isEmpty()) {
            return Collections.emptyList();
        }

        return listBookingRequest.stream()
                .map(this::toBookingItem)
                .toList();
    }

    public BookingItem toBookingItem(BookingItemEntity itemEntity){
        if(Objects.isNull(itemEntity)){
            return new BookingItem();
        }
        BookingItem bookingItem = new BookingItem();
        bookingItem.setId(itemEntity.getId());
        bookingItem.setSku(itemEntity.getSku());
        bookingItem.setDescription(itemEntity.getDescription());
        bookingItem.setQuantity(itemEntity.getQuantity());
        bookingItem.setUnitPrice(itemEntity.getUnitPrice());
        bookingItem.setTotalMount(itemEntity.getTotalAmount());

        return bookingItem;
    }

    public BookingRequestEntity toBookingRequestEntity(BookingRequest request) {

        BookingRequestEntity requestEntity = new BookingRequestEntity();

        requestEntity.setBookingCode(request.getBookingCode());
        requestEntity.setIssueDate(request.getIssueDate());
        requestEntity.setExpirationDate(request.getExpirationDate());
        requestEntity.setCurrency(request.getCurrency());
        requestEntity.setIncotermCode(request.getIncotermCode());
        requestEntity.setFreightMode(request.getFreightMode());
        requestEntity.setOriginCountry(request.getOriginCountry());
        requestEntity.setDestinationCountry(request.getDestinationCountry());
        requestEntity.setFobValue(request.getFobValue());
        requestEntity.setStatus(request.getStatus());
        requestEntity.setCreatedAt(request.getCreatedAt());
        requestEntity.setActive(request.getActive());
//        requestEntity.setSupplier(toSupplierEntity(request.getSupplier()));
//        requestEntity.setItems(toBookingItemListEntity(request.getItems()));

        return requestEntity;
    }

//    public SupplierEntity toSupplierEntity(Supplier supplierRequest){
//        if(Objects.isNull(supplierRequest)){
//            throw new BookingNotFoundException("Error al convertir Supplier en Entity");
//        }
//        SupplierEntity supplierEntity = new SupplierEntity();
//        supplierEntity.setId(supplierRequest.getId());
//        supplierEntity.setName(supplierRequest.getName());
//        supplierEntity.setTaxId(supplierRequest.getTaxId());
//        supplierEntity.setCountry(supplierRequest.getCountry());
//        supplierEntity.setAddress(supplierRequest.getAddress());
//        supplierEntity.setContactEmail(supplierRequest.getContactEmail());
//        return supplierEntity;
//    }
//
//    public List<BookingItemEntity> toBookingItemListEntity(List<BookingItem> listBookingRequest){
//        if (listBookingRequest == null || listBookingRequest.isEmpty()) {
//            throw new BookingNotFoundException("Error al convertir las listas de BookingItem en Entity");
//        }
//
//        return listBookingRequest.stream()
//                .map(this::toBookingItemEntity)
//                .toList();
//    }
//
//    public BookingItemEntity toBookingItemEntity(BookingItem itemEntity){
//        if(Objects.isNull(itemEntity)){
//            throw new BookingNotFoundException("Error al convertir BookingItem en Entity");
//        }
//        BookingItemEntity bookingItem = new BookingItemEntity();
//        bookingItem.setId(itemEntity.getId());
//        bookingItem.setSku(itemEntity.getSku());
//        bookingItem.setDescription(itemEntity.getDescription());
//        bookingItem.setQuantity(itemEntity.getQuantity());
//        bookingItem.setUnitPrice(itemEntity.getUnitPrice());
//        bookingItem.setTotalAmount(itemEntity.getTotalMount());
//
//        return bookingItem;
//    }
}
