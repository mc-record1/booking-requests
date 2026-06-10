package cl.acr.technicaltest.bookingrequests_api.presentation.mappers;

import cl.acr.technicaltest.bookingrequests_api.domain.enums.FreightMode;
import cl.acr.technicaltest.bookingrequests_api.domain.enums.Status;
import cl.acr.technicaltest.bookingrequests_api.domain.exceptions.BookingNotFoundException;
import cl.acr.technicaltest.bookingrequests_api.domain.model.*;
import cl.acr.technicaltest.bookingrequests_api.presentation.request.*;
import cl.acr.technicaltest.bookingrequests_api.presentation.response.BookingItemResponse;
import cl.acr.technicaltest.bookingrequests_api.presentation.response.BookingResponse;
import cl.acr.technicaltest.bookingrequests_api.presentation.response.SupplierResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class BookingSearchMapper {

    public BookingSearchCriteria toCriteria(
            BookingSearchRequest request) {

        BookingSearchCriteria criteria =
                new BookingSearchCriteria();

        criteria.setTaxId(request.getTaxId());

        criteria.setStatus(
                request.getStatus() != null
                        ? Status.valueOf(
                        request.getStatus().toUpperCase())
                        : null);

        criteria.setFreightMode(
                request.getFreightMode() != null
                        ? FreightMode.valueOf(
                        request.getFreightMode().toUpperCase())
                        : null);

        criteria.setDateFrom(request.getDateFrom());
        criteria.setDateTo(request.getDateTo());
        criteria.setBookingCode(request.getBookingCode());

        return criteria;
    }

    public List<BookingResponse> toResponseList(
            List<BookingRequest> bookingRequests) {

        if (bookingRequests == null || bookingRequests.isEmpty()) {
            return Collections.emptyList();
        }

        return bookingRequests.stream()
                .map(this::toResponse)
                .toList();
    }

    public BookingResponse toResponse(BookingRequest bookingRequest) {

        if (bookingRequest == null) {
            throw new BookingNotFoundException("Error al mapear los valores de Respuesta del servicio");
        }

        BookingResponse response = new BookingResponse();

        response.setId(bookingRequest.getId());
        response.setBookingCode(bookingRequest.getBookingCode());
        response.setIssueDate(bookingRequest.getIssueDate());
        response.setExpirationDate(bookingRequest.getExpirationDate());
        response.setCurrency(bookingRequest.getCurrency());
        response.setIncotermCode(bookingRequest.getIncotermCode());
        response.setFreightMode(bookingRequest.getFreightMode());
        response.setOriginCountry(bookingRequest.getOriginCountry());
        response.setDestinationCountry(bookingRequest.getDestinationCountry());
        response.setFobValue(bookingRequest.getFobValue());
        response.setStatus(bookingRequest.getStatus());
        response.setCreatedAt(bookingRequest.getCreatedAt());
        response.setActive(bookingRequest.getActive());
        response.setSupplier(toSupplierResponse(bookingRequest.getSupplier()));
        response.setItems(toListBookingItemResponse (bookingRequest.getItems()));

        return response;
    }

    public BookingRequestUpdate toRequestUpdate(Long id, UpdateBookingRequest bookingRequest) {

        if (bookingRequest == null) {
            throw new BookingNotFoundException("Error al mapear los valores de BookingActualizado");
        }

        BookingRequestUpdate request = new BookingRequestUpdate();

        request.setId(id);
        request.setIssueDate(bookingRequest.getIssueDate());
        request.setExpirationDate(bookingRequest.getExpirationDate());
        request.setCurrency(bookingRequest.getCurrency());
        request.setFobValue(bookingRequest.getFobValue());

        return request;
    }

    public SupplierResponse toSupplierResponse(Supplier supplier){
        if(Objects.isNull(supplier)){
            throw new BookingNotFoundException("Error al mapear los valores de supplier para respuesta del servicio");
        }
        SupplierResponse response = new SupplierResponse();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setTaxId(supplier.getTaxId());
        response.setCountry(supplier.getCountry());
        response.setAddress(supplier.getAddress());
        response.setContactEmail(supplier.getContactEmail());

        return response;
    }

    public List<BookingItemResponse> toListBookingItemResponse(List<BookingItem> itemList) {
        if(itemList == null || itemList.isEmpty()){
            throw new BookingNotFoundException("Error al mapear las listas de BookingItem para la respuesta del servicio");
        }

        return itemList.stream()
                .map(this::toBookingItemResponse)
                .toList();
    }

    public BookingItemResponse toBookingItemResponse(BookingItem item){
        if(Objects.isNull(item)){
            throw new BookingNotFoundException("Error al mapear los valores de BookingItem para la respuesta del servicio");
        }

        BookingItemResponse itemResponse = new BookingItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setSku(item.getSku());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setUnitPrice(item.getUnitPrice());
        itemResponse.setQuantity(item.getQuantity());
        itemResponse.setTotalMount(item.getTotalMount());

        return itemResponse;
    }

    public BookingRequest toRequestCreate(CreateBookingRequest request){
        if(Objects.isNull(request)){
            throw new BookingNotFoundException("El objeto del request no puede ser nulo");
        }
        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setBookingCode(request.getBookingCode());
        bookingRequest.setIssueDate(request.getIssueDate());
        bookingRequest.setExpirationDate(request.getExpirationDate());
        bookingRequest.setCurrency(request.getCurrency());
        bookingRequest.setIncotermCode(request.getIncotermCode());
        bookingRequest.setFreightMode(request.getFreightMode());
        bookingRequest.setOriginCountry(request.getOriginCountry());
        bookingRequest.setDestinationCountry(request.getDestinationCountry());
        bookingRequest.setFobValue(request.getFobValue());
        bookingRequest.setStatus(request.getStatus());
        bookingRequest.setCreatedAt(request.getCreatedAt());
        bookingRequest.setActive(request.getActive());
        bookingRequest.setSupplier(toCreateSupplier(request.getSupplier()));
        bookingRequest.setItems(toListBookingItem(request.getItems()));
        return bookingRequest;
    }

    public Supplier toCreateSupplier(CreateSupplier supplier){
        if(Objects.isNull(supplier)){
            throw new BookingNotFoundException("Error al mapear los valores de supplier para respuesta del servicio");
        }
        Supplier response = new Supplier();
        response.setId(supplier.getId());
        response.setName(supplier.getName());
        response.setTaxId(supplier.getTaxId());
        response.setAddress(supplier.getAddress());
        response.setCountry(supplier.getCountry());
        response.setContactEmail(supplier.getContactEmail());

        return response;
    }

    public List<BookingItem> toListBookingItem(List<CreateBookingItem> itemList) {
        if(itemList == null || itemList.isEmpty()){
            throw new BookingNotFoundException("Error al mapear las listas de BookingItem para la respuesta del servicio");
        }

        return itemList.stream()
                .map(this::toBookingItem)
                .toList();
    }

    public BookingItem toBookingItem(CreateBookingItem item){
        if(Objects.isNull(item)){
            throw new BookingNotFoundException("Error al mapear los valores de BookingItem para la respuesta del servicio");
        }

        BookingItem itemResponse = new BookingItem();
        itemResponse.setId(item.getId());
        itemResponse.setSku(item.getSku());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setUnitPrice(item.getUnitPrice());
        itemResponse.setQuantity(item.getQuantity());
//        itemResponse.setTotalMount(item.getTotalMount());

        return itemResponse;
    }

}
