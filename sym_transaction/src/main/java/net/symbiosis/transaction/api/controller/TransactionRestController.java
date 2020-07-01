package net.symbiosis.transaction.api.controller;

/***************************************************************************
 * *
 * Created:     11 / 03 / 2020                                             *
 * Author:      Tsungai Kaviya                                             *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 * *
 ***************************************************************************/

import io.swagger.annotations.ApiOperation;
import net.symbiosis.common.contract.SymResponse;
import net.symbiosis.common.contract.SymWalletList;
import net.symbiosis.common.contract.SymWalletTransactionList;
import net.symbiosis.transaction.api.service.TransactionRequestProcessor;
import net.symbiosis.transaction.api.service.TransactionRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static java.lang.String.format;
import static net.symbiosis.common.utilities.WebUtils.getRealParamValue;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionRestController implements TransactionRestService {

    private static final Logger logger = Logger.getLogger(TransactionRestController.class.getSimpleName());
    private final TransactionRequestProcessor transactionRequestProcessor;

    @Autowired
    public TransactionRestController(TransactionRequestProcessor transactionRequestProcessor) {
        this.transactionRequestProcessor = transactionRequestProcessor;
    }

    @Override
    @ApiOperation(value = "Get wallet information for specified user ID", response = SymResponse.class)
    @GetMapping("/user/{userId}/wallet")
    public SymWalletList getWallet(@PathVariable("userId") Long userId, @RequestParam("channel") String channel) {
        logger.info(format("Got request to get wallet information for userId %s on channel %s", userId, channel));
        return transactionRequestProcessor.getWallet(getRealParamValue(userId), getRealParamValue(channel));
    }

    @Override
    @ApiOperation(value = "Get wallet transaction history for specified user ID", response = SymResponse.class)
    @GetMapping("/user/{userId}/history")
    public SymWalletTransactionList getWalletHistory(@PathVariable("userId") Long userId, @RequestParam("channel") String channel) {
        logger.info(format("Got request to get transaction history for userId %s on channel %s", userId, channel));
        return transactionRequestProcessor.getWalletHistory(getRealParamValue(userId), getRealParamValue(channel));
    }
}
