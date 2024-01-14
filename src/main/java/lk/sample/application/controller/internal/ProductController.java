package lk.sample.application.controller.internal;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.sample.application.aspects.TrackExecutionTime;
import lk.sample.application.response.entities.SuccessMessage;
import lk.sample.application.util.ResponseUtils;
import lk.sample.domain.model.Product;
import lk.sample.domain.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@RequestMapping("/products/v1")
public class ProductController {

    //TODO: add proper logging for each methods
    //TODO: avoid directly using entity class in controller level, need to add dtos

    private final ProductService productService;
    private final ResponseUtils responseUtils;
    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    /**
     * getAllProductsWithPagination
     * @param traceId
     * @param pageable
     * @return
     */
    @GetMapping("/paged")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get products is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> getAllProductsWithPagination(@RequestHeader("trace-Id") String traceId,
                                                                       Pageable pageable) {
        LOGGER.info("get all products with trace-Id = {}",traceId);
        return responseUtils.wrapSuccess(productService.getAllProductsWithPagination(pageable), HttpStatus.OK);
    }

    /**
     * getProductById
     * @param traceId
     * @param productId
     * @return
     */
    @GetMapping("/{productId}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get product is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> getProductById(@RequestHeader("trace-Id") String traceId,
                                                         @PathVariable String productId) {
        return responseUtils.wrapSuccess(productService.getProductById(productId), HttpStatus.OK);
    }

    /**
     * saveProduct
     * @param traceId
     * @param product
     * @return
     */
    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 201, message = "save products is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> saveProduct(@RequestHeader("trace-Id") String traceId,
                                                      @RequestBody Product product) {
        return responseUtils.wrapSuccess(productService.saveProduct(product), HttpStatus.CREATED);
    }

    /**
     * deleteProduct
     * @param traceId
     * @param productId
     * @return
     */
    @DeleteMapping("/{productId}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "delete products is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> deleteProduct(@RequestHeader("trace-Id") String traceId,
                                                        @PathVariable String productId) {
        productService.deleteProduct(productId);
        return responseUtils.wrapSuccess("Success",HttpStatus.NO_CONTENT);
    }
}
