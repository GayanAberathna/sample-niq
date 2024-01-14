package lk.sample.application.controller.internal;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lk.sample.application.aspects.TrackExecutionTime;
import lk.sample.application.response.entities.SuccessMessage;
import lk.sample.application.util.ResponseUtils;
import lk.sample.domain.model.Shopper;
import lk.sample.domain.service.ShopperService;
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
@RequestMapping("/shoppers/v1")
public class ShopperController {

    //TODO: add proper logging for each methods
    //TODO: avoid directly using entity class in controller level, need to add dtos
    //TODO: use Constant instead duplicating variables
    private static final Logger LOGGER = LogManager.getLogger(ShopperController.class);

    private final ShopperService shopperService;
    private final ResponseUtils responseUtils;

    /**
     * getRelevancyScore
     * @param traceId
     * @param shopperId
     * @return
     */
    @GetMapping("/relevancy-score/{shopperId}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get shoppers is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> getRelevancyScore(@RequestHeader("trace-Id") String traceId,
                                                                       @PathVariable String shopperId) {
        LOGGER.info("get relevancy score for shopper ={} trace-Id = {}",traceId,shopperId);
        return responseUtils.wrapSuccess(shopperService.getRelevancyScore(shopperId,traceId), HttpStatus.OK);
    }

    /**
     * getAllShoppersWithPagination
     * @param traceId
     * @param pageable
     * @return
     */
    @GetMapping("/paged")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get shoppers is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> getAllShoppersWithPagination(@RequestHeader("trace-Id") String traceId,
                                                                       Pageable pageable) {
        return responseUtils.wrapSuccess(shopperService.getAllShoppersWithPagination(pageable), HttpStatus.OK);
    }

    /**
     * getShopperById
     * @param traceId
     * @param shopperId
     * @return
     */
    @GetMapping("/{shopperId}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "get shopper is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> getShopperById(@RequestHeader("trace-Id") String traceId,
                                                         @PathVariable String shopperId) {
        return responseUtils.wrapSuccess(shopperService.getShopperById(shopperId), HttpStatus.OK);
    }

    /**
     * saveShopper
     * @param traceId
     * @param shopper
     * @return
     */
    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 201, message = "save shoppers is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> saveShopper(@RequestHeader("trace-Id") String traceId,
                                                      @RequestBody Shopper shopper) {
        return responseUtils.wrapSuccess(shopperService.saveShopper(shopper), HttpStatus.CREATED);
    }

    @DeleteMapping("/{shopperId}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "delete shoppers is success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @TrackExecutionTime
    @SneakyThrows
    public ResponseEntity<SuccessMessage> deleteShopper(@RequestHeader("trace-Id") String traceId,
                                                        @PathVariable String shopperId) {
        shopperService.deleteShopper(shopperId);
        return responseUtils.wrapSuccess("Success",HttpStatus.NO_CONTENT);
    }
}
