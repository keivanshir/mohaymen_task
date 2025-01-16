package com.example.mohaymen_task.service.implementation;

import com.example.mohaymen_task.configuration.Configuration;
import com.example.mohaymen_task.dto.Mapper;
import com.example.mohaymen_task.dto.Response;
import com.example.mohaymen_task.dto.TransactionDto;
import com.example.mohaymen_task.entity.Transaction;
import com.example.mohaymen_task.enums.TransactionStatus;
import com.example.mohaymen_task.enums.TransactionType;
import com.example.mohaymen_task.exception.NotFoundException;
import com.example.mohaymen_task.repository.TransactionRepository;
import com.example.mohaymen_task.service.TransactionService;
import com.example.mohaymen_task.service.implementation.accountStrategy.OperationStrategy;
import com.example.mohaymen_task.service.implementation.accountStrategy.OperatorSelector;
import com.example.mohaymen_task.service.implementation.transactionStrategy.TransactionCreatorStrategy;
import com.example.mohaymen_task.service.implementation.transactionStrategy.TransactionStrategy;
import com.example.mohaymen_task.specifications.AccountTurnoverSpecification;
import com.example.mohaymen_task.utils.TransactionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplementation implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final OperatorSelector operatorSelector;
    private final TransactionUtils transactionUtils;
    private final Configuration configuration;
    private final Mapper mapper;


    public TransactionServiceImplementation(TransactionRepository transactionRepository,
                                            OperatorSelector operatorSelector,
                                            TransactionUtils transactionUtils,
                                            Configuration configuration, Mapper mapper) {
        this.transactionRepository = transactionRepository;
        this.operatorSelector = operatorSelector;
        this.transactionUtils = transactionUtils;
        this.configuration = configuration;
        this.mapper = mapper;
    }

    @Override
    public Response<TransactionDto> createTransaction(TransactionDto transactionDto) {

        TransactionCreatorStrategy strategy = TransactionStrategy.getTransaction(transactionDto.getTransactionType());
        Transaction transaction = strategy.createTransaction(transactionDto.getSourceAccountNumber(),
                transactionDto.getDestinationAccountNumber());
        transaction.setValue(transactionDto.getValue());
        transaction.setTrackingCode(generate10DigitNumber());

        OperationStrategy operationStrategy = operatorSelector.getStrategy(transactionDto.getTransactionType());

        Transaction savedTransaction;

        try{
            operationStrategy.operate(transactionDto.getSourceAccountNumber(),
                    transactionDto.getDestinationAccountNumber(), transactionDto.getValue());
            if (transactionDto.getTransactionType().equals(TransactionType.TRANSFER)){
                operationStrategy.operate(transactionDto.getSourceAccountNumber(),
                        configuration.mainBankAccount, transactionUtils.calculateFee(transactionDto.getValue()).longValue());
            }
            transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);

        } catch (Exception exception){
            exception.printStackTrace();
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            // todo write log here
        } finally {
            savedTransaction = transactionRepository.save(transaction);
        }
        return Response.<TransactionDto>builder()
                .statusCode(200)
                .message("تراکنش ثبت شد!")
                .data(mapper.mapToTransactionDto(savedTransaction))
                .build();
    }

    public String generate10DigitNumber() {
        Random random = new Random();
        long number;

        do {
            number = 1_000_000_000L + (long) (random.nextDouble() * 9_999_999_999L);
        } while (!isUniqueInDatabase(number));

        return String.valueOf(number);
    }

    private boolean isUniqueInDatabase(long number) {
        Optional<Transaction> transactionOptional = transactionRepository.findTransactionByTrackingCode(String.valueOf(number));
        return transactionOptional.isEmpty();
    }

    @Override
    public Response<TransactionStatus> getTransactionStatusByTrackingCode(Long trackingCode) {
        Optional<Transaction> transactionOptional = transactionRepository.findTransactionByTrackingCode(String.valueOf(trackingCode));
        if (transactionOptional.isPresent()){
            return Response.<TransactionStatus>builder()
                    .data(transactionOptional.get().getTransactionStatus())
                    .message("وضعیت تراکنش")
                    .statusCode(200)
                    .build();
        } else throw new NotFoundException("کد پیگیری نامعتبر است!");
    }

    @Override
    public Response<List<TransactionDto>> findAllTransactionsByFilter(int page,
                                                                      int size,
                                                                      TransactionType type,
                                                                      String accountNumber,
                                                                      Long fromValue,
                                                                      Long toValue,
                                                                      LocalDate fromDate,
                                                                      LocalDate toDate) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Specification<Transaction> specification = Specification.
                where(AccountTurnoverSpecification
                        .filterTransactions(type, accountNumber, fromValue, toValue, fromDate, toDate));

        Page<Transaction> transactionPage = transactionRepository.findAll(specification, pageable);
        if (!transactionPage.isEmpty()){
            return Response.<List<TransactionDto>>builder()
                    .statusCode(200)
                    .message("تراکنش های فیلتر شده")
                    .data(transactionPage.getContent()
                            .stream()
                            .map(mapper::mapToTransactionDto)
                            .collect(Collectors.toList()))
                    .build();
        } else throw new NotFoundException("تراکنشی پیدا نشد!");
    }
}
