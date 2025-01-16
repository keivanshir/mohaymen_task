package com.example.mohaymen_task.controller;

import com.example.mohaymen_task.dto.Response;
import com.example.mohaymen_task.dto.TransactionDto;
import com.example.mohaymen_task.enums.TransactionStatus;
import com.example.mohaymen_task.enums.TransactionType;
import com.example.mohaymen_task.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "creates transaction based on type",
            responses = {
                    @ApiResponse(responseCode = "201", description = "transaction created"),
                    @ApiResponse(responseCode = "400", description = "bad request"),
                    @ApiResponse(responseCode = "404", description = "not found"),
            })
    @PostMapping("/add")
    public ResponseEntity<Response<TransactionDto>> createTransaction(@RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @GetMapping("/getTransactionStatus")
    public ResponseEntity<Response<TransactionStatus>> getTransactionStatus(@RequestParam Long trackingCode) {
        return new ResponseEntity<>(transactionService.getTransactionStatusByTrackingCode(trackingCode), HttpStatus.OK);
    }

    @GetMapping("/findAllTransactionsByFilter")
    public ResponseEntity<Response<List<TransactionDto>>> findAllTransactionsByFilter(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) Long fromValue,
            @RequestParam(required = false) Long toValue,
            @RequestParam(required = false) LocalDate fromDate,
            @RequestParam(required = false) LocalDate toDate

    ) {
        return new ResponseEntity<>(
                transactionService.findAllTransactionsByFilter(
                        page, size, type, accountNumber, fromValue, toValue, fromDate, toDate),
                HttpStatus.OK);
    }

}
