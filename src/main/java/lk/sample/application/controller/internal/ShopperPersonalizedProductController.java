package lk.sample.application.controller.internal;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.sample.application.aspects.TrackExecutionTime;
import lk.sample.application.response.entities.SuccessMessage;
import lk.sample.application.util.ResponseUtils;
import lk.sample.domain.model.ShopperPersonalizedProduct;
import lk.sample.domain.service.ShopperPersonalizedProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/personalized-products/v1")
public class ShopperPersonalizedProductController {

    //TODO: add proper logging for each methods
    //TODO: avoid directly using entity class in controller level, need to add dtos

    private final ShopperPersonalizedProductService personalizedProductService;
    private final ResponseUtils responseUtils;

    /**
     * getAllPersonalizedProductsWithPagination
     * @param traceId
     * @param pageable
     * @return
     */
    @GetMapping("/paged")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get personalized products is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> getAllPersonalizedProductsWithPagination(@RequestHeader("trace-Id") String traceId,
                                                                                   Pageable pageable) {
        return responseUtils.wrapSuccess(personalizedProductService.getAllPersonalizedProductsWithPagination(pageable), HttpStatus.OK);
    }

    /**
     * getPersonalizedProductById
     * @param traceId
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get personalized product is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> getPersonalizedProductById(@RequestHeader("trace-Id") String traceId,
                                                                     @PathVariable Integer id) {
        return responseUtils.wrapSuccess(personalizedProductService.getPersonalizedProductById(id), HttpStatus.OK);
    }

    /**
     * savePersonalizedProduct
     * @param traceId
     * @param personalizedProduct
     * @return
     */
    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 201, message = "save personalized products is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> savePersonalizedProduct(@RequestHeader("trace-Id") String traceId,
                                                                  @RequestBody ShopperPersonalizedProduct personalizedProduct) {
        return responseUtils.wrapSuccess(personalizedProductService.savePersonalizedProduct(personalizedProduct), HttpStatus.CREATED);
    }

    /**
     * deletePersonalizedProduct
     * @param traceId
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "delete personalized products is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> deletePersonalizedProduct(@RequestHeader("trace-Id") String traceId,
                                                                    @PathVariable Integer id) {
        personalizedProductService.deletePersonalizedProduct(id);
        return responseUtils.wrapSuccess("Success",HttpStatus.NO_CONTENT);
    }
}
