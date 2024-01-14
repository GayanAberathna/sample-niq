package lk.sample.application.controller.eCommerce;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.sample.application.aspects.TrackExecutionTime;
import lk.sample.application.response.entities.SuccessMessage;
import lk.sample.application.util.ResponseUtils;
import lk.sample.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;

@RestController
@RequestMapping("/ecommerce/v1")
@RequiredArgsConstructor
public class EcommerceController {

    //TODO: avoid directly using entity class in controller level, need to add dtos

    private static final Logger LOGGER = LogManager.getLogger(EcommerceController.class);

    private final ResponseUtils responseUtils;
    private final ProductService productService;

    /**
     * GetProductsByShopper
     * @param traceId
     * @param shopperId
     * @param category
     * @param brand
     * @param limit
     * @param offset
     * @return
     */
    @GetMapping("/score-by-shopper/{shopperId}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get score by shopper is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    public ResponseEntity<SuccessMessage> getProductsByShopper(@RequestHeader("trace-Id") String traceId,
                                                               @PathVariable String shopperId,
                                                               @RequestParam(required = false) String category,
                                                               @RequestParam(required = false) String brand,
                                                               @RequestParam(defaultValue = "10") @Max(100) int limit,
                                                               @RequestParam(defaultValue = "0") int offset) {
        LOGGER.info("get products by shopper with trace-Id = {}|shopperId ={}|category = {}|brand = {}|limit = {}|offset = {}",
                traceId, shopperId, category, brand, limit, offset);
        return responseUtils.wrapSuccess(productService.getProductsByShopper(shopperId, category, brand, limit, offset),
                HttpStatus.OK);
    }

    /**
     * getShopperByProduct
     * @param traceId
     * @param productId
     * @param limit
     * @param offset
     * @return
     */
    @GetMapping("/shopper-by-product/{productId}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get shopper by product is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    public ResponseEntity<SuccessMessage> getShopperByProduct(@RequestHeader("trace-Id") String traceId,
                                                               @PathVariable String productId,
                                                               @RequestParam(defaultValue = "10") @Max(1000) int limit,
                                                               @RequestParam(defaultValue = "0") int offset) {
        LOGGER.info("get shopper by products with trace-Id = {}|productId ={}|limit = {}|offset = {}",
                traceId, productId,limit, offset);
        return responseUtils.wrapSuccess(productService.getShopperByProduct(productId,limit, offset),
                HttpStatus.OK);
    }
}
